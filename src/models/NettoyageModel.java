package models;

import java.sql.*;
import java.util.*;
import java.text.*;
import java.sql.Date;
import java.util.Calendar;

public class NettoyageModel
{

	private static NettoyageModel instance = null;

	private final String DRIVER_NAME = "org.mariadb.jdbc.Driver";
	private final String DB_URL 	 = "jdbc:mariadb://dwarves.iut-fbleau.fr/rock";
	private final String LOGIN 		 = "rock";
	private final String  MDP		 = "rock";

	private final String DB_URL_EXTERNE	 = "jdbc:mariadb://dwarves.iut-fbleau.fr/projetihm";
	private final String LOGIN_EXTERNE	 = "projetihm";
	private final String  MDP_EXTERNE	 = "mhitejorp";

	private Connection conInterne;
	private Connection conExterne;
	private Statement stmt;

	public static NettoyageModel getInstance()
	{
		if(instance == null)
			instance = new NettoyageModel();

		return instance;
	}

	private NettoyageModel()
	{
		try
		{
			Class.forName(this.DRIVER_NAME);
			connectDB();
		} catch(ClassNotFoundException cnfe)
		{
			System.err.println("Driver not loaded by the JVM !");
		}
	}

	public void connectDB()
	{
		try
		{
			this.conInterne = DriverManager.getConnection(DB_URL, LOGIN, MDP);
		} 
		catch(SQLException err)
		{
			System.err.println(err.getMessage());
			return;
		}
	}

	public void connectDBexterne()
	{
		try
		{
			this.conExterne = DriverManager.getConnection(DB_URL_EXTERNE, LOGIN_EXTERNE, MDP_EXTERNE);
		} 
		catch(SQLException err)
		{
			System.err.println(err.getMessage());
			return;
		}
	}

	public void openStatement()
	{
		if(this.conInterne == null) connectDB();
		try
		{
			this.stmt = this.conInterne.createStatement();
		}
		catch(SQLException err)
		{
			System.err.println(err.getMessage());
			return;
		}
	}

	public void openStatementExterne()
	{
		if(this.conExterne == null) connectDBexterne();
		try
		{
			this.stmt = this.conExterne.createStatement();
		}
		catch(SQLException err)
		{
			System.err.println(err.getMessage());
			return;
		}
	}

//implementer le boolean isDirty
	// Methode pour le nettotage des chambres : 
	public String[][] getEmployer() {
		int id;
		String ListeEmployer[][]=null;
		String prenom;
		openStatement();
		String requete="Select id,prenom from personnelNettoyage";
		try {
			ResultSet rs=this.stmt.executeQuery(requete);
int size= 0;
    		if (rs != null)   
    		{  
    			rs.beforeFirst();  
    			rs.last();  
    			size = rs.getRow();
    			ListeEmployer = new String[size][2];  
    			rs.beforeFirst();
    			int i=0;
    			while(rs.next()) {

    				ListeEmployer[i][0]=Integer.toString(rs.getInt("id"));
    				ListeEmployer[i][1]=rs.getString("prenom");
    				i++;
    			}

    		}

		}catch(SQLException e) {
			id=0;
			System.out.println("Probleme executeQuery in getEmployer");
			closestmtBd();
			closeBd();
			return ListeEmployer;
		}
		
		closestmtBd();
		return ListeEmployer;
	}
//compareTo renvoi 0 si date=autre <0 si 1er < 2eme
	public String knowDepartClient(int idreservation, Date datedujour,int idChambre) {
		System.out.println(datedujour);
		String departclient=null;
		Date datedebut=null;
		int duree=0;
		openStatementExterne();
		String requete="Select duree,debut from reservationfa where id = '" + idreservation+ "';";
		try {
			ResultSet rs=this.stmt.executeQuery(requete);
			while(rs.next()) {
			duree= rs.getInt("duree");
		    datedebut= rs.getDate("debut");
}
			Calendar cal = Calendar.getInstance();
			cal.setTime(datedebut);
			cal.add( Calendar.DAY_OF_MONTH, duree );
			Date datefin =  new Date(cal.getTime().getTime()); //subtilité : 2 fois get time pour recuperer un long et pouvoir l'utilisé en bon type


			if(datedujour.after(datefin)){
				System.out.println("Datedujour is after Datefin");
               departclient="AC"; // aucun client dans la chambre
           }

           if(datedujour.before(datefin)){
           	System.out.println("Datedujour is before Datefin");
                departclient="DCNA"; // depart client pas aujourdhui
            }

            if(datedujour.equals(datefin)){
            	System.out.println("Datedujour is equal Datefin");
                departclient="DCA"; // depart client aujourdhui
                clientpartit(idChambre); 
            }

        }catch(SQLException e) {
        	System.out.println(e.getMessage());
        	System.out.println("Probleme executeQuery in knowDepartClient()");
        	closestmtBd();
        	closeBd();
        	return departclient;
        }

        closestmtBd();
        return departclient;
    }

    public String[][] recupChambreEmployer(int idemployer){
    	java.sql.Date datedujour = new java.sql.Date((new java.util.Date()).getTime());
    	openStatement();
    	String chambreanettoyer[][]=null;
    	try {
    		int firstroom=(idemployer*10)-9; 
    		int lastroom=idemployer*10;
    		String requete="Select idChambre,reservation from Chambre where idChambre >= '" + firstroom+ "' AND idChambre <= '" + lastroom + "' AND reservation > 0 AND isDirty=1 ";
    		ResultSet rs=this.stmt.executeQuery(requete);
    		int size= 0;
    		if (rs != null)   
    		{  
    			rs.beforeFirst();  
    			rs.last();  
    			size = rs.getRow();
    			chambreanettoyer = new String[size][2];  
    			rs.beforeFirst(); 
    			int i=0;
    			while(rs.next()) {

    				chambreanettoyer[i][0]=Integer.toString(rs.getInt("idChambre"));

    				chambreanettoyer[i][1]=knowDepartClient(rs.getInt("reservation"),datedujour,rs.getInt("idChambre"));
    				i++;
    			}

    		} 
    	}
    	catch(SQLException e) {
    		System.out.println("Probleme executeQuery recupChambreEmployer");
    		closestmtBd();
    		closeBd();
    		return chambreanettoyer;
    	}

    	closestmtBd();
    	return chambreanettoyer;
    }

    public void clientpartit(int idChambre) {
    	openStatement();
    	String requete="UPDATE Chambre SET reservation = 0 WHERE idChambre '" + idChambre + "'";
    	try {
    		this.stmt.executeUpdate(requete);

    	}
    	catch(SQLException e) {
    		System.out.println("Probleme executeQuery clientpartit");
    		closestmtBd();
    		closeBd();
    		return;
    	}
    	return;
    }

    public void nettoyageFait(int idChambre) {
    	openStatement();
    	String requete="UPDATE Chambre SET isDirty = 0 WHERE idChambre ='" + idChambre + "'";
    	try {
    		this.stmt.executeUpdate(requete);

    	}
    	catch(SQLException e) {
    		System.out.println("Probleme executeQuery nettoyageFait");
    		closestmtBd();
    		closeBd();
    		return;
    	}
    	return;
    }


    public void closestmtBd() {
    	try {
    		this.stmt.close();
    	} catch (SQLException e4) {
    		System.out.println("Probleme Fermeture canal");
    		closeBd();
    	}
    }

    public void closeBd() {
    	try {

    		this.conInterne.close();
    		this.conExterne.close();

    		System.out.println("Fermeture de la base de donnée");
    	}catch(SQLException e4) {
    		System.out.println("Impossible de fermer la BD");
    	}
    }

}

package models;

import java.sql.*;
import java.util.*;
import java.text.*;
import java.sql.Date;
import java.lang.Object;
import org.joda.time.DateTime;

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

	private Connection con;
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
			this.con = DriverManager.getConnection(DB_URL, LOGIN, MDP);
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
			this.con = DriverManager.getConnection(DB_URL_EXTERNE, LOGIN_EXTERNE, MDP_EXTERNE);
		} 
		catch(SQLException err)
		{
			System.err.println(err.getMessage());
			return;
		}
	}

	public void openStatement()
	{
		if(this.con == null) connectDB();
		try
		{
			this.stmt = this.con.createStatement();
		}
		catch(SQLException err)
		{
			System.err.println(err.getMessage());
			return;
		}
	}

	public void openStatementExterne()
	{
		if(this.con == null) connectDBexterne();
		try
		{
			this.stmt = this.con.createStatement();
		}
		catch(SQLException err)
		{
			System.err.println(err.getMessage());
			return;
		}
	}

	// Methode pour le nettotage des chambres : 
	public int getIdEmployer(String prenompersonnel) {
		int id;
		openStatement();
			String requete="Select id from personnelNettoyage where Prenom = '" + prenompersonnel+ "'";
			try {
				ResultSet rs=this.stmt.executeQuery(requete);
				rs.first();
				id= rs.getInt("id");
			}catch(SQLException e) {
				System.out.println("Probleme executeQuery in getIdEmployer");
				closestmtBd();
				closeBd();
				return id;
			}
		
		closestmtBd();
		return id;
	}

	public String knowDepartClient(int idreservation, Date datedujour) {
		String departclient=null;
		openStatementExterne();
			String requete="Select duree,debut from reservationfa where id = '" + idreservation+ "'";
			try {
				ResultSet rs=this.stmt.executeQuery(requete);
				rs.first();
				int duree= rs.getInt("duree");
				rs.next();
				Date dtdebut= rs.getDate("debut");
				int jour=dtdebut.getDays();     //DEBUGGER ICI

				Date datefin=datefin.setDays(jour);


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
            }

        }catch(SQLException e) {
        	System.out.println("Probleme executeQuery in getIdEmployer");
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
		String requete="Select idChambre,reservation from Chambre where idChambre >= '" + firstroom+ "' AND idChambre <= '" + lastroom + "' AND reservation =! 0 ";
		ResultSet rs=this.stmt.executeUpdate(requete);
		int size= 0;
		if (rs != null)   
		{  
			rs.beforeFirst();  
			rs.last();  
			size = rs.getRow();
			chambreanettoyer = new String[size][2];  
			rs.first();
			int i=0;
			while(rs.next()) {

				chambreanettoyer[i][0]=Integer.toString(rs.getInt("idChambre"));
				chambreanettoyer[i][1]=knowDepartClient(rs.getInt("reservation"),datedujour);
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
		this.con.close();

		System.out.println("Fermeture de la base de donnée");
	}catch(SQLException e4) {
		System.out.println("Impossible de fermer la BD");
	}
}

}

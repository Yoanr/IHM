package models;

import java.sql.*;
import java.util.*;
import java.text.*;

/**
 * Classe AccuielModel
 * @author Martin Barreau
 * 
 * Classe permettant d'accéder aux bases de données
 * cette classe est soumise à la règle du design pattern singleton
 * la classe devient accessible de manière globale dans tout le programme,
 * pour éviter de polluer les connexions à la BD
 */

public class AccueilModel
{

	private static AccueilModel instance = null;

	private final String DRIVER_NAME 	= "org.mariadb.jdbc.Driver";

	private final String DB_URL 	 	= "jdbc:mariadb://dwarves.iut-fbleau.fr/projetihm";
	private final String INTERNAL_DB 	= "jdbc:mariadb://dwarves.iut-fbleau.fr/rock";

	private final String INTERNAL_LOGIN = "rock";
	private final String INTERNAL_MDP	= "rock";

	private final String LOGIN 		 	= "projetihm";
	private final String  MDP		 	= "mhitejorp";

	private java.sql.Date dateOfDay;
	private java.util.Date dateCompare;
	private String 		  dateStr;

	private Connection con;
	private Connection internal_con;
	private Statement stmt;
	private Statement internal_stmt;

	public static AccueilModel getInstance()
	{
		if(instance == null)
			instance = new AccueilModel();

		return instance;
	}

	public String getStrDate() { return this.dateStr; }
	public void setStrDate(String str) { this.dateStr = str; }

	private AccueilModel()
	{
		dateOfDay 	= new java.sql.Date((new java.util.Date()).getTime());
		dateStr		= dateOfDay.toString();
		try
		{
			Class.forName(this.DRIVER_NAME);
			connectDB();
			connectInternalDB();
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

	public void connectInternalDB()
	{
		try
		{
			this.internal_con = DriverManager.getConnection(INTERNAL_DB, INTERNAL_LOGIN, INTERNAL_MDP);
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

	public void openInternalStatement()
	{
		if(this.internal_con == null) connectInternalDB();
		try
		{
			this.internal_stmt = this.internal_con.createStatement();
		}
		catch(SQLException err)
		{
			System.err.println(err.getMessage());
			return;
		}
	}


	public ArrayList<ArrayList<Object>> getReservationsOfDay()
	{
		ArrayList<ArrayList<Object>> list = new ArrayList<ArrayList<Object>>();
		ArrayList<Object> tmp = new ArrayList<Object>();

		String query ="SELECT reservationfa.id, clientfa.nom, clientfa.prenom, debut, duree, categoriefa.raccourci FROM reservationfa INNER JOIN clientfa ON reservationfa.referenceClient = clientfa.id INNER JOIN categoriefa ON reservationfa.referenceCategorie = categoriefa.id WHERE debut = '" + dateStr + "'";
		try
		{
			openStatement();
			ResultSet rs = this.stmt.executeQuery(query);

			rs.first();

			int numCols = rs.getMetaData().getColumnCount();

			while(rs.next())
			{
				ArrayList<Object> row = new ArrayList<Object>();
				int  i = 1;
				while(i <= numCols)
					row.add(rs.getObject(i++));
				list.add(row);
				row = null;
			}

			this.stmt.close();
		}
		catch(SQLException err)
		{
			System.err.println(err.getMessage());
			return null;
		}

		return list;
	}

	public ArrayList<ArrayList<Object>> getReservationByName(String name)
	{
		ArrayList<ArrayList<Object>> list = new ArrayList<ArrayList<Object>>();
		String query = "SELECT reservationfa.id, clientfa.nom, clientfa.prenom, debut, duree, categoriefa.raccourci FROM reservationfa INNER JOIN clientfa ON reservationfa.referenceClient = clientfa.id INNER JOIN categoriefa ON reservationfa.referenceCategorie = categoriefa.id WHERE clientfa.nom = '" + name + "'";

		try
		{
			openStatement();
			ResultSet rs = this.stmt.executeQuery(query);

			rs.beforeFirst();

			int numCols = rs.getMetaData().getColumnCount();

			while(rs.next())
			{
				ArrayList<Object> row = new ArrayList<Object>();
				int  i = 1;
				while(i <= numCols)
					row.add(rs.getObject(i++));
				list.add(row);
				row = null;
			}
			this.stmt.close();
		}
		catch(SQLException err)
		{
			System.err.println(err.getMessage());
			return null;
		}

		return list;
	}

	public ArrayList<Object> getReservationByID(int id)
	{
		ArrayList<Object> result = new ArrayList<Object>();
		String query = "SELECT clientfa.nom, clientfa.prenom, debut, duree, categoriefa.id, categoriefa.raccourci ,categoriefa.designation FROM clientfa INNER JOIN reservationfa ON clientfa.id = reservationfa.referenceClient INNER JOIN categoriefa ON categoriefa.id = reservationfa.referenceCategorie WHERE reservationfa.id = " + id;

		try
		{
			openStatement();
			ResultSet rs = this.stmt.executeQuery(query);

			rs.beforeFirst();

			int numCols = rs.getMetaData().getColumnCount();

			while(rs.next())
			{
				int i = 1;
				while(i <= numCols)
					result.add(rs.getObject(i++));
			}

			this.stmt.close();
		}
		catch(SQLException err)
		{
			System.err.println(err.getMessage());
			return null;
		}

		return result;
	}

	public ArrayList<Integer> getAvailablesRooms(int categorie)
	{
		ArrayList<Integer> result = new ArrayList<Integer>();

		String query = "SELECT idChambre FROM Chambre WHERE Categorie = " + categorie + " AND reservation = 0 AND isDirty = 0";

		try
		{
			openInternalStatement();
			ResultSet rs = this.internal_stmt.executeQuery(query);

			rs.first();

			while(rs.next())
			{
				result.add(rs.getInt(1));
			}

			this.internal_stmt.close();
		}
		catch(SQLException err)
		{
			System.err.println(err.getMessage());
			return null;
		}

		return result;
	}

	public boolean confirmReservation(int idChambre, int idReservation)
	{
		String query = "UPDATE Chambre SET reservation = " + idReservation + ", isDirty = 1 WHERE idChambre = " + idChambre;

		try
		{
			openInternalStatement();
			this.internal_stmt.executeUpdate(query);
			this.internal_stmt.close();
		}
		catch(SQLException err)
		{
			System.err.println(err.getMessage());
			return false;
		}
		return true;
	}
}
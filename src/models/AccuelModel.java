package models;

import java.sql.*;
import java.util.*;
import java.text.*;

public class AccuelModel
{

	private static AccuelModel instance = null;

	private final String DRIVER_NAME = "org.mariadb.jdbc.Driver";
	private final String DB_URL 	 = "jdbc:mariadb://dwarves.iut-fbleau.fr/rock";
	private final String LOGIN 		 = "rock";
	private final String  MDP		 = "rock";

	private Connection con;
	private Statement stmt;

	public static AccuelModel getInstance()
	{
		if(instance == null)
			instance = new AccuelModel();

		return instance;
	}

	private AccuelModel()
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


	public ArrayList<ArrayList<Object>> getReservationsOfDay()
	{
		ArrayList<ArrayList<Object>> list = new ArrayList<ArrayList<Object>>();
		ArrayList<Object> tmp = new ArrayList<Object>();

		java.sql.Date d = new java.sql.Date((new java.util.Date()).getTime());
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = df.format(d);

		String query = "Select Client, nomClient, prenomClient FROM Reservation INNER JOIN Client ON Reservation.Client = Client.idClient WHERE debut = '" + dateStr + "'";

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
		//name = "Duffet";
		ArrayList<ArrayList<Object>> list = new ArrayList<ArrayList<Object>>();
		String query = "Select Client, nomClient, prenomClient, debut FROM Reservation INNER JOIN Client ON Reservation.Client = Client.idClient WHERE nomClient = '" + name + "'";

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

}
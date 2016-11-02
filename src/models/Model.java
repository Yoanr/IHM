import java.sql.*;

public class Model {

	private Connection con;
	private Statement stmt;

	public Model() {

	}

	public int loadBd() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		}
		catch(ClassNotFoundException e1) {
			System.out.println("Probleme Chargement BD");

			return -1;
		}
		return 0;
	}

	public int connectBdInterne() {
		try {
			this.con =DriverManager.getConnection("jdbc:mariadb://ambarona.arda/rock","rock","toto");
		}
		catch(SQLException e2) {
			System.out.println("Probleme Connexion BD interne");

			return -1;
		}
		return 0;
	}

	public int connectBdExterne() {
		try {
			this.con =DriverManager.getConnection("jdbc:mariadb://ambarona.arda/projetihm","projetihm","mhitejorp");
		}
		catch(SQLException e2) {
			System.out.println("Probleme Connexion BD externe");

			return -1;
		}
		return 0;
	}

	public int openstmtBd() {
		try {
			this.stmt = this.con.createStatement();
		}
		catch(SQLException e3) {
			System.out.println("Probleme Ouverture canal");
			closeBd();
			return -1;
		}
		return 0;
	}

	

	public void executeUpdate(String requete) {

try {
		this.stmt.executeUpdate(requete);
	    }
	    catch(SQLException e) {
		 System.out.println("Probleme   executeUpdate ");
		return -1;
		
	    }

	}

	public void executeQuery(String requete) {

try {
		this.stmt.executeQuery(requete);
	    }
	    catch(SQLException e) {
		 System.out.println("Probleme   executeQuery ");
		return -1;
		
	    }

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

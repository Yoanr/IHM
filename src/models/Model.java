/*package models;

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
			this.con =DriverManager.getConnection("jdbc:mariadb://dwarves.arda/rock","rock","rock");
		}
		catch(SQLException e2) {
			System.out.println("Probleme Connexion BD interne");

			return -1;
		}
		return 0;
	}

	public int connectBdExterne() {
		try {
			this.con =DriverManager.getConnection("jdbc:mariadb://dwarves.arda/projetihm","projetihm","mhitejorp");
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

// Methode pour la reservation : 
	public String[][] getReservationByClient(String clientName) {
		String DataReservation[][]=null;
		if(openstmtBd()==0) {

			String requete="SELECT id,clientfa.nom,duree,debut,categoriefa.id,categoriefa.raccourci FROM reservationfa INNER JOIN clientfa ON (reservationfa.referenceClient = clientfa.id) INNER JOIN categoriefa ON (reservationfa.referenceCategorie = categoriefa.id) WHERE clientfa.nom = '" + clientName + "'";
			try {
				ResultSet response=this.stmt.executeQuery(requete);
				int size= 0;
				if (response != null)   
				{  
					response.beforeFirst();  
					response.last();  
					size = response.getRow();
					DataReservation = new String[size][5];  
					response.first();
					int i=0;
					while(response.next()) {

						DataReservation[i][0]=Integer.toString(response.getInt("idReservation"));
						DataReservation[i][1]=Integer.toString(response.getInt("duree"));
						DataReservation[i][2]=response.getDate("debut").toString();
						DataReservation[i][3]=Integer.toString(response.getInt("Chambre"));
						DataReservation[i][4]=response.getString("raccourci");
						DataReservation[i][5]=
						i++;
					}

				}


			}
			catch(SQLException e) {
				System.out.println("Probleme   executeQuery getreservationByClient");
				closestmtBd();
				closeBd();

			}

			closestmtBd();
		}
		return DataReservation;
	}

	public String validerReservation(int reservationId) { 
		if(openstmtBd()==0) {
			String requete="UPDATE Reservation SET Reservation.checkin = 1 Where idReservation = '" + reservationId+ "'";
			String requete2="UPDATE Chambre SET isDirty = 1 Where Reservation= '" + reservationId+ "'";
			try {
				this.stmt.executeUpdate(requete);
				this.stmt.executeUpdate(requete2);
			}
			catch(SQLException e) {
				System.out.println("Probleme   executeUpdate validerReservation ");
				closestmtBd();
				closeBd();
				return "Un probleme est survenu pour valider votre réservation !";

			}

		}
		else {
			return "Erreur interne (BD stmt)";
		}
		return "Votre validation à bien été pris en compte";
	}

	public String[][] getAvailableRooms(int idChambre,String raccourci) {
		String DataAvailablerooms[][]=null;
		if(openstmtBd()==0) { // A RECODER 

			// TODO: 
			String requete="SELECT * FROM Chambre INNER JOIN Categorie ON (Chambre.idChambre = Categorie.Chambre) Where etat = 0 AND idChambre != " + idChambre + " AND raccourci = '" + raccourci+ "'";
			try {
				ResultSet response=this.stmt.executeQuery(requete);
				int size= 0;
				if (response != null)   
				{  
					response.beforeFirst();  
					response.last();  
					size = response.getRow();
					DataAvailablerooms = new String[size][3];  
					response.first();
					int i=0;
					while(response.next()) {

						DataAvailablerooms[i][0]=Integer.toString(response.getInt("idChambre"));
						DataAvailablerooms[i][1]=Integer.toString(response.getInt("raccourci"));
						DataAvailablerooms[i][2]=response.getDate("designation").toString();

						i++;
					}

				}


			}
			catch(SQLException e) {
				System.out.println("Probleme   executeQuery getAvailableRooms");
				closestmtBd();
				closeBd();

			}
closestmtBd();
		}

		
		return DataAvailablerooms;
	}

	public String modifierReservation(int reservationId,int idChambre) {
		if(openstmtBd()==0) {
			String requete="UPDATE Reservation SET Reservation.checkin = 1 SET Reservation.Chambre = '" + idChambre+ "' Where idReservation = '" + reservationId+ "'";
			String requete2="UPDATE Chambre SET isDirty = 1 Where Reservation= '" + reservationId+ "'";
			try {
				this.stmt.executeUpdate(requete);
				this.stmt.executeUpdate(requete2);
			}
			catch(SQLException e) {
				System.out.println("Probleme executeUpdate modifierReservation ");
				closestmtBd();
				closeBd();
				return "Un probleme est survenu pour modifier votre réservation !";

			}

		}
		else {
			return "Erreur interne (BD stmt)";
		}
		closestmtBd();
		return "Votre modification à bien été pris en compte";
	}

// Methode pour le nettotage des chambres : 

	public String[][] getChambreanettoyerbyNom(String prenompersonnel) {
		Int id;
		String chambreanettoyer[][]=null;
		if(openstmtBd()==0) {
			String requete="Select id from personnelNettoyage where Prenom = '" + prenompersonnel+ "'";

			try {
				ResultSet response=this.stmt.executeQuery(requete);
				response.first();
				id= rs.getInt("id");

				try {
					firstroom=id*10+1;
					lastroom=id*10+10;
					String requete2="Select idChambre,Chambre.Categorie,Categorie.raccourci 
					from Chambre INNER JOIN Categorie ON (Chambre.Categorie = Categorie.id)
					where idChambre >= '" + firstroom+ "' AND idChambre <= '" + lastroom + "' AND isDirty = 1 ";
					ResultSet response=this.stmt.executeUpdate(requete2);
					int size= 0;
					if (response != null)   
					{  
						response.beforeFirst();  
						response.last();  
						size = response.getRow();
						chambreanettoyer = new String[size][2];  
						response.first();
						int i=0;
						while(response.next()) {

							chambreanettoyer[i][0]=Integer.toString(response.getInt("idChambre"));
							chambreanettoyer[i][1]=response.getInt("raccourci");
							i++;
						}

					} 

					try {
						int j;
						//Date du jour ici
						Date date = new Date();
					for(j=0;j<size;j++) {

						String requete3="Select debut,duree from Reservation where idChambre = '" + Integer.parseInt(chambreanettoyer[0][j]) + "'";
						ResultSet response=this.stmt.executeUpdate(requete3);
						if (response != null)   
					{ 
						response.first();
						date=getDate("debut");
						duree=getDate("duree");
// non fini ici , a continuer

					}
					}

					}
					catch(SQLException e) {
						System.out.println("Probleme executeQuery requete3 ");
						closestmtBd();
						closeBd();
						return chambreanettoyer;
					}
				}	
				
				catch(SQLException e) {
					System.out.println("Probleme executeQuery requete2 ");
					closestmtBd();
					closeBd();
					return chambreanettoyer;
				}	
			}
			catch(SQLException e) {
				System.out.println("Probleme executeQuery requete ");
				closestmtBd();
				closeBd();
				return chambreanettoyer;
			}



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
*/
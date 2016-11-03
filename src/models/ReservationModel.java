import java.sql.*;

public class ReservationModel extends Model {
	public ReservationModel() {


	}

	public String getReservationByClient(String clientName) {
		String requete="SELECT * FROM Reservation INNER JOIN Client WHERE Reservation.Client = Client.idClient AND Client.nomClient = '" + clientName + "'";
		return requete;
	}

	public boolean validerReservation(int reservationId) {
		return false;
	}

	public void modifierReservation(int reservationId) {

	}

}

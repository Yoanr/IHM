//ordre d'exec des fonctions : 

/*Model m = new Model();
m.loadBd();
m.connectBDInterne();
// dans le listener oublie pas de recupere le nom du client
String tableaureservation = new String[][];
tableaureservation=m.getReservationByClient(CLIENTNAME);
// OCCUPE TOI DE LAFFICHER DANS LA VUE ;D

// ENSUITE IL CHOISIE EN COCHANT QUELLE RESERVATION CHOISIR ( recupere l'id reservation selectionner)

if(button=="valider"){
String responsemessage=validerReservation(IDRESERVATION)
//afficher responsemessage dans une bulle dans la vue
}

else {
	String tableauchambredispo = new String[][];
tableauchambredispo=getAvailableRooms(IDCHAMBRE_NON_DESIRER,RACCOURCI_CONTRAINTE);
//afficher dans la vue la liste des chambres disponibles
// ENSUIE IL CHOISIE EN COCHANT LA CHAMBRE (recupere idchambre choisie)
 String responsemessage=modifierReservation(IDRESERVATION,IDCHAMBRE_CHOISIE);
 //afficher responsemessage dans une bulle dans la vue
}

//avant que le progamme se ferme 
m.closeBd();*/

package controllers;

import views.accueil.*;
import models.*;

import java.util.ArrayList;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class controller implements ActionListener
{
	/**
	 * @deprecated la classe Model n'arrive pas à compiler ...
	 */
	private Model m;
	private SearchView sv;


	public controller(SearchView sv)
	{
		this.m 	= new Model();
		this.sv = sv;
	}

	/**
	 * HACK: Wrapper pour ajouter un tableau de String retourné par la DB
	 * ajout de la valeur faux par défaut si la réservartion n'est pas validée
	 */
	private void addRow(String[] row)
	{
		ArrayList<Object> list = new ArrayList<Object>();
		JCheckBoxTable tab = this.sv.getResultTab();

		for(int i = 0; i < row.length; i++)
			list.add(row[i]);
		if(row.length == 3)
			list.add(false);

		this.sv.getRes().setData(list.toArray());
	}

	/**
	 * Listener permettant de prendre les données de la DB
	 * avec la valeur récupérée dans un champ de texte
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand().equals(sv.SEARCH_BTN_TXT))
		{
			String clientName = this.sv.getText();
			String t[][] = {{"42", "58", "dsqdq"}}; //tableau de test ...


			String reservations[][];
			// Regarder si le champ est vide
			if(clientName.trim().equals(""))
			{
				this.sv.showError();
				return;
			}

			this.sv.hidePreviousError(); // Au cas où, l'utilisateur ait fait une erreur avant

			/**
			 * BUG: Vérifier si le model retourne bien le tableau au bon format
			 */

			//m.loadBd();
			//m.connectBdInterne();
			//reservations = m.getReservationByClient(clientName);

			for(int i = 0; i < 1/*reservations.length*/; i++)
				addRow(t[i]);

			this.sv.getRes().setVisible(true);
			this.sv.refresh();
			//m.closeBd();
		}

		if(e.getActionCommand().equals(JReservation.CONFIRMATION))
		{
			// TODO: Ajouter les fonctions de base de données
			JOptionPane.showMessageDialog(null,"La réservation a bien été modifiée !", "Réservation", JOptionPane.DEFAULT_OPTION);
			return;
		}
	}
}
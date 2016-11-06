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
		Test tab = this.sv.getResultTab();

		for(int i = 0; i < row.length; i++)
			list.add(row[i]);
		if(row.length == 3)
			list.add(false);

		Object[] tmp = new Object[list.size()];
		tmp = list.toArray(tmp);

		tab.addRow(list.toArray());
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
			//String t[] = {"42", "58", "dsqdq"}; tableau de test ...


			String reservations[][];
			Test tab = this.sv.getResultTab();
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

			m.load();
			m.connectBDInterne();
			reservations = m.getReservationByClient(clientName);

			for(int i = 0; i < reservations.length; i++)
				addRow(t[i]);

			this.sv.getTab().setVisible(true);
			this.sv.refresh();
			m.closeBd();
		}
	}
}
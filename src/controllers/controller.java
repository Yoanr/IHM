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
	//private Model m;
	private SearchView sv;


	public controller(SearchView sv)
	{
		//this.m 	= new Model();
		this.sv = sv;
	}

	/**
	 * HACK: Wrapper pour ajouter un tableau de String retourné par la DB
	 * ajout de la valeur faux par défaut si la réservartion n'est pas validée
	 */
	private void addRow(ArrayList<Object> row)
	{
		JScrollPane tab = this.sv.getResultTab();

		row.add(false);

		this.sv.getModel().addRow(row.toArray());
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

			clientName.trim();

			// Regarder si le champ est vide
			if(clientName.equals(""))
			{
				this.sv.showError();
				return;
			}

			this.sv.removeAllRows();
			this.sv.hidePreviousError(); // Au cas où, l'utilisateur ait fait une erreur avant

			AccuelModel am = AccuelModel.getInstance();
			ArrayList<ArrayList<Object>> l = am.getReservationByName(clientName);

			for(int i = 0; i < l.size(); i++)
				addRow(l.get(i));

			this.sv.refresh();
		}

		if(e.getActionCommand().equals(sv.REFRESH_TXT))
		{
			this.sv.updateFromCache();
			this.sv.refresh();
		}
	}
}

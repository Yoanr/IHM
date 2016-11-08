package controllers;

import views.accueil.*;
import models.*;

import java.util.ArrayList;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
/*

//AccuelModel am = AccuelModel.getInstance(); => VIEW

*/
public class ControllerNettoyage implements ActionListener
{
public ControllerNettoyage()
	{
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		NettoyageModel am = NettoyageModel.getInstance();

		if(e.getActionCommand().equals("Rechercher"))
		{
			//recuperer pseudo dans la view;
			String pseudoPersonnel;
			
			int idPersonnel= am.getIdEmployer(pseudoPersonnel); 
			// je recupere l'id du salarié
			String[][] chambreanettoyer=recupChambreEmployer();
			 //Afficher mes chambre dans la vue
		}
		if(e.getActionCommand().equals("Choix de ma chambre"))
		{
			// recuperer l'id de la reservation
			int idChambre;
			nettoyageFait(idChambre);
		}
		if(e.getActionCommand().equals("Changer autre chambre")) {

		//recuperer valeur JTEXTFIELD 
		int valuejtextfield;
		if(valuejtextfield >0 && <=100) {
		nettoyageFait(valuejtextfield);
		}


	}

}

package controllers;

import views.accueil.*;
import models.*;
import views.*;

import java.util.ArrayList;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
/*

//AccuelModel am = AccuelModel.getInstance(); => VIEW

*/
public class ControllerNettoyage implements ActionListener
{
	private NettoyageView nettoview;
	public ControllerNettoyage(NettoyageView nv )
	{
		this.nettoview=nv;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		NettoyageModel am = NettoyageModel.getInstance();
		
			//recuperer pseudo dans la view;
			 JButton a =(JButton) e.getSource(); // adresse de l'objet => cast en jbutton

			String prenom=a.getText(); //recuperer le prenom de la personne

					
			int idpersonnel=Integer.parseInt(e.getActionCommand());

			String[][] chambreanettoyer=am.recupChambreEmployer(idpersonnel);

			nettoview.afficherNettoyage(chambreanettoyer);

			 //Afficher mes chambre dans la vue
		/*
		if(e.getActionCommand().equals("Choix de ma chambre"))
		{
			// recuperer l'id de la reservation
			int idChambre=1;
			am.nettoyageFait(idChambre);
		}
		if(e.getActionCommand().equals("Changer autre chambre")) {

		//recuperer valeur JTEXTFIELD 
			int valuejtextfield=1;
			if(valuejtextfield >0 && valuejtextfield <=100) {
				am.nettoyageFait(valuejtextfield);
			}


		}*/

	}
}

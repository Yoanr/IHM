package controllers;

import views.accueil.*;
import models.*;
import views.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

/**
 * Classe ControllerNettoyage
 * @author Yoan ROCK
 * Classe permettant de Gérer les boutons qui representent les chambres assignés par le Systeme
 */

public class ControllerNettoyage implements ActionListener {

	private NettoyageView nettoview;
	private String prenom;
	private String status; // Pour faire ma difference entre "venir la 1er Card" ou "rafraichi seulement" 

	public ControllerNettoyage(NettoyageView nv, String prenomm,String statu) {
		this.nettoview=nv;
		this.prenom=prenomm;
		this.status=statu;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Stockage s = Stockage.getInstance();
		NettoyageModel am = NettoyageModel.getInstance();

		if(Integer.parseInt(e.getActionCommand())==-1) {

			JButton a =(JButton) e.getSource();
			String strchambre=a.getText();
			int idchambre=Integer.parseInt(strchambre);
			int choice=nettoview.etesvoussur(); // Verifier qu'il ne s'est pas tromper
			if (choice == JOptionPane.YES_OPTION) {

				am.nettoyageFait(idchambre);
				int idEmploye=s.getId();
				String[][] chambreanettoyer=am.recupChambreEmployer(idEmploye);
				nettoview.afficherNettoyage(chambreanettoyer,prenom);
				
			}

		}
		
		if(this.status.equals("new")) {

			//recuperer pseudo dans la view;
			 JButton a =(JButton) e.getSource(); // adresse de l'objet => cast en jbutton
			String prenom=a.getText(); //recuperer le prenom de la personne
			int idpersonnel=Integer.parseInt(e.getActionCommand());
			s.setId(idpersonnel); // permet de recuperer l'id de la personne dans les autre classes
			String[][] chambreanettoyer=am.recupChambreEmployer(idpersonnel);
			nettoview.afficherNettoyage(chambreanettoyer,prenom); // correspond au refresh du JPanel

		}
	}
}

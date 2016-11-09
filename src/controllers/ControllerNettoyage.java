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
	private String prenom;
	private int idEmploye;
	private String status;
	public ControllerNettoyage(NettoyageView nv, String prenomm,String statu)
	{
		this.nettoview=nv;
		this.prenom=prenomm;
		this.status=statu;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		NettoyageModel am = NettoyageModel.getInstance();
		if(Integer.parseInt(e.getActionCommand())==-1) {
			JButton a =(JButton) e.getSource();

			String strchambre=a.getText();
			int idchambre=Integer.parseInt(strchambre);

			int choice=nettoview.etesvoussur();


			if (choice == JOptionPane.YES_OPTION)
			{
				am.nettoyageFait(idchambre);
				String[][] chambreanettoyer=am.recupChambreEmployer(idEmploye);
				System.out.println("here");
				nettoview.afficherNettoyage(chambreanettoyer,prenom);
				this.status="new";
			}

		}
		if(Integer.parseInt(e.getActionCommand())==-2) {
			
		}
		if(this.status=="new") {


			//recuperer pseudo dans la view;
			 JButton a =(JButton) e.getSource(); // adresse de l'objet => cast en jbutton

			String prenom=a.getText(); //recuperer le prenom de la personne


			int idpersonnel=Integer.parseInt(e.getActionCommand());
			this.idEmploye=idpersonnel;
			String[][] chambreanettoyer=am.recupChambreEmployer(idpersonnel);

			nettoview.afficherNettoyage(chambreanettoyer,prenom);
		}
		


	}


}

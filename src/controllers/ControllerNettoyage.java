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
		Stockage s = Stockage.getInstance();
		NettoyageModel am = NettoyageModel.getInstance();
		if(Integer.parseInt(e.getActionCommand())==-1) {
			System.out.println("hereEEEEEEEE");

			JButton a =(JButton) e.getSource();

			String strchambre=a.getText();
			int idchambre=Integer.parseInt(strchambre);

			int choice=nettoview.etesvoussur();


			if (choice == JOptionPane.YES_OPTION)
			{
				
				am.nettoyageFait(idchambre);
				int idEmploye=s.getId();
				System.out.println(idEmploye);
				String[][] chambreanettoyer=am.recupChambreEmployer(idEmploye);
				
				for(int i=0;i<chambreanettoyer.length;i++) {
					for(int j=0;j<2;j++) {
						System.out.println(chambreanettoyer[i][j]);
						
					}
					
				}
				System.out.println(prenom);
				nettoview.afficherNettoyage(chambreanettoyer,prenom);
				
			}

		}
		if(Integer.parseInt(e.getActionCommand())==-2) {
			
		}
		if(this.status.equals("new")) {


			//recuperer pseudo dans la view;
			 JButton a =(JButton) e.getSource(); // adresse de l'objet => cast en jbutton

			String prenom=a.getText(); //recuperer le prenom de la personne


			int idpersonnel=Integer.parseInt(e.getActionCommand());
			s.setId(idpersonnel);
			//setIdEmploye(idpersonnel);
			String[][] chambreanettoyer=am.recupChambreEmployer(idpersonnel);

			nettoview.afficherNettoyage(chambreanettoyer,prenom);
		}
		


	}


}

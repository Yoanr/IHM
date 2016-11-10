package views.nettoyage;

/* Customs imports */
//import controllers.*;
import views.*;

import java.awt.color.*;
import models.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import views.NettoyageView;
import java.util.*;
import controllers.*;


public class ChoixNettoyage extends JPanel
{
	/*private String APP_NAME 				= "Nettoyage Hotel";
	private  final String SEARCH_HINT 		= "Rechercher salarié par prénom ...";
	private  final String  EMPTY_ERR    	= "Pour lancer une rechercher, merci de remplir le champ de texte";
	public   final String SEARCH_BTN_TXT	= "Rechercher";
	public	 final String REFRESH_TXT 		= "Rafraichir";*/
	
	private String[][] tableauchambre;
   private NettoyageView nv;

	public ChoixNettoyage(NettoyageView nview)
	{
				this.nv=nview;

	}
	public void setData(String[][] tableauchambreanettoyer,String prenom) {

		tableauchambre=tableauchambreanettoyer;
		this.removeAll();
		
		GridLayout gestionnaire = new GridLayout(1,2);
		GridLayout gestionnairegauche = new GridLayout(14,1);
		GridLayout gestionnairedroite= new GridLayout(14,1);
		JPanel jpgauche = new JPanel();
		JPanel jpdroite = new JPanel();
		
		this.setLayout(gestionnaire);
		jpgauche.setLayout(gestionnairegauche);
		jpdroite.setLayout(gestionnairedroite);
		
		ControllerNettoyage event = new ControllerNettoyage(this.nv,prenom,"refresh");
		JLabel title0 = new JLabel("salarie:");
		JLabel prenomsalarie = new JLabel(prenom);
		JLabel title1 = new JLabel("num");
		JLabel title2 = new JLabel("Status");
		
		
		jpgauche.add(title0);
		jpdroite.add(prenomsalarie);
		jpgauche.add(title1);
		jpdroite.add(title2);
		for(int i=0;i<tableauchambre.length;i++) {
			//NettoyageModel am = NettoyageModel.getInstance();
			JButton bouton = new JButton(tableauchambre[i][0]);
			JLabel infoclient = new JLabel(tableauchambre[i][1]);
			jpgauche.add(bouton);
			jpdroite.add(infoclient);
			bouton.setActionCommand("-1");
			bouton.addActionListener(event);
			bouton.setForeground(Color.RED);
		}
		
		JLabel jl = new JLabel("Une autre chambre ? : ");
		jpgauche.add(jl);
		JTextField jf = new JTextField();
		jpdroite.add(jf);
		JButton valider2 = new JButton("CLEAN");
		JButton legende= new JButton("legende");
		ControllerNettoyage2 event2 = new ControllerNettoyage2(jf,this.nv);
		jpgauche.add(valider2);
		jpdroite.add(legende);
		valider2.addActionListener(event2);
		legende.addActionListener(event2);
		this.add(jpgauche);
		this.add(jpdroite);
		this.revalidate();
	}

	
}
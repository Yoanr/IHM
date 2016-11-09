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
		
		GridLayout gestionnaire = new GridLayout(13,2);
		
		this.setLayout(gestionnaire);
		
		
		ControllerNettoyage event = new ControllerNettoyage(this.nv,prenom,"refresh");
		JLabel title0 = new JLabel("salarie:");
		JLabel prenomsalarie = new JLabel(prenom);
		JLabel title1 = new JLabel("num");
		JLabel title2 = new JLabel("Status");
		
		
		this.add(title0);
		this.add(prenomsalarie);
		this.add(title1);
		this.add(title2);
		for(int i=0;i<tableauchambre.length;i++) {
			//NettoyageModel am = NettoyageModel.getInstance();
			JButton bouton = new JButton(tableauchambre[i][0]);
			JLabel infoclient = new JLabel(tableauchambre[i][1]);
			this.add(bouton);
			this.add(infoclient);
			bouton.setActionCommand("-1");
			bouton.addActionListener(event);
			bouton.setForeground(Color.RED);
		}
		
		JLabel jl = new JLabel("Une autre chambre ? : ");
		this.add(jl);
		JTextField jf = new JTextField();
		this.add(jf);
		JButton valider2 = new JButton("nettoyer Manuel");
		ControllerNettoyage2 event2 = new ControllerNettoyage2(jf,this.nv);
		this.add(valider2);
		valider2.addActionListener(event2);
		this.revalidate();
	}

	
}
package views.nettoyage;

import views.*;

import java.awt.color.*;
import models.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import views.NettoyageView;
import java.util.*;
import controllers.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;


/**
 * Classe ChoixNettoyage
 * @author Yoan ROCK
 * Classe permettant de choisir quelle chambre nettoyer
 */


public class ChoixNettoyage extends JPanel {

	public static final String RETOUR =  "\u2190";
	private String[][] tableauchambre;
	private NettoyageView nv;

	public ChoixNettoyage(NettoyageView nview) {
		this.nv=nview;

	}

	public void setData(String[][] tableauchambreanettoyer,String prenom) {

		tableauchambre=tableauchambreanettoyer;
		this.removeAll();
		
		Border border = LineBorder.createBlackLineBorder();

		GridLayout gestionnaire = new GridLayout(1,2);
		GridLayout gestionnairegauche = new GridLayout(18,1);
		GridLayout gestionnairedroite= new GridLayout(18,1);
		JPanel jpgauche = new JPanel();
		JPanel jpdroite = new JPanel();
		
		this.setLayout(gestionnaire);
		jpgauche.setLayout(gestionnairegauche);
		jpdroite.setLayout(gestionnairedroite);
		
		ControllerNettoyage event = new ControllerNettoyage(this.nv,prenom,"refresh");
		JLabel title0 = new JLabel("Salarié:");
		title0.setHorizontalAlignment(SwingConstants.CENTER);
		title0.setVerticalAlignment(SwingConstants.CENTER);

		JLabel prenomsalarie = new JLabel(prenom);
		prenomsalarie.setForeground(new Color(0,0,255));
		prenomsalarie.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,30));
		prenomsalarie.setHorizontalAlignment(SwingConstants.CENTER);
		prenomsalarie.setVerticalAlignment(SwingConstants.CENTER);
		JLabel title1 = new JLabel("num Ch");
		title1.setBorder(border);
		title1.setHorizontalAlignment(SwingConstants.CENTER);
		title1.setVerticalAlignment(SwingConstants.CENTER);
		JLabel title2 = new JLabel("Status");
		title2.setBorder(border);
		title2.setHorizontalAlignment(SwingConstants.CENTER);
		title2.setVerticalAlignment(SwingConstants.CENTER);
		
		jpgauche.add(title0);
		jpdroite.add(prenomsalarie);
		JLabel vide1 = new JLabel();
		JLabel vide2 = new JLabel();
		jpgauche.add(vide1);
		jpdroite.add(vide2);
		jpgauche.add(title1);
		jpdroite.add(title2);

		for(int i=0;i<tableauchambre.length;i++) {
			
			JButton bouton = new JButton(tableauchambre[i][0]);
			JLabel infoclient = new JLabel(tableauchambre[i][1]);
			jpgauche.add(bouton);
			jpdroite.add(infoclient);
			infoclient.setBorder(border);
			bouton.setBorder(border);
			infoclient.setHorizontalAlignment(SwingConstants.CENTER);
			infoclient.setVerticalAlignment(SwingConstants.CENTER);

			bouton.setActionCommand("-1"); //permet d'identifier cette liste de bouton 
			bouton.addActionListener(event);
			bouton.setForeground(Color.BLUE);
		}
		JLabel vide3 = new JLabel();
		JLabel vide4 = new JLabel();
		jpgauche.add(vide3);
		jpdroite.add(vide4);
		
		JLabel jl = new JLabel("OTHER :");
		jl.setBorder(border);
		jl.setHorizontalAlignment(SwingConstants.CENTER);
		jl.setVerticalAlignment(SwingConstants.CENTER);
		jpgauche.add(jl);
		JTextField jf = new JTextField();
		jf.setBorder(border);
		jpdroite.add(jf);
		JButton valider2 = new JButton("CLEAN");
		JButton legende= new JButton("legende");
		JButton retour= new JButton(RETOUR);
		retour.setFont(new java.awt.Font(Font.SERIF,Font.BOLD,30));
		JLabel vide = new JLabel();
		JLabel vide5 = new JLabel();
		JLabel vide6 = new JLabel();
		JLabel vide0 = new JLabel();
		ControllerNettoyage2 event2 = new ControllerNettoyage2(jf,this.nv);
		
		jpgauche.add(vide0);
		jpdroite.add(valider2);
		jpdroite.add(vide);
		jpgauche.add(vide5);
		jpgauche.add(legende);
		jpdroite.add(retour);
		valider2.addActionListener(event2);
		legende.addActionListener(event2);
		retour.addActionListener(event2);
		this.add(jpgauche);
		this.add(jpdroite);
		this.revalidate();
	}
}

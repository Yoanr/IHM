package views;

import views.nettoyage.*;
import java.awt.*;
import javax.swing.*;
import models.*;
import java.text.SimpleDateFormat;
import java.text.DateFormat;

/**
 * Classe NettoyageView 
 * @author Yoan ROCK
 * Classe permettant de gerer les fenetres
 */

public class NettoyageView extends JFrame {

	private JPanel cards;
	private CardLayout layout;
	private JPanel firstview;
	private ChoixNettoyage choixnettoyage;

	public NettoyageView() {



		super();
		NettoyageModel am = NettoyageModel.getInstance();
		if(am.getStrDate().equals("")) {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
java.sql.Date datedujour = new java.sql.Date((new java.util.Date()).getTime());
String str= df.format(datedujour);
			this.setTitle(str);
		}else {
			this.setTitle(am.getStrDate());
		}
		this.setSize(650, 250);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		layout = new CardLayout();
		cards = new JPanel(); 
		cards.setLayout(layout);
		firstview = new SearchViewNettoyage(this);
		choixnettoyage = new ChoixNettoyage(this);
		cards.add(firstview,"1er");
		cards.add(choixnettoyage,"choixnettoyage");
		this.add(cards);
		this.setLocationRelativeTo(null);
	}

	public void refresh() {
		pack();
		setLocationRelativeTo(null);
	}

	public void swap(String jpanelname) { //Changement de Carte
		this.setSize(650, 250);
		this.layout.show(cards,jpanelname);
		revalidate();
	}

	public void afficherNettoyage(String[][] tableauchambreanettoyer,String prenom) {
		

		swap("choixnettoyage");
		this.choixnettoyage.setData(tableauchambreanettoyer,prenom);
		this.setSize(250, 550);
		//this.setResizable(false);
		revalidate();
	}

	public int etesvoussur() {
		int choice = JOptionPane.showOptionDialog(null, 
			"Nettoyer ?", 
			"Avez-vous bien nettoyé cette chambre ?", 
			JOptionPane.YES_NO_OPTION, 
			JOptionPane.QUESTION_MESSAGE, 
			null, null, null);
		return choice;
	}
	public void error() {
		JOptionPane.showMessageDialog(this,
			"Entrer un nombre entre 1 & 100.",
			"S'il vous plait",
			JOptionPane.ERROR_MESSAGE);
	}
	public void legende() {
		JOptionPane.showMessageDialog(this,
			"AC -> Aucun client aujourd'hui,\nDCNA -> Client present ET quitte pas l'hotel aujourdhui,\nDCA -> Client present et quitte l'hotel aujourdhui.");
	}
}

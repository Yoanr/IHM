package views;

import views.nettoyage.*;
import java.awt.*;
import javax.swing.*;

public class NettoyageView extends JFrame
{

	private JPanel cards;
	private CardLayout layout;
	private JPanel firstview;
	private ChoixNettoyage choixnettoyage;
	private String FRAME_NAME = "Choix prénom";
	public NettoyageView()
	{
		super("Nettoyage Express");

		this.setSize(350, 350);
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
		//afficher en 1er page de Liste employé
		//afficher ses chambres a nettoyer
	}

	public void refresh()
	{
		pack();
		setLocationRelativeTo(null);
	}

	public void swap(String jpanelname) {
		
this.layout.show(cards,jpanelname);
revalidate();
	}
	public void afficherNettoyage(String[][] tableauchambreanettoyer) {
		swap("choixnettoyage");
		this.choixnettoyage.setData(tableauchambreanettoyer);
		revalidate();
	}
}
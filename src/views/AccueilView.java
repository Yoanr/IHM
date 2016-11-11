package views;

import views.accueil.*;

import models.*;

import java.awt.*;
import javax.swing.*;

/**
 * Classe regorupant toute les vues pour l'accueil
 * @author Martin Barreau
 * @description("Classe contenant tout les JPanels pour la vue de l'accueil")
 * HACK: Pour le moment, il n'y a qu'un seul JPanel,
 * on peut en ajouter autant que l'on veux tant que l'on utilise un CardLayout
 */

public class AccueilView extends JFrame
{

	private JPanel searchView;

	public AccueilView()
	{
		super("Recherche de réservations");

		this.setSize(350, 350);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		searchView = new SearchView();

		add(searchView);
		pack();
		this.setLocationRelativeTo(null);
	}
}
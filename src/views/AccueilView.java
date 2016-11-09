package views;

import views.accueil.*;

import models.*;

import java.awt.*;
import javax.swing.*;

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
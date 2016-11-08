package views;

import views.accueil.*;

import models.*;

import java.awt.*;
import javax.swing.*;

public class AccueilView extends JFrame
{

	private JPanel metaTester;

	public AccueilView()
	{
		super("Recherche de réservations");

		this.setSize(350, 350);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		metaTester = new SearchView();

		add(metaTester);
		pack();
		this.setLocationRelativeTo(null);
	}
}
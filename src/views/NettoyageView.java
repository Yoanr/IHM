package views;

import views.nettoyage.*;

//import models.*;

import java.awt.*;
import javax.swing.*;

public class NettoyageView extends JFrame
{

	private JPanel metaTester;

	public NettoyageView()
	{
		super("Nettoyage Express");

		this.setSize(350, 350);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		metaTester = new SearchViewNettoyage();

		add(metaTester);
		pack();
		this.setLocationRelativeTo(null);
		//afficher en 1er page de rechercher et SI pseudo correct 
		//afficher ses chambres a nettoyer
	}
}
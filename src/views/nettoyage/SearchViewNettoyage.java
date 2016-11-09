package views.nettoyage;

/* Customs imports */
//import controllers.*;//setLayout(new GridBagLayout());


//import models.*;

/* Java  imports */
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

import java.util.*;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class SearchViewNettoyage extends JPanel
{
	private String APP_NAME 				= "Nettoyage Hotel";
	private  final String SEARCH_HINT 		= "Rechercher salarié par prénom ...";
	private  final String  EMPTY_ERR    	= "Pour lancer une rechercher, merci de remplir le champ de texte";

	public SearchViewNettoyage()
	{
		/*lbl						= new JLabel(APP_NAME);
		gbc 					= new GridBagConstraints();
		errorField				= new JLabel(EMPTY_ERR);
		searchButton 			= new JButton(this.SEARCH_BTN_TXT);
		refreshButton			= new JButton(this.REFRESH_TXT);
		ctrl 					= new ControllerNettoyage(this);
		searchField	= new JTextField("\r", 20); 


		NettoyageModel am = NettoyageModel.getInstance();*/
		


	}

	private void InitNettoyageView()  {
setLayout(new GridBagLayout());

/* Mise en place du label */
		/*gbc.gridx 		= 1;
		gbc.gridy 		= 1;
		gbc.gridheight 	= 1;
		gbc.gridwidth	= 1;
		gbc.insets		= new Insets(5, 5, 5, 5);

		add(lbl, gbc);

		/* Mise en place du champ de recherche */
		/*gbc.gridx 		= 1;
		gbc.gridy 		= 2;
		gbc.gridheight 	= 1;
		gbc.gridwidth	= 1;
		gbc.weightx		= 1.0;
		gbc.weighty		= 1.0;
		gbc.anchor		= GridBagConstraints.CENTER;
		gbc.fill 		= GridBagConstraints.HORIZONTAL;
		gbc.insets		= new Insets(5, 5, 5, 5);

			add(searchField, gbc);*/
	}
}
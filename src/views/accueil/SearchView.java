package views.accueil;

/* Customs imports */
import controllers.*;

/* Java  imports */
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class SearchView extends JPanel
{
	private String APP_NAME 			= "Recherche de réservations";
	private final String SEARCH_HINT 	= "Rechercher client par nom ...";
	public  final String SEARCH_BTN_TXT	= "Rechercher";
	private final String[] METADATA_TAB = { "Numéro", "Nom", "Prénom", "Confirmé" };
	private  final String  EMPTY_ERR    = "Pour lancer une rechercher, merci de remplir le champ de texte";

	private JTextField 	searchField;
	private JLabel		errorField;
	private JButton 	searchButton;
	private JCheckBoxTable 		dtm;
	private JTable 		resultTab;
	private JScrollPane resultView;

	private JReservation res;

	private controller 	ctrl;
	private GridBagConstraints gbc;
	private JLabel 		lbl;

	public SearchView()
	{
		lbl						= new JLabel(APP_NAME);
		gbc 					= new GridBagConstraints();
		searchField 			= new JTextField("\r", 20);
		errorField				= new JLabel(EMPTY_ERR);
		searchButton 			= new JButton(this.SEARCH_BTN_TXT);
		dtm 					= new JCheckBoxTable(METADATA_TAB, 0);
		resultTab				= new JTable(this.dtm);
		resultView				= new JScrollPane(this.resultTab);

		res 					= new JReservation();

		ctrl 					= new controller(this);
		initUI();

	}

	private void initUI()
	{
		errorField.setForeground(Color.RED);

		// Setting up listeners ...
		searchButton.addActionListener(ctrl);
		res.getSubmitBtn().addActionListener(ctrl);
		searchField.addFocusListener(new FocusListener()
		{
			public void focusGained(FocusEvent e)
			{
				searchField.setText("");
			}

			public void focusLost(FocusEvent e)
			{
				if(searchField.getText().equals("\r")) searchField.setText(SEARCH_HINT);
			}
		});

		setLayout(new GridBagLayout());

		/* Mise en place du label */
		gbc.gridx 		= 1;
		gbc.gridy 		= 1;
		gbc.gridheight 	= 1;
		gbc.gridwidth	= 1;
		gbc.insets		= new Insets(5, 5, 5, 5);

		add(lbl, gbc);

		/* Mise en place du champ de recherche */
		gbc.gridx 		= 1;
		gbc.gridy 		= 2;
		gbc.gridheight 	= 1;
		gbc.gridwidth	= 1;
		gbc.weightx		= 1.0;
		gbc.weighty		= 1.0;
		gbc.anchor		= GridBagConstraints.CENTER;
		gbc.fill 		= GridBagConstraints.HORIZONTAL;
		gbc.insets		= new Insets(5, 5, 5, 5);

		add(searchField, gbc);

		/* Mise en place du bouton de submit */
		gbc.gridx 		= 2;
		gbc.gridy 		= 2;
		gbc.gridheight 	= 1;
		gbc.gridwidth	= 1;
		gbc.weightx		= 1.0;
		gbc.weighty		= 1.0;
		gbc.anchor		= GridBagConstraints.CENTER;
		gbc.fill		= GridBagConstraints.HORIZONTAL;
		gbc.insets		= new Insets(5, 5, 5, 5);

		add(searchButton, gbc);

		/* Mise en place du message d'erreur */
		gbc.gridx 		= 1;
		gbc.gridy 		= 3;
		gbc.gridheight 	= 1;
		gbc.gridwidth	= 1;
		gbc.weightx		= 1.0;
		gbc.weighty		= 1.0;
		gbc.anchor		= GridBagConstraints.CENTER;
		gbc.insets		= new Insets(5, 5, 5, 5);

		add(errorField, gbc);

		/* Mise en place du tableau de réservations */
		gbc.gridx 		= 1;
		gbc.gridy 		= 4;
		gbc.gridheight 	= 1;
		gbc.gridwidth	= 1;
		gbc.weightx		= 1.0;
		gbc.weighty		= 1.0;
		gbc.anchor		= GridBagConstraints.CENTER;
		gbc.insets		= new Insets(5, 5, 5, 5);

		add(res, gbc);

		errorField.setVisible(false);
		res.setVisible(false); // Element caché au début car pas utile de le montrer vide
	}

	/* "Usefull" getters ... */
	public String getText() 						{ return this.searchField.getText(); }
	public JCheckBoxTable getResultTab() 			{ return this.dtm; }
	public JReservation getRes()					{ return this.res; }
	
	public void refresh()
	{
		views.AccueilView av = (views.AccueilView) SwingUtilities.getWindowAncestor(this);
		av.pack();
		av.setLocationRelativeTo(null);
	}

	/* Méthodes d'affichage pour la rétroaction */
	public void showError()
	{
		errorField.setVisible(true);
		refresh();
	}

	public void hidePreviousError()
	{
		if(this.errorField.isDisplayable()) errorField.setVisible(false);
		refresh();
	}

	public JScrollPane getTab()
	{
		return this.resultView;
	}
}
package views.accueil;

/* Customs imports */
import controllers.*;
import models.*;

/* Java  imports */
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

import java.util.*;
import java.text.*;


public class SearchView extends JPanel
{
	private String APP_NAME 				= "Recherche de réservations";
	private  final String SEARCH_HINT 		= "Rechercher client par nom ...";
	public   final String SEARCH_BTN_TXT	= "Rechercher";
	public	 final String REFRESH_TXT 		= "Rafraichir";
	private  final String[] METADATA_TAB 	= { "ID réservation", "Nom", "Prénom", "Durée" };
	private  final String  EMPTY_ERR    	= "Pour lancer une rechercher, merci de remplir le champ de texte";

	private JTextField 	searchField;
	private JLabel		errorField;
	private JCheckBoxTable 		dtm;
	private JTable 		resultTab;
	private JScrollPane resultView;


	private JButton 	searchButton;
	private JButton		refreshButton;

	private Controller 	ctrl;
	private GridBagConstraints gbc;
	private JLabel 		lbl;

	private ArrayList<ArrayList<Object>> cached;

	public SearchView()
	{
		lbl						= new JLabel(APP_NAME);
		gbc 					= new GridBagConstraints();
		searchField 			= new JTextField("\r", 20);
		errorField				= new JLabel(EMPTY_ERR);
		searchButton 			= new JButton(this.SEARCH_BTN_TXT);
		refreshButton			= new JButton(this.REFRESH_TXT);
		dtm 					= new JCheckBoxTable(METADATA_TAB, 0);
		resultTab				= new JTable(this.dtm);
		resultView				= new JScrollPane(this.resultTab);
		ctrl 					= new Controller(this);


		AccueilModel am = AccueilModel.getInstance();

		cached = am.getReservationsOfDay();

		int s = cached.size();

		for(int i = 0; i < s; i++)
			dtm.addRow(cached.get(i).toArray());

		initUI();
		initControllers();
	}

	/**
	 * Méthode pour initialiser la mise en forme de l'interface
	 */
	private void initUI()
	{
		errorField.setForeground(Color.RED);

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
		gbc.weightx		= 0.0;
		gbc.weighty		= 0.0;
		gbc.anchor		= GridBagConstraints.CENTER;
		gbc.insets		= new Insets(5, 5, 5, 5);

		add(resultView, gbc);

		/* Mise en place du bouton de refresh */
		gbc.gridx 		= 2;
		gbc.gridy 		= 4;
		gbc.gridheight 	= 1;
		gbc.gridwidth	= 1;
		gbc.weightx		= 0.0;
		gbc.weighty		= 0.0;
		gbc.anchor		= GridBagConstraints.PAGE_START;
		gbc.insets		= new Insets(5, 5, 5, 5);

		add(refreshButton, gbc);

		errorField.setVisible(false); // Cacher le champ d'erreur
		resultView.setVisible(true);
	}

	/**
	 * Méthode de création et/ou définitions de controlleurs
	 */
	private void initControllers()
	{
		searchButton.addActionListener(ctrl);
		refreshButton.addActionListener(ctrl);
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

		// Listener de la selection
		resultTab.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ListSelectionModel selectionModel = resultTab.getSelectionModel();
		selectionModel.addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent e)
			{
				if(!e.getValueIsAdjusting())
				{
					ArrayList<Object> list = new ArrayList<Object>();
					int i = resultTab.getSelectedRow();

					if(i < 0) return;

					int s = 0;
					for(s = 0; s < METADATA_TAB.length; s++)
						list.add(resultTab.getModel().getValueAt(i, s));

					JReservation r = new JReservation();
					r.setData(list.toArray());
					r.setVisible(true);
				}
			}
		});

	}

	/* "Usefull" getters ... */
	public String getText() 						{ return this.searchField.getText(); }
	public JScrollPane getResultTab() 				{ return this.resultView; }
	public JCheckBoxTable getModel()				{ return this.dtm; }
	public JScrollPane getTab() 					{ return this.resultView; }

	/* Méthodes d'affichage pour la rétroaction */
	
	public void refresh()
	{
		views.AccueilView av = (views.AccueilView) SwingUtilities.getWindowAncestor(this);
		av.pack();
		av.setLocationRelativeTo(null);
	}

	public void hidePreviousError()
	{
		if(this.errorField.isDisplayable()) errorField.setVisible(false);
		refresh();
	}

	public void showError()
	{
		errorField.setVisible(true);
		refresh();
	}

	/* Méthodes de rafraichissement du tableau des réservations */

	public void removeAllRows()
	{
		int count = dtm.getRowCount();

		for(int i = count - 1; i >= 0; i--)
			dtm.removeRow(i);
	}

	public void updateFromCache()
	{
		if(cached == null) return;
		if(dtm.getRowCount() > 0) removeAllRows();

		int s = cached.size();

		for(int i = 0; i < s; i++)
			dtm.addRow(cached.get(i).toArray());
	}

	public void getReservations()
	{
		cached = AccueilModel.getInstance().getReservationsOfDay();

		int s = cached.size();

		for(int i = 0; i < s; i++)
			dtm.addRow(cached.get(i).toArray());
	}
}

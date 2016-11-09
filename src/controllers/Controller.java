package controllers;

import views.accueil.*;
import models.*;

import java.util.ArrayList;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class Controller implements ActionListener
{
	private SearchView sv;

	public Controller(SearchView sv)
	{
		this.sv = sv;
	}

	/**
	 * HACK: Wrapper pour ajouter un tableau de String retourné par la DB
	 */
	private void addRow(ArrayList<Object> row)
	{
		JScrollPane tab = this.sv.getResultTab();

		row.add(false);

		this.sv.getModel().addRow(row.toArray());
	}

	/**
	 * Listener permettant de prendre les données de la DB
	 * avec la valeur récupérée dans un champ de texte
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand().equals(sv.SEARCH_BTN_TXT))
		{
			String clientName = this.sv.getText();

			clientName.trim();

			// Regarder si le champ est vide
			if(clientName.equals(""))
			{
				this.sv.showError();
				return;
			}

			this.sv.removeAllRows();
			this.sv.hidePreviousError(); // Au cas où, l'utilisateur a fait une erreur avant

			AccueilModel am = AccueilModel.getInstance();
			ArrayList<ArrayList<Object>> l = am.getReservationByName(clientName);

			for(int i = 0; i < l.size(); i++)
				addRow(l.get(i));

			this.sv.refresh();
		}

		if(e.getActionCommand().equals(sv.REFRESH_TXT))
		{
			this.sv.updateFromCache();
			this.sv.refresh();
		}
	}
}

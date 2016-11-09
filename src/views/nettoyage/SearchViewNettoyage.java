package views.nettoyage;

/* Customs imports */
//import controllers.*;//setLayout(new GridBagLayout());

import views.NettoyageView;
import models.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

import java.util.*;
import controllers.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class SearchViewNettoyage extends JPanel
{
	private String APP_NAME 				= "Nettoyage Hotel";
	private  final String SEARCH_HINT 		= "Rechercher salarié par prénom ...";
	private  final String  EMPTY_ERR    	= "Pour lancer une rechercher, merci de remplir le champ de texte";
	public   final String SEARCH_BTN_TXT	= "Rechercher";
	public	 final String REFRESH_TXT 		= "Rafraichir";

	public SearchViewNettoyage(NettoyageView nv)
	{
		NettoyageModel am = NettoyageModel.getInstance();
		String[][] listeemploye=am.getEmployer();
		
		ControllerNettoyage event = new ControllerNettoyage(nv);
		for(int i=0;i<listeemploye.length;i++) {
			
			JButton bouton = new JButton(listeemploye[i][1]);
			this.add(bouton);
			bouton.setActionCommand(listeemploye[i][0]);
			
			bouton.addActionListener(event);
		}

	}


}
package views.nettoyage;

/* Customs imports */
//import controllers.*;//setLayout(new GridBagLayout());
import views.*;

import java.awt.color.*;
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

public class ChoixNettoyage extends JPanel
{
	/*private String APP_NAME 				= "Nettoyage Hotel";
	private  final String SEARCH_HINT 		= "Rechercher salarié par prénom ...";
	private  final String  EMPTY_ERR    	= "Pour lancer une rechercher, merci de remplir le champ de texte";
	public   final String SEARCH_BTN_TXT	= "Rechercher";
	public	 final String REFRESH_TXT 		= "Rafraichir";*/
	
	private String[][] tableauchambre;

	public ChoixNettoyage(NettoyageView nv)
	{
		
		


	}
	public void setData(String[][] tableauchambreanettoyer) {
		tableauchambre=tableauchambreanettoyer;
		this.removeAll();
		for(int i=0;i<tableauchambre.length;i++) {
			NettoyageModel am = NettoyageModel.getInstance();
			System.out.println(tableauchambre[i][0]);
			System.out.println(tableauchambre[i][1]);

			JButton bouton = new JButton(tableauchambre[i][0]);
			JLabel infoclient = new JLabel(tableauchambre[i][1]);
			JLabel trait = new JLabel("/");
			this.add(bouton);
			this.add(infoclient);
			this.add(trait);

			bouton.setForeground(Color.RED);
		}

		this.revalidate();
	}

	
}
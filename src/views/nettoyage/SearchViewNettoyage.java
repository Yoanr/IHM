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


	public SearchViewNettoyage(NettoyageView nv)
	{
		NettoyageModel am = NettoyageModel.getInstance();
		String[][] listeemploye=am.getEmployer();
		GridLayout gestionnaire = new GridLayout(2,5);
		this.setLayout(gestionnaire);
		ControllerNettoyage event = new ControllerNettoyage(nv,"","new");
		for(int i=0;i<listeemploye.length;i++) {
			
			JButton bouton = new JButton(listeemploye[i][1]);
			this.add(bouton);
			bouton.setActionCommand(listeemploye[i][0]);
			
			bouton.addActionListener(event);
		}

	}


}
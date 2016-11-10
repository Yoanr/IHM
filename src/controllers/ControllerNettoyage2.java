package controllers;

import views.accueil.*;
import models.*;
import views.*;

import java.util.ArrayList;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
/*

//AccuelModel am = AccuelModel.getInstance(); => VIEW

*/
public class ControllerNettoyage2 implements ActionListener
{
	private JTextField jtf;
	private NettoyageView nv;
	
	public ControllerNettoyage2(JTextField jf,NettoyageView nview)
	{
		this.nv=nview;
		this.jtf=jf;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("legende")) {
		nv.legende();

		}
		Stockage s = Stockage.getInstance();
		NettoyageModel am = NettoyageModel.getInstance();
		String strid = jtf.getText();
		if(strid.equals(""))
		{
			return;
		}

		int idchambre=Integer.parseInt(strid);

		if(idchambre<1 || idchambre>100) {
			nv.error();
		}
		else {
			int choice=nv.etesvoussur();
			if (choice == JOptionPane.YES_OPTION)
			{
			am.nettoyageFait(idchambre);
			int idEmploye=s.getId();
			String[][] chambreanettoyer=am.recupChambreEmployer(idEmploye);
			String prenom=am.recupprenombyid(idEmploye);

			nv.afficherNettoyage(chambreanettoyer,prenom);
		}
		}
	}



}

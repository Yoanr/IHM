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
		NettoyageModel am = NettoyageModel.getInstance();
		String strid = jtf.getText();
		int idchambre=Integer.parseInt(strid);
		if(idchambre<1 || idchambre>100) {
			nv.error();
		}
		else {
			am.nettoyageFait(idchambre);
		}
	}


}

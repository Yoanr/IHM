package controllers;

import views.accueil.*;
import models.*;
import views.*;

import java.util.ArrayList;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class Stockage
{
private static Stockage instance = null;
private int idEmploye;
private Stockage() {
this.idEmploye=0;
}
public static Stockage getInstance()
{
	if(instance == null)
			instance = new Stockage();

		return instance;
}
public int getId() {
return this.idEmploye;
}
public void setId(int id) {
this.idEmploye=id;
}

}



package views.accueil;

import javax.swing.table.*;
import javax.swing.event.*;

/**
 * Classe JCheckBoxTable
 * Cette classe a été à l'origine utilisée pour avoir un JCheckBox
 * Cependant depuis il n'a plus été utilisé
 *
 * HACK: Cette classe pour réavoir un JCheckBox dans getColumnClass
 * faites un case de l'index que vous souhaitez avec le statement: return Boolean.class;
 */

public class JCheckBoxTable extends DefaultTableModel
{
	public JCheckBoxTable(String[] meta, int rowAdded)
	{
		super(meta, rowAdded);
	}

	public Class<?> getColumnClass(int column)
	{
		switch(column)
		{
			case 0: 	return String.class;
			case 1: 	return String.class;
			case 2: 	return String.class;
			case 3: 	return String.class;
			default:	return String.class;
		}
	}

	/**
	 * Méthode pour rendre une case du JTable non éditable
	 * @param int row: le numéro de ligne, int col: le numéro de colonne
	 * @return boolean détermine s'il faut éditer la case ou non
	 */
	@Override
	public boolean isCellEditable(int row, int col)
	{
		return false;
	}
}
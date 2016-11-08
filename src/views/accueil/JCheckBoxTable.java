package views.accueil;

import javax.swing.table.*;
import javax.swing.event.*;

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
			case 0: 	return Integer.class;
			case 1: 	return String.class;
			case 2: 	return String.class;
			case 3: 	return Boolean.class;
			default:	return String.class;
		}
	}

	@Override
	public boolean isCellEditable(int row, int col)
	{
		return false;
	}
}
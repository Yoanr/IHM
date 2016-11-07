package views.accueil;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

import java.util.*;

public class JReservation extends JPanel
{
	private JPanel 		roomNumber;
	private JLabel		nameLegend;
	private JLabel		pnameLegend;
	private JTextField	nameField;
	private JTextField	pnameField;
	private JButton		confirmation;
	private JButton 	modification;
	private JSpinner	spin;

	public static final String CONFIRMATION = "Confirmer";
	public static final String MODIFICATION = "Modifier";

	private GridBagConstraints gbc;

	public JReservation()
	{
		super();
		roomNumber 			= new JPanel();
		nameLegend			= new JLabel("Nom");
		pnameLegend 		= new JLabel("Prénom");
		nameField			= new JTextField(10);
		pnameField			= new JTextField(10);
		confirmation		= new JButton(JReservation.CONFIRMATION);
		modification 		= new JButton(JReservation.MODIFICATION);
		gbc 				= new GridBagConstraints();

		SpinnerModel s = new SpinnerNumberModel(0, 0, 0, 1);
		spin = new JSpinner(s);
		spin.setPreferredSize(new Dimension(100, 40));

		Font f = ((JSpinner.NumberEditor) spin.getEditor()).getTextField().getFont();

		((JSpinner.NumberEditor) spin.getEditor()).getTextField().setFont(f.deriveFont(45.0f));

		roomNumber.add(spin);

		Border b = BorderFactory.createLineBorder(Color.BLACK, 1);
		setBorder(b);
		setLayout(new GridBagLayout());

		initUI();
	}

	private void initUI()
	{
		/* Mise en place du label */
		//roomNumber.setFont(roomNumber.getFont().deriveFont(64.0f));
		gbc.gridx 		= 1;
		gbc.gridy 		= 1;
		gbc.gridheight 	= 1;
		gbc.gridwidth	= 1;
		gbc.insets		= new Insets(5, 5, 5, 5);
		add(roomNumber, gbc);

		gbc.gridx 		= 2;
		gbc.gridy 		= 1;
		gbc.gridheight 	= 1;
		gbc.gridwidth	= 1;
		gbc.insets		= new Insets(5, 5, 5, 5);
		add(nameLegend, gbc);

		gbc.gridx 		= 3;
		gbc.gridy 		= 1;
		gbc.gridheight 	= 1;
		gbc.gridwidth	= 1;
		gbc.insets		= new Insets(5, 5, 5, 5);
		add(nameField, gbc);

		gbc.gridx 		= 4;
		gbc.gridy 		= 1;
		gbc.gridheight 	= 1;
		gbc.gridwidth	= 1;
		gbc.insets		= new Insets(5, 5, 5, 5);
		add(pnameLegend, gbc);

		gbc.gridx 		= 5;
		gbc.gridy 		= 1;
		gbc.gridheight 	= 1;
		gbc.gridwidth	= 1;
		gbc.insets		= new Insets(5, 5, 5, 5);
		add(pnameField, gbc);


		gbc.gridx 		= 6;
		gbc.gridy 		= 1;
		gbc.gridheight 	= 1;
		gbc.gridwidth	= 1;
		gbc.insets		= new Insets(5, 5, 5, 5);
		add(confirmation, gbc);
	}

	public void setData(Object[] data)
	{
		//roomNumber.setText(data[0].toString());
		nameField.setText(data[1].toString());
		pnameField.setText(data[2].toString());
	}

	public JButton getSubmitBtn() { return this.confirmation; }
	public int getRoomNumber()	  { return (int) this.spin.getValue(); }

	public void setRooms(Object[] data)
	{
		Integer[] d =  (Integer[]) data;

		for(int i = 0; i < d.length; i++)
			spin.setValue(d[i].intValue());
	}
}
package views.accueil;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class JReservation extends JPanel
{
	private JLabel 		roomNumber;
	private JLabel		nameLegend;
	private JLabel		pnameLegend;
	private JTextField	nameField;
	private JTextField	pnameField;
	private JButton		confirmation;
	private JButton 	modification;

	public static final String CONFIRMATION = "Confirmer";
	public static final String MODIFICATION = "Modifier";

	private GridBagConstraints gbc;

	public JReservation()
	{
		super();
		roomNumber 			= new JLabel();
		nameLegend			= new JLabel("Nom");
		pnameLegend 		= new JLabel("Prénom");
		nameField			= new JTextField(10);
		pnameField			= new JTextField(10);
		confirmation		= new JButton(JReservation.CONFIRMATION);
		modification 		= new JButton(JReservation.MODIFICATION);
		gbc 				= new GridBagConstraints();

		Border b = BorderFactory.createLineBorder(Color.BLACK, 1);
		setBorder(b);
		setLayout(new GridBagLayout());

		initUI();
	}

	private void initUI()
	{
		/* Mise en place du label */
		roomNumber.setFont(roomNumber.getFont().deriveFont(64.0f));
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
		roomNumber.setText(data[0].toString());
		nameField.setText(data[1].toString());
		pnameField.setText(data[2].toString());
	}

	public JButton getSubmitBtn() { return this.confirmation; }
}
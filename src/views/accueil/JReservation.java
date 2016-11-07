package views.accueil;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

import java.util.*;

public class JReservation extends JFrame
{
	private JPanel root;
	private JPanel 		roomNumber;
	private JLabel		nameLegend;
	private JLabel		pnameLegend;
	private JTextField	nameField;
	private JTextField	pnameField;
	private JButton		confirmation;
	private JButton 	modification;
	private JSpinner	spin;

	public final String APP_NAME = "Réservation";

	public static final String CONFIRMATION = "Confirmer";
	public static final String MODIFICATION = "Modifier";

	private GridBagConstraints gbc;

	public JReservation()
	{
		super("Réservation");
		setSize(400, 250);
		setLocationRelativeTo(null);

		root 				= new JPanel();
		roomNumber 			= new JPanel();
		nameLegend			= new JLabel("Nom");
		pnameLegend 		= new JLabel("Prénom");
		nameField			= new JTextField(20);
		pnameField			= new JTextField(20);
		confirmation		= new JButton(JReservation.CONFIRMATION);
		modification 		= new JButton(JReservation.MODIFICATION);
		gbc 				= new GridBagConstraints();

		SpinnerModel s = new SpinnerNumberModel(0, 0, 0, 1);
		spin = new JSpinner(s);
		spin.setPreferredSize(new Dimension(100, 40));

		Font f = ((JSpinner.NumberEditor) spin.getEditor()).getTextField().getFont();

		((JSpinner.NumberEditor) spin.getEditor()).getTextField().setFont(f.deriveFont(45.0f));

		roomNumber.add(spin);
		root.setLayout(new GridBagLayout());

		initUI();
	}

	private void initUI()
	{
		gbc.gridx 		= 1;
		gbc.gridy 		= 1;
		gbc.gridheight 	= 1;
		gbc.gridwidth	= 1;
		gbc.anchor 		= GridBagConstraints.CENTER;
		gbc.insets		= new Insets(5, 5, 5, 5);
		root.add(roomNumber, gbc);

		gbc.gridx 		= 2;
		gbc.gridy 		= 1;
		gbc.gridheight 	= 1;
		gbc.gridwidth	= 1;
		gbc.insets		= new Insets(5, 5, 5, 5);
		root.add(nameLegend, gbc);

		gbc.gridx 		= 3;
		gbc.gridy 		= 1;
		gbc.gridheight 	= 1;
		gbc.gridwidth	= 1;
		gbc.insets		= new Insets(5, 5, 5, 5);
		root.add(nameField, gbc);

		gbc.gridx 		= 4;
		gbc.gridy 		= 1;
		gbc.gridheight 	= 1;
		gbc.gridwidth	= 1;
		gbc.insets		= new Insets(5, 5, 5, 5);
		root.add(pnameLegend, gbc);

		gbc.gridx 		= 5;
		gbc.gridy 		= 1;
		gbc.gridheight 	= 1;
		gbc.gridwidth	= 1;
		gbc.insets		= new Insets(5, 5, 5, 5);
		root.add(pnameField, gbc);


		gbc.gridx 		= 6;
		gbc.gridy 		= 1;
		gbc.gridheight 	= 1;
		gbc.gridwidth	= 1;
		gbc.insets		= new Insets(5, 5, 5, 5);
		root.add(confirmation, gbc);

		add(root);
	}

	public void setData(String[] data)
	{
		System.out.println(data[0]);
		nameField.setText(data[0]);
		pnameField.setText(data[1]);
		this.revalidate();
	}
}
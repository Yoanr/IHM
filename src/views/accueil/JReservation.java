package views.accueil;

import models.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.util.*;

public class JReservation extends JFrame
{
	private JPanel 		root;
	private JPanel 		roomNumber;
	private JLabel		nameLegend;
	private JLabel		pnameLegend;
	private JTextField	nameField;
	private JTextField	pnameField;
	private JButton		confirmation;
	private JButton 	modification;

	private int 		idReservation;

	private JComboBox<Integer> selector;
	private ArrayList<Object> data;

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
		nameField			= new JTextField();
		pnameField			= new JTextField();
		confirmation		= new JButton(JReservation.CONFIRMATION);
		modification 		= new JButton(JReservation.MODIFICATION);
		gbc 				= new GridBagConstraints();

		selector 			= new JComboBox<Integer>();

		roomNumber.add(selector);

		confirmation.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				int i = (int) selector.getSelectedItem();
				System.out.println(data.get(0));
				System.out.println(idReservation);

				AccuelModel am = AccuelModel.getInstance();
				am.confirmReservation(i, idReservation);
			}
		});

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

		gbc.gridx 		= 1;
		gbc.gridy 		= 2;
		gbc.gridheight 	= 1;
		gbc.gridwidth	= 1;
		gbc.anchor 		= GridBagConstraints.CENTER;
		gbc.insets		= new Insets(5, 5, 5, 5);
		root.add(nameLegend, gbc);

		gbc.gridx 		= 2;
		gbc.gridy 		= 2;
		gbc.gridheight 	= 1;
		gbc.gridwidth	= 1;
		gbc.anchor 		= GridBagConstraints.LINE_END;
		gbc.insets		= new Insets(5, 5, 5, 5);
		root.add(nameField, gbc);

		gbc.gridx 		= 1;
		gbc.gridy 		= 3;
		gbc.gridheight 	= 1;
		gbc.gridwidth	= 1;
		gbc.anchor 		= GridBagConstraints.CENTER;
		gbc.insets		= new Insets(5, 5, 5, 5);
		root.add(pnameLegend, gbc);

		gbc.gridx 		= 2;
		gbc.gridy 		= 3;
		gbc.gridheight 	= 1;
		gbc.gridwidth	= 1;
		gbc.anchor 		= GridBagConstraints.LINE_END;
		gbc.insets		= new Insets(5, 5, 5, 5);
		root.add(pnameField, gbc);


		gbc.gridx 		= 1;
		gbc.gridy 		= 4;
		gbc.gridheight 	= 1;
		gbc.gridwidth	= 1;
		gbc.insets		= new Insets(5, 5, 5, 5);
		root.add(confirmation, gbc);

		add(root);
		pack();
	}

	public void setData(Object[] data)
	{
		AccuelModel am = AccuelModel.getInstance();

		String s = String.valueOf(data[0]);
		int l = Integer.parseInt(s);

		idReservation = l;

		this.data = am.getReservationByID(l);

		System.out.println(this.data);

		nameField.setText(String.valueOf(this.data.get(0)));
		pnameField.setText(String.valueOf(this.data.get(1)));

		ArrayList<Integer> p = am.getAvailablesRooms(Integer.parseInt(String.valueOf(this.data.get(4))));

		for(int i = 0; i < p.size(); i++)
			selector.addItem(p.get(i));

		if(p.size() > 0)
			selector.setSelectedIndex(0);

		this.revalidate();
		pack();
	}
}

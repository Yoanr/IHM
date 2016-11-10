package views.accueil;

import models.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
 * Classe JReservation
 * @author Martin Barreau
 * Classe permettant d'afficher une réservation et de la confirmer avec des appels de DB
 */

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

	private JLabel		categorieLegend;
	private JTextField	categorieField;

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
		categorieLegend		= new JLabel("Catégorie");
		nameField			= new JTextField();
		pnameField			= new JTextField();
		categorieField		= new JTextField();
		confirmation		= new JButton(JReservation.CONFIRMATION);
		modification 		= new JButton(JReservation.MODIFICATION);
		gbc 				= new GridBagConstraints();

		selector 			= new JComboBox<Integer>();

		roomNumber.add(selector);

		confirmation.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				if(selector.getSelectedItem() == null)
				{
					JOptionPane.showMessageDialog(null, "Veuillez sélectionner une chambre, pour confirmer la réservation !", "Confirmation de réservation", JOptionPane.ERROR_MESSAGE);
					return;
				}
				int i = (int) selector.getSelectedItem();
				System.out.println(data.get(0));
				System.out.println(selector.getSelectedItem());

				AccueilModel am = AccueilModel.getInstance();
				boolean b = false;

				b = am.confirmReservation(i, idReservation);

				if(b)
				{
					JOptionPane.showMessageDialog(null, "La réservation a bien été enregistrée !", "Confirmation de réservation", JOptionPane.INFORMATION_MESSAGE);
					dispose();
				}
				else
					JOptionPane.showMessageDialog(null, "Un problème interne a annulée la confirmation, veuillez réessayer plus tard", "Confirmation de réservation", JOptionPane.ERROR_MESSAGE);
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
		gbc.anchor 		= GridBagConstraints.CENTER;
		gbc.insets		= new Insets(5, 5, 5, 5);
		root.add(categorieLegend, gbc);

		gbc.gridx 		= 2;
		gbc.gridy 		= 4;
		gbc.gridheight 	= 1;
		gbc.gridwidth	= 1;
		gbc.anchor 		= GridBagConstraints.CENTER;
		gbc.insets		= new Insets(5, 5, 5, 5);
		root.add(categorieField, gbc);

		gbc.gridx 		= 1;
		gbc.gridy 		= 5;
		gbc.gridheight 	= 1;
		gbc.gridwidth	= 1;
		gbc.insets		= new Insets(5, 5, 5, 5);
		root.add(confirmation, gbc);

		add(root);
		//pack();
	}

	/**
	 * @param Object[] Objet contenant les données pour "nourrir" la vue
	 * BUG: CastExceptions lancées durant l'extraction
	 * HACK: Ce setter peut lancer des exceptions de transtypage
	 * ce problème est principalement dû au fait que l'extraction des données
	 * ce font sur des indexs spécifiques, ne pas oublier à bien former le tableau
	 */
	public void setData(Object[] data)
	{
		AccueilModel am = AccueilModel.getInstance();

		String s = String.valueOf(data[0]);
		int l = Integer.parseInt(s);

		idReservation = l;

		this.data = am.getReservationByID(l);

		System.out.println(this.data);

		nameField.setText(String.valueOf(this.data.get(0)));
		pnameField.setText(String.valueOf(this.data.get(1)));
		categorieField.setText(String.valueOf(this.data.get(this.data.size() - 1)));

		ArrayList<Integer> p = am.getAvailablesRooms(Integer.parseInt(String.valueOf(this.data.get(4))));

		for(int i = 0; i < p.size(); i++)
			selector.addItem(p.get(i));

		if(p.size() > 0)
			selector.setSelectedIndex(0);

		this.revalidate();
		pack();
	}
}

package views.accueil;

/* Customs imports */
import controllers.*;

/* Java  imports */
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

public class SearchView extends JPanel
{
	private String APP_NAME 			= "Recherche de réservations";
	private final String SEARCH_HINT 	= "Rechercher client par nom ...";
	private final String SEARCH_BTN_TXT	= "Rechercher";

	private final String[] METADATA_TAB = { "Numéro", "Nom", "Prénom" };

	/*private JPanel 		mainContainer;*/
	private JTextField 	searchField;
	private JButton 	searchButton;
	private DefaultTableModel dtm;
	private JTable resultTab;
	private JScrollPane resultView;

	public SearchView()
	{
		JLabel lbl				= new JLabel(APP_NAME);
		JPanel searchPan 		= new JPanel();
		JPanel mainContainer	= new JPanel();
		GridBagConstraints gbc 	= new GridBagConstraints();
		searchField 			= new JTextField(this.SEARCH_HINT);
		searchButton 			= new JButton(this.SEARCH_BTN_TXT);

		dtm 					= new DefaultTableModel(METADATA_TAB, 0); // By default no data
		resultTab				= new JTable(this.dtm);
		resultView				= new JScrollPane(this.resultTab);


		searchPan.setLayout(new GridBagLayout());

		/* Mise en place du label */
		gbc.gridx 		= 1;
		gbc.gridy 		= 1;
		gbc.gridheight 	= 1;
		gbc.gridwidth	= 1;
		gbc.insets		= new Insets(5, 5, 5, 5);

		searchPan.add(lbl, gbc);

		/* Mise en place du champ de recherche */
		gbc.gridx 		= 1;
		gbc.gridy 		= 2;
		gbc.gridheight 	= 1;
		gbc.gridwidth	= 1;
		gbc.anchor		= GridBagConstraints.CENTER;
		gbc.insets		= new Insets(5, 5, 5, 5);

		searchPan.add(searchField, gbc);

		/* Mise en place du bouton de submit */
		gbc.gridx 		= 2;
		gbc.gridy 		= 2;
		gbc.gridheight 	= 1;
		gbc.gridwidth	= 1;
		gbc.anchor		= GridBagConstraints.WEST;
		gbc.insets		= new Insets(5, 5, 5, 5);

		searchPan.add(searchButton, gbc);

		/* Mise en place du tableau de réservations */
		gbc.gridx 		= 2;
		gbc.gridy 		= 3;
		gbc.gridheight 	= 1;
		gbc.gridwidth	= 1;
		gbc.anchor		= GridBagConstraints.CENTER;
		gbc.insets		= new Insets(5, 5, 5, 5);

		searchPan.add(resultView, gbc);

		//resultView.setVisible(false);

		/* Ajout du panneau de recherche sur le panneau "root" */
		add(searchPan);

	}
}
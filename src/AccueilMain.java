import views.*;
import models.*;

import javax.swing.JOptionPane;
import java.text.*;

/**
 * Classe MainNettoyage
 * @author Martin Barreau
 * Classe Main pour l'application de l'accueil
 */

public class AccueilMain
{
	public static void main(String[] args)
	{

		if(args.length == 1)
		{
			if(args[0].equals("tester") || args[0].equals("-tester") || args[0].equals("--tester"))
			{
				boolean validDate = false;
				DateFormat inputFormat = new SimpleDateFormat("YYYY-MM-dd");
				String dateSelected = "";

				while(!validDate)
				{
					dateSelected = JOptionPane.showInputDialog("Choisissez une date (format: aaaa-mm-dd): ");
					try
					{
						inputFormat.parse(dateSelected.trim());
						validDate = true;
					}catch(ParseException pe)
					{
						validDate = false;
					}
					catch(NullPointerException npe)
					{
						System.exit(0);
					}
				}

				AccueilModel am = AccueilModel.getInstance();

				am.setStrDate(dateSelected);
			}
			else if(args[0].equals("help") || args[0].equals("-h") || args[0].equals("--helper"))
			{
				System.out.println("Options possibles: tester, -tester, --tester");
				System.exit(0);
			}
			else
			{
				System.err.println("Option invalide: utiliser l'option -h pour plus d'aide");
				System.exit(-1);
			}
		}

		(new AccueilView()).setVisible(true);
	}
}

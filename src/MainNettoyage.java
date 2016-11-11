import views.*;
import models.*;

import javax.swing.JOptionPane;
import java.text.*;

/**
 * Classe MainNettoyage
 * @author Yoan ROCK
 * Classe Main de cette appli
 */

public class MainNettoyage {
	public static void main(String[] args)
	{

NettoyageModel am = NettoyageModel.getInstance();
am.setStrDate("");
		if(args.length == 1)
		{
			if(args[0].equals("tester") || args[0].equals("-tester") || args[0].equals("--tester"))
			{
				boolean validDate = false;
				DateFormat inputFormat = new SimpleDateFormat("yyyyMMdd");
				String dateSelected = "";

				while(!validDate)
				{
					dateSelected = JOptionPane.showInputDialog("Choisissez une date (format: YYYYMMdd): ");
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
		(new NettoyageView()).setVisible(true);
	}
}
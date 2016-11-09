import views.*;
import models.*;

import javax.swing.JOptionPane;
import java.text.*;

public class AccueilTester
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
					System.out.println(dateSelected);
				}

				AccueilModel am = AccueilModel.getInstance();

				am.setStrDate(dateSelected);
			}
			if(args[0].equals("help") || args[0].equals("-h") || args[0].equals("--helper"))
			{
				System.out.println("Options possibles: tester, -tester, --tester");
				System.exit(0);
			}
		}
		

		(new AccueilView()).setVisible(true);
	}
}
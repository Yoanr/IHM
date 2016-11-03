package controllers;

import views.accueil.*;

import java.awt.event.*;

public class SearchController implements ActionListener
{
	private SearchView sv;

	public SearchController(SearchView sv)
	{
		this.sv = sv;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getActionCommand().equals(this.sv.SEARCH_BTN_TXT))
		{
			System.out.println(this.sv.getText());
			this.sv.getResultView().setVisible(true);
			this.sv.revalidate(); // Needed to make changes visibles on the JPanel
			this.sv.refresh();
		}
	}
}
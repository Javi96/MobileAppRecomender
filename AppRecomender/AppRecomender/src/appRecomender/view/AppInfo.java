package appRecomender.view;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import appRecomender.Utilities;
import appRecomender.model.App;

public class AppInfo extends JPanel implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AppInfo(ArrayList<App> apps) {
		JTextPane textPane = new JTextPane();
		Utilities.generateTextPane(textPane,
				"List of recomended aplications: ", 26, Color.black);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(textPane);
	}

	@SuppressWarnings("unchecked")
	public void update(Observable arg0, Object arg1) {
		removeAll();
		ArrayList<App> apps = (ArrayList<App>) arg1;
		this.setBackground(Color.white);
		JTextPane textPane = new JTextPane();
		Utilities.generateTextPane(textPane,
				"List of recomended aplications:    ", 26, Color.black);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(textPane);
		for (App app : apps) {
			textPane = new JTextPane();
			Utilities.generateTextPane(textPane, "Name: " + app.getName()
					+ "\n" + "Category: " + app.getCategoryList() + "\n"
					+ "Prize: " + app.getPrize() + "$\n", 20, Color.black);
			this.add(textPane);
		}
		validate();
		repaint();
	}
}

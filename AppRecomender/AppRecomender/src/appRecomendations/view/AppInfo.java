package appRecomendations.view;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import appRecomendations.Utilities;
import appRecomendations.model.App;

public class AppInfo extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AppInfo(ArrayList<App> apps) {
		this.setBackground(Color.white);
		JTextPane textPane = new JTextPane();
		Utilities.generateTextPane(textPane, "List of recomended aplications:         					 ",
				26, Color.black);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(textPane);
		for (App app : apps) {
			textPane = new JTextPane();
			Utilities.generateTextPane(textPane, app.getName() + " -- " + app.getCategoryList() + " -- " + app.getPrize(),
					20, Color.black);
			this.add(textPane);
		}
	}

	public static void main(String[] args) {
		ArrayList<App> apps = new ArrayList<App>();
		apps.add(new App("app1", 1.1f, "cat 1"));
		apps.add(new App("app2", 1.2f, "cat 2"));
		apps.add(new App("app3", 1.3f, "cat 3"));
		apps.add(new App("app4", 1.4f, "cat 4"));

		AppInfo app = new AppInfo(apps);
		app.setPreferredSize(new Dimension(500, 500));

		JFrame frame = new JFrame();
		frame.pack();
		frame.add(app);
		frame.setVisible(true);

	}
}

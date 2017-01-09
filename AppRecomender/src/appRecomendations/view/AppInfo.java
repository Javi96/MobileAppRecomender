package appRecomendations.view;

import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import appRecomendations.model.App;

public class AppInfo extends JPanel{
	
	public AppInfo(ArrayList<App> apps) {
		this.add(new JTextArea("Main apps"));
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		for(App app : apps){
			this.add(new JTextArea(app.getName() + app.getCategoryList() + app.getPrize()));
		}
	}
}

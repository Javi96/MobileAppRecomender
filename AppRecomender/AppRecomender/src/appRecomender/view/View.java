package appRecomender.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import jess.JessException;
import appRecomender.DataBase;
import appRecomender.Engine;
import appRecomender.Utilities;
import appRecomender.controller.Controller;
import appRecomender.model.App;

public class View extends JFrame implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Controller ctrl;
	private JScrollPane gamePanelScroll;
	private JPanel mainPanel;
	private JPanel recomendedGamesPanel;
	private AppInfo appsInfo;
	private AddUser addUser;
	private ArrayList<App> apps;
	private JTextPane recomendedGamesTitle;


	public void setController(Controller cont) {
		this.ctrl = cont;
	}

	public static void main(String[] args) {
		try {
			new Controller(new Engine(new DataBase()), new View());
		} catch (JessException e) {
			e.printStackTrace();
		}
	}


	public void start(Controller controller) {
		this.ctrl = controller;
		this.setPreferredSize(new Dimension(800, 800));
		this.setLocationRelativeTo(null);
		this.setBackground(Color.WHITE);
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(MAXIMIZED_BOTH);

		this.recomendedGamesPanel = new JPanel(new GridLayout(8, 4));
		this.recomendedGamesPanel.setBorder(null);
		this.recomendedGamesPanel.setBackground(Color.WHITE);
		
		this.mainPanel = new JPanel();
		this.mainPanel
				.setLayout(new BoxLayout(this.mainPanel, BoxLayout.Y_AXIS));
		this.mainPanel.setBackground(Color.WHITE);

		this.recomendedGamesTitle = new JTextPane();
		this.recomendedGamesTitle.setPreferredSize(new Dimension(648, 50));
		Utilities.generateTextPane(this.recomendedGamesTitle,
				"Recomendados para tí: ", 26, Color.black);

		this.addUser = new AddUser(ctrl.getUser(), controller);
		
		
		JPanel verticalPanel = new JPanel();
		verticalPanel.setLayout(new BoxLayout(verticalPanel, BoxLayout.Y_AXIS));

		this.gamePanelScroll = new JScrollPane(this.recomendedGamesPanel,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.gamePanelScroll.getVerticalScrollBar().setUnitIncrement(16);
		verticalPanel.add(recomendedGamesTitle);
		verticalPanel.add(gamePanelScroll);
		verticalPanel.add(addUser);

		JPanel horizontPanel = new JPanel();
		horizontPanel.setLayout(new BoxLayout(horizontPanel, BoxLayout.X_AXIS));

		this.appsInfo = new AppInfo(apps);
		controller.add(appsInfo);
		
		JScrollPane appsInfoScroll = new JScrollPane(this.appsInfo,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		appsInfoScroll.getVerticalScrollBar().setUnitIncrement(16);

		horizontPanel.add(appsInfoScroll);
		horizontPanel.add(verticalPanel);

		this.mainPanel.add(horizontPanel);
		ctrl.getRecom();
		this.add(this.mainPanel);
		this.setVisible(true);
	}

	public void changePanel(ArrayList<App> apps){
		class Label extends JLabel {

			private static final long serialVersionUID = 1L;

			public Label(App app) {
				super(Utilities
						.resizeImage("src/appRecomender/model/images/"
								+ app.getName() + ".png"));
			}
		}
		
		for (final App a : apps) {

			JPanel panel = new JPanel();
			panel.setBackground(Color.white);
			panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
			Label label = new Label(a);
			label.addMouseListener(new MouseListener() {

				public void mouseReleased(MouseEvent arg0) {
				}

				public void mousePressed(MouseEvent arg0) {
				}

				public void mouseExited(MouseEvent arg0) {
				}

				public void mouseEntered(MouseEvent arg0) {
				}

				public void mouseClicked(MouseEvent arg0) {
					ctrl.act(a);
					ctrl.getRecom();
				
				}
				
			});
			JPanel aux = new JPanel();
			aux.setLayout(new BoxLayout(aux, BoxLayout.X_AXIS));
			aux.add(Box.createRigidArea(new Dimension(10, 1)));
			aux.setBackground(Color.white);
			aux.add(label);
			panel.add(aux);
			String name;
			if (a.getName().length() <= 15) {
				name = a.getName();
			} else {
				name = "   " + a.getName().substring(0, 15) + "...";

			}

			JTextField text = new JTextField(name);
			text.setPreferredSize(new Dimension(10, 20));
			text.setBorder(null);
			text.setBackground(null);
			text.setEditable(false);
			text.setFont(new Font("Monospaced", Font.PLAIN, 14));
			text.setHorizontalAlignment(JTextField.CENTER);
			panel.add(text);

			this.recomendedGamesPanel.add(panel);
		}
	}
	@SuppressWarnings("unchecked")
	public void update(Observable arg0, Object arg1) {
		recomendedGamesPanel.removeAll();
		changePanel((ArrayList<App>)arg1);
		recomendedGamesPanel.validate();
		recomendedGamesPanel.repaint();
		repaint();
		
	}
}
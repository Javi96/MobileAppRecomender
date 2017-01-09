package appRecomendations.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import jess.JessException;
import appRecomendations.DataBase;
import appRecomendations.Engine;
import appRecomendations.Utilities;
import appRecomendations.controller.Controller;
import appRecomendations.model.App;

public class View extends JFrame implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Controller ctrl;

	private JScrollPane mainScroll;
	private JScrollPane recomendedGames;
	private JScrollPane recomendedByCategory;

	private JPanel mainPanel;
	private JPanel recomendedGamesPanel;
	private JPanel recomendedByCategoryPanel;
	private AppInfo appsInfo;
	private AddUser addUser;

	private ArrayList<App> apps;

	public View() {
	}

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

	// -------------------------

	private JTextPane recomendedGamesTitle;

	public void start(Controller controller) {
		this.ctrl = controller;
		this.setPreferredSize(new Dimension(800, 800));
		this.setLocationRelativeTo(null);
		this.setBackground(Color.WHITE);
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(MAXIMIZED_BOTH);

		this.recomendedGamesPanel = new JPanel(new GridLayout(4, 5));
		this.recomendedGamesPanel.setBorder(null);
		this.recomendedGamesPanel.setBackground(Color.WHITE);
		this.recomendedGamesPanel.setPreferredSize(new Dimension(648, 648));

		class Label extends JLabel {

			private static final long serialVersionUID = 1L;

			public Label(App app) {
				super(Utilities
						.resizeImage("src/appRecomendations/model/images/"
								+ app.getName() + ".png"));
			}
		}

		this.apps = ctrl.getRecom();
		for (final App a : apps) {
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

				}
			});
			this.recomendedGamesPanel.add(label);
		}

		this.recomendedGames = new JScrollPane(this.recomendedGamesPanel,
				JScrollPane.VERTICAL_SCROLLBAR_NEVER,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		this.recomendedByCategoryPanel = new JPanel();
		this.recomendedByCategory = new JScrollPane(
				this.recomendedByCategoryPanel,
				JScrollPane.VERTICAL_SCROLLBAR_NEVER,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		this.mainPanel = new JPanel();
		this.mainPanel
				.setLayout(new BoxLayout(this.mainPanel, BoxLayout.Y_AXIS));
		this.mainPanel.setBackground(Color.WHITE);

		this.recomendedGamesTitle = new JTextPane();
		this.recomendedGamesTitle.setPreferredSize(new Dimension(648, 20));
		Utilities.generateTextPane(this.recomendedGamesTitle,
				"Recomendados para t�: ", 26, Color.black);

		this.addUser = new AddUser(ctrl.getUser(), controller);

		JPanel verticalPanel = new JPanel();
		verticalPanel.setLayout(new BoxLayout(verticalPanel, BoxLayout.Y_AXIS));

		verticalPanel.add(this.recomendedGamesTitle);
		verticalPanel.add(recomendedGames);
		verticalPanel.add(addUser);

		JPanel horizontPanel = new JPanel();
		horizontPanel.setLayout(new BoxLayout(horizontPanel, BoxLayout.X_AXIS));

		this.appsInfo = new AppInfo(apps);
		JScrollPane appsInfoScroll = new JScrollPane(this.appsInfo,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		horizontPanel.add(appsInfoScroll);
		horizontPanel.add(verticalPanel);

		this.mainPanel.add(horizontPanel);

		this.mainScroll = new JScrollPane(this.mainPanel,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		this.add(this.mainScroll);
		this.setVisible(true);
	}

	public void update(Observable arg0, Object arg1) {

	}
}
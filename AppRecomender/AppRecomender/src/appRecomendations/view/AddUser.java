package appRecomendations.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

import appRecomendations.Utilities;
import appRecomendations.controller.Controller;
import appRecomendations.model.User;

public class AddUser extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTextArea name;
	
	private JTextPane currentUser;
	
	private Controller controller;
	
	public AddUser(User user, final Controller controller) {
		this.controller = controller;
		this.setBackground(Color.white);
		currentUser = new JTextPane();
		Utilities.generateTextPane(currentUser, "Current user: " + user.getName(), 26,
				Color.black);

		JPanel horizontPanel = new JPanel();
		horizontPanel.setLayout(new BoxLayout(horizontPanel, BoxLayout.X_AXIS));
		horizontPanel.add(currentUser);
		JButton changeUser = new JButton("Change User");
		changeUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Change user");
				
				controller.changeUser(new User(name.getText(), 0, '_', "__"));
				name.setText("");
				reloadView();
			}
		});

		JButton newUser = new JButton("New User");
		newUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("New user");
			}
		});
		
		horizontPanel.add(changeUser);
		horizontPanel.add(Box.createRigidArea(new Dimension(50, 0)));
		horizontPanel.add(newUser);
		horizontPanel.add(Box.createRigidArea(new Dimension(50, 0)));

		this.add(horizontPanel);

		JTextPane textPane = new JTextPane();
		Utilities.generateTextPane(textPane, "New user: ", 26, Color.black);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(textPane);

		textPane = new JTextPane();
		Utilities.generateTextPane(textPane, "Name", 20, Color.black);
		this.add(textPane);

		name = new JTextArea();
		setImput(name);
		this.add(name);

		textPane = new JTextPane();
		Utilities.generateTextPane(textPane, "Year of birth", 20, Color.black);
		this.add(textPane);

		JTextArea imput = new JTextArea();
		setImput(imput);
		this.add(imput);

		textPane = new JTextPane();
		Utilities.generateTextPane(textPane, "Sex (m or f)", 20, Color.black);
		this.add(textPane);

		imput = new JTextArea();
		setImput(imput);
		this.add(imput);

		textPane = new JTextPane();
		Utilities.generateTextPane(textPane, "Country", 20, Color.black);
		this.add(textPane);

		imput = new JTextArea();
		setImput(imput);
		this.add(imput);

	}
	
	public void reloadView(){
		this.currentUser.setText("Current user: " + controller.getUser().getName());
		this.repaint();
	}

	private void setImput(JTextArea textArea) {
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 16));
		textArea.setBackground(Color.LIGHT_GRAY);
		textArea.setBorder(BorderFactory.createEtchedBorder());
	}

	public static void main(String[] args) {
		AddUser app = new AddUser(new User("Pedro", 1995, 'm', "Spain"), null);
		app.setPreferredSize(new Dimension(500, 500));

		JFrame frame = new JFrame();
		frame.pack();
		frame.add(app);
		frame.setVisible(true);

	}
}
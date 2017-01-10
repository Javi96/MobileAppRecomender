package appRecomender.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

import appRecomender.Utilities;
import appRecomender.controller.Controller;
import appRecomender.model.User;

public class AddUser extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTextArea name;
	private JTextArea sex;
	private JTextArea country;
	private JTextArea yearBirth;

	private JTextPane currentUser;
	private Controller controller;

	public AddUser(User user, final Controller controller) {
		this.controller = controller;
		this.setBackground(Color.white);
		currentUser = new JTextPane();
		Utilities.generateTextPane(currentUser,
				"Current user: " + user.getName(), 26, Color.black);
		JPanel horizontPanel = new JPanel();
		horizontPanel.setLayout(new BoxLayout(horizontPanel, BoxLayout.X_AXIS));
		horizontPanel.add(currentUser);
		JButton changeUser = new JButton("Change User");
		changeUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.changeUser(new User(name.getText(), 0, '_', "__"));
				name.setText("");
				reloadView();
			}
		});
		JButton newUser = new JButton("New User");
		newUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (name.getText() == "" || country.getText() == ""
						|| yearBirth.getText() == "" || sex.getText() == "") {
					JOptionPane.showMessageDialog(null,  "Invalid data", null, JOptionPane.ERROR_MESSAGE);
				}
				else{
					User user= parserUser(name.getText(), yearBirth.getText(), sex.getText(), country.getText());
					if(user==null){
						JOptionPane.showMessageDialog(null,  "Invalid data", null, JOptionPane.ERROR_MESSAGE);
					}else{
						controller.addUser(user);
					}
				}
				name.setText("");
				country.setText("");
				yearBirth.setText("");
				sex.setText("");
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

		yearBirth = new JTextArea();
		setImput(yearBirth);
		this.add(yearBirth);

		textPane = new JTextPane();
		Utilities.generateTextPane(textPane, "Sex (m or f)", 20, Color.black);
		this.add(textPane);

		sex = new JTextArea();
		setImput(sex);
		this.add(sex);

		textPane = new JTextPane();
		Utilities.generateTextPane(textPane, "Country", 20, Color.black);
		this.add(textPane);

		country = new JTextArea();
		setImput(country);
		this.add(country);

	}

	User parserUser(String name, String birthYear, String sex, String country){
		int age=0;
		try{
			age = Integer.parseInt(birthYear);
		}catch(Exception e){
			return null;
		}
		if(age<1920 && age>=2017){
			return null;
		}
		if(sex.equals("m") && sex.equals("f")){
			return null;
		}
		return new User(name, age, sex.charAt(0), country);
	}
	public void reloadView() {
		this.currentUser.setText("Current user: "
				+ controller.getUser().getName());
		this.repaint();
	}

	private void setImput(JTextArea textArea) {
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 16));
		textArea.setBackground(Color.LIGHT_GRAY);
		textArea.setBorder(BorderFactory.createEtchedBorder());
	}
}
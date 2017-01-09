package appRecomendations.view;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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

	public View() {
	}
	public void start() {
		ArrayList<ImageIcon> imageIconList = ctrl.getImageIconList();
		
		this.setPreferredSize(new Dimension(500, 500));
		this.setLocationRelativeTo(null);
		this.setBackground(Color.WHITE);
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		this.recomendedGamesPanel = new JPanel(new GridLayout(1, imageIconList.size()));
		this.recomendedGamesPanel.setBorder(null);
		this.recomendedGamesPanel.setBackground(Color.WHITE);
		this.recomendedGamesPanel.setPreferredSize(new Dimension(648, 215));
		
		JLabel label;
		for(ImageIcon image : imageIconList){
			label = new JLabel(image);
			label.addMouseListener(new MouseListener() {
				
				public void mouseReleased(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}
				
				public void mousePressed(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}
				
				public void mouseExited(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}
				
				public void mouseEntered(MouseEvent arg0) {
					
				}
				
				public void mouseClicked(MouseEvent arg0) {
					//ctrl.act();
					
				}
			});
			this.recomendedGamesPanel.add(label);
		}
		
		this.recomendedGames = new JScrollPane(this.recomendedGamesPanel, JScrollPane.VERTICAL_SCROLLBAR_NEVER,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		this.recomendedByCategoryPanel = new JPanel();
		this.recomendedByCategory = new JScrollPane(this.recomendedByCategoryPanel, JScrollPane.VERTICAL_SCROLLBAR_NEVER,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		this.mainPanel = new JPanel();
		this.mainPanel.setBackground(Color.WHITE);
		this.mainPanel.add(this.recomendedGames);
		//this.mainPanel.add(this.recomendedByCategory);
		this.mainScroll = new JScrollPane(this.mainPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		this.add(this.mainScroll);
		this.setVisible(true);
	}
	
	public void setCtrl(Controller cont){
		this.ctrl = cont;		
	}
	
	public static void main(String[] args) {
		try {
			new Controller(new Engine(new DataBase()), new View());
		} catch (JessException e) {
			e.printStackTrace();
		}
	}
	
	//-------------------------
	
	private JTextPane recomendedGamesTitle;
	
	public void start2(Controller controller) {
		this.ctrl = controller;
		this.setPreferredSize(new Dimension(500, 500));
		this.setLocationRelativeTo(null);
		this.setBackground(Color.WHITE);
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		this.recomendedGamesPanel = new JPanel(new GridLayout(2, 4));
		this.recomendedGamesPanel.setBorder(null);
		this.recomendedGamesPanel.setBackground(Color.WHITE);
		this.recomendedGamesPanel.setPreferredSize(new Dimension(648, 215));
		
		class Label extends JLabel {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			App app;
			public Label(App app) {
				
				super(Utilities.resizeImage("src/appRecomendations/model/images/"
						+ app.getName() + ".png"));
				System.out.println("jajajajaja");
				this.app = app;
			}			
		}
		for(final App a: ctrl.getRecom()){
			//final App appAux = a;
			System.out.println("jojojojo");
			Label label = new Label(a);	
			label.addMouseListener(new MouseListener() {
				
				public void mouseReleased(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}
				
				public void mousePressed(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}
				
				public void mouseExited(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}
				
				public void mouseEntered(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}
				
				public void mouseClicked(MouseEvent arg0) {
					System.err.println("OK");
					ctrl.act(a);
					
				}
			});
			this.recomendedGamesPanel.add(label);
		}
		
		this.recomendedGames = new JScrollPane(this.recomendedGamesPanel, JScrollPane.VERTICAL_SCROLLBAR_NEVER,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		this.recomendedByCategoryPanel = new JPanel();
		this.recomendedByCategory = new JScrollPane(this.recomendedByCategoryPanel, JScrollPane.VERTICAL_SCROLLBAR_NEVER,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		this.mainPanel = new JPanel();
		this.mainPanel.setLayout(new BoxLayout(this.mainPanel, BoxLayout.Y_AXIS));
		this.mainPanel.setBackground(Color.WHITE);
		this.recomendedGamesTitle = new JTextPane();
		this.recomendedGamesTitle.setPreferredSize(new Dimension(648,50));
		this.generateTextPane(this.recomendedGamesTitle, "Recomendados para tí <3: ", 45, Color.blue);
		this.mainPanel.add(this.recomendedGamesTitle);
		this.mainPanel.add(this.recomendedGames);
		this.mainScroll = new JScrollPane(this.mainPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.add(this.mainScroll);
		this.setVisible(true);
	}
	
	private void generateTextPane(JTextPane component, String constant, int fontSize, Color fontColor) {
		component.setText(constant);
		component.setBorder(null);
		component.setFont(new Font("Arial", Font.PLAIN, fontSize));
		component.setForeground(fontColor);
		component.setOpaque(false);
		component.setEditable(false);
	}
	//-------------------------
	
	public void update(Observable arg0, Object arg1) {
		
	}
}
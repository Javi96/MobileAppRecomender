package appRecomendations.view;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import appRecomendations.DataBase;
import appRecomendations.controller.Controller;

public class View extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JScrollPane mainScroll;
	private JScrollPane recomendedGames;
	private JScrollPane recomendedByCategory;

	private JPanel mainPanel;
	private JPanel recomendedGamesPanel;
	private JPanel recomendedByCategoryPanel;

	public void start(Controller controller) {
		ArrayList<ImageIcon> imageIconList = controller.getImageIconList();
		
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
					System.err.println("hola");
					
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
		this.pack();
	}
	
	public static void main(String[] args) {
		View view  = new View();
		view.start2(new Controller(new DataBase(), view));
	}
	
	//-------------------------
	
	private JTextPane recomendedGamesTitle;
	private int counter=0;
	
	public void start2(final Controller controller) {
		ArrayList<ImageIcon> imageIconList = controller.getImageIconList();
		
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
		for(counter=0; counter< imageIconList.size(); counter++){
			label = new JLabel(imageIconList.get(counter));
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
					controller.actUserApp(counter);
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
		this.generateTextPane(this.recomendedGamesTitle, "Recomendados para t� <3: ", 45, Color.blue);
		this.mainPanel.add(this.recomendedGamesTitle);
		this.mainPanel.add(this.recomendedGames);
		this.mainScroll = new JScrollPane(this.mainPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		this.add(this.mainScroll);
		this.setVisible(true);
		this.pack();
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

}
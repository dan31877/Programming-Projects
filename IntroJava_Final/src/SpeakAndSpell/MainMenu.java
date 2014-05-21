package SpeakAndSpell;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Daniel Anderson
 * CS 232   Due: 12/18/2012
 * Final Project
 * MainMenu.java
 */

public class MainMenu extends JFrame{
	
	// GUI variables
	private JLabel readingTitle; 
	private JLabel mathTitle;
	
	private JButton spellandspeak; 
	private JButton fcsingle; 
	private JButton fcdouble;
	private JButton fcdoublesingle;
	private JButton fcmultiply;
	private JButton fcdivide;
	 
	private JPanel one; 
	
	// Default Constructor
	public MainMenu(){ 
		
		super("Main Menu"); 
		/**
		 * Sets the JLabels
		 */
		readingTitle = new JLabel("Select for the spell and speak: "); 
		mathTitle = new JLabel("Select choices for flash cards: "); 
		
		/**
		 * Defines the buttons and sets an action to open the appropriate GUI
		 * and close the current.  
		 */
		spellandspeak = new JButton("Spell and Speak"); 
		spellandspeak.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent event){
						SpeakSpellGUI speakspell = new SpeakSpellGUI(); 
						speakspell.createGUI();
						close(); 
					}
				});
		
		fcsingle = new JButton("Single digit addition and subtraction"); 
		fcsingle.addActionListener(
				new ActionListener(){
				public void actionPerformed(ActionEvent event){
						FlashCard flashcard = new FlashCard("single"); 
						flashcard.createGUI();
						close(); 
					}
				});
		fcdouble = new JButton("Double digit addition and subtraction");
		fcdouble.addActionListener(
				new ActionListener(){
				public void actionPerformed(ActionEvent event){
						FlashCard flashcard = new FlashCard("double"); 
						flashcard.createGUI();
						close(); 
					}
				});
		fcdoublesingle = new JButton("Single and Double digit addition and subtraction");
		fcdoublesingle.addActionListener(
				new ActionListener(){
				public void actionPerformed(ActionEvent event){
						FlashCard flashcard = new FlashCard("doublesingle"); 
						flashcard.createGUI();
						close(); 
					}
				});
		fcmultiply = new JButton("Simple multiplication");
		fcmultiply.addActionListener(
				new ActionListener(){
				public void actionPerformed(ActionEvent event){
						FlashCard flashcard = new FlashCard("multiply"); 
						flashcard.createGUI();
						close(); 
					}
				});
		fcdivide = new JButton("Simple division");
		fcdivide.addActionListener(
				new ActionListener(){
				public void actionPerformed(ActionEvent event){
						FlashCard flashcard = new FlashCard("divide"); 
						flashcard.createGUI();
						close(); 
					}
				});
		
		/**
		 * Adds components to a JPanel using gridlayout then adds to the frame
		 */
		Component[] comps = {readingTitle, spellandspeak, mathTitle, fcsingle, 
				fcdouble, fcdoublesingle, fcmultiply, fcdivide};
		one = new JPanel();
		one.setLayout(new GridLayout(8, 1));
		for(int i=0; i<comps.length; i++){ 
			addToPane(comps[i], one); 
		}
		
		add(one); 
	}
	
	/**
	 * Creates the GUI 
	 */
	public void createGUI(){ 
	     this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	     this.setSize(400, 350);
	     this.setLocationRelativeTo(null);
	     this.setVisible(true);
	}
	
	/**
	 * Closes the current frame
	 */
	public void close(){
		setVisible(false);
		dispose();
	}
	
	/**
	 * Adds buttons to a pane to be added to the grid
	 * 
	 * @param comp
	 * @param panel
	 */
	public void addToPane(Component comp, JPanel panel){ 
		JPanel p = new JPanel();
		p.add(comp); 
		panel.add(p); 
	}
	
	//Main method: used for testing
	public static void main(String[] args){ 
		MainMenu main = new MainMenu(); 
		main.createGUI(); 
	}
	
}

package SpeakAndSpell;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/**
 * Daniel Anderson
 * CS 232   Due: 12/18/2012
 * Final Project
 * SpeakSpellGUI.java
 */

public class SpeakSpellGUI extends JFrame{
	
	// GUI variables
	private JPanel panel;   
	private JPanel buttonpanel; 
	private JButton button; 
	private JButton readIt; 
	private JButton mainmenu; 
	private JTextField textfield; 
	private String letter; 
	private String word; 
	
	// Voice object to read letters and words
	private Voice voice = new Voice("mbrola_us1");
	
	// String array of letter labels
	private String[] letters = 
		{ "Q", "W", "E", "R", "T", "Y","U", "I", "O", "P", 
		  "A", "S", "D", "F", "G", "H", "J", "K", "L", "Ret",
		   "", "Z", "X", "C", "V", "B", "N", "M", "Sp", ""};
	
	// Default Constructor
	public SpeakSpellGUI(){
		super("Spell and Speak");
		
		/**
		 * Creates a textfield, adds an action that reads the word or reads an 
		 * error message, then adds to the frame. 
		 */
		textfield = new JTextField(20);
		word = textfield.getSelectedText();
		textfield.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent event){
							try{ 
								word = event.getActionCommand();
								if(!wordChecker(word))
									throw new InvalidWordException();
								voice.say(word);
								clear();
							}
							catch(InvalidWordException e){
								voice.say(e.getMessage());
								clear();
							}
					}
				});
		add(textfield, BorderLayout.NORTH); 
		
		/**
		 * Creates buttons read it and main menu, adds to a JPanel then adds the 
		 * JPanel to the frame.  
		 * Main Menu - brings you to the main menu
		 * Read It - reads the word
		 */
		buttonpanel = new JPanel(); 
		mainmenu = new JButton("Main Menu"); 
		mainmenu.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent event){
						close(); 
						MainMenu menu = new MainMenu(); 
						menu.createGUI(); 
					}
				});
		buttonpanel.add(mainmenu); 
		readIt = new JButton("Read It!");
		readIt.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent event){
						try{ 
							if(!wordChecker(textfield.getText()))
								throw new InvalidWordException();
							voice.say(textfield.getText());
							clear();
						}
						catch(InvalidWordException e){
							voice.say(e.getMessage());
							clear();
						}
					}
				});
		buttonpanel.add(readIt); 
		add(buttonpanel, BorderLayout.SOUTH);
				
		/**
		 * Creates a JPanel with a gridlayout.  Uses a for loop to add a button with each letter.
		 * Pushing the button reads the letter and adds it to word and the text field.  Pressing space will 
		 * not read anything and pressing enter reads the word.
		 */
		panel = new JPanel(); 
		panel.setLayout(new GridLayout(3, 10)); 
	      for (int row = 0; row < letters.length; row++) {
	    	  	  word = "";
	    	  	  letter = letters[row]; 
	    		  button = new JButton(letter);
	    		  button.addActionListener(
	    					new ActionListener(){
	    						public void actionPerformed(ActionEvent event){
	    							letter = event.getActionCommand();
	    							if(letter.equalsIgnoreCase("Sp")){
	    								word = word + " ";
	    							}
	    							else if(letter.equalsIgnoreCase("Ret")){
	    								try{ 
	    									if(!wordChecker(textfield.getText()))
	    										throw new InvalidWordException();
	    									voice.say(textfield.getText());
	    									clear();
	    								}
	    								catch(InvalidWordException e){
	    									voice.say(e.getMessage());
	    									clear();
	    								}
	    								
	    							}
	    							else{
	    								voice.say(letter);  
	    								word = word + letter.toLowerCase(); 
	    								textfield.setText(word); 
	    							}
	    						}
	    					});
	    		  
	    		  panel.add(button);
	    	  }
	      add(panel, BorderLayout.CENTER); 
	    
	  }
	/**
	 * Checks the word for numbers
	 * @param str
	 * @return
	 */
	public boolean wordChecker(String str){ 
		
		boolean check = true; 
		for(int j=0; j<str.length(); j++){
			if( str.charAt(j)>='0' && str.charAt(j)<='9'){
				check = false; 
				}
		}
		return check;
			
	}
	/**
	 * Clears the word after it's read
	 */
	public void clear(){ 
		word = "";		
		textfield.setText("");
	}

	/**
	 * Closes the current frame
	 */
	public void close(){
		setVisible(false);
		dispose();
	}
	/**
	 * Creates the GUI 
	 */
	public void createGUI(){ 
	     this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	     this.setSize(600, 250);
	     this.setLocationRelativeTo(null);
	     this.setVisible(true);
		
	}
	//Main method: used for testing
	public static void main(String[] args){ 
	     SpeakSpellGUI speakspell = new SpeakSpellGUI();
	     speakspell.createGUI();
		
	}

}

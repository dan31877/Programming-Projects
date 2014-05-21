package SpeakAndSpell;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Daniel Anderson
 * CS 232   Due: 12/18/2012
 * Final Project
 * FlashCard.java
 */


public class FlashCard extends JFrame{
	
		// GUI variables
		private JPanel panel; 
		private JPanel buttonpanel; 
		private JLabel firstnum; 
		private JLabel secondnum; 
		private JLabel answer; 
		
		private JButton answerButton; 
		private JButton next; 
		private JButton mainmenu; 
		
		private String selection;
		private FlashCardMath fcm; // FlashCard math variable
		
		private String first; 
		private String second; 
		private String operator;
		private String ans;
		
		private String secondLabel; 
		
		private Voice voice = new Voice("kevin16");
		
		public FlashCard(String string){
			super("Flashcard"); 
			
			// Defines the FlashCard Math variables
			selection = string; 
			fcm = new FlashCardMath(selection);
			first = fcm.getFirst(); 
			second = fcm.getSecond(); 
			operator = fcm.getOperator();
			ans = fcm.getAnswer();
			secondLabel = operator + "       " + second;
			
			panel = new JPanel(); 
			buttonpanel = new JPanel(); 
			firstnum = new JLabel(first); 
			this.setLabel(firstnum);
			secondnum = new JLabel("<html><u>" + secondLabel + "</html></u>"); 
			this.setLabel(secondnum);
			answer = new JLabel();
			this.setLabel(answer);
			
			panel.setLayout(new GridLayout(3, 1)); 
			panel.add(firstnum); 
			panel.add(secondnum);
			panel.add(answer);
			
			/**
			 * Answer button reads the answer
			 */
			answerButton = new JButton("Answer");
			answerButton.addActionListener(
					new ActionListener(){
						public void actionPerformed(ActionEvent event){
							answer.setText(ans);
							voice.say(fcm.getAnswer()); 
						}
					});
			
			/**
			 * Next Button: generates new variables from the FlashCardMath class. 
			 * Assigns the new values to the JLabels and reads the math sentence.
			 */
			next = new JButton("Next"); 
			next.addActionListener(
					new ActionListener(){
						public void actionPerformed(ActionEvent event){
							answer.setText(""); 
							fcm.generate(selection);
							firstnum.setText(fcm.getFirst()); 
							secondLabel = (fcm.getOperator() + "       " + 
									fcm.getSecond());
							secondnum.setText("<html><u>" + secondLabel + 
									"</html></u>"); 
							ans = fcm.getAnswer(); 
							String question = fcm.getFirst() + " " + fcm.getSayOperator() + " "
									+ fcm.getSecond();
							voice.say(question);
						}
					});
			
			/**
			 * Brings you back to the main menu, closes current frame
			 */
			mainmenu = new JButton("Main Menu");
			mainmenu.addActionListener(
					new ActionListener(){
						public void actionPerformed(ActionEvent event){
							close(); 
							MainMenu menu = new MainMenu(); 
							menu.createGUI(); 
						}
					});
			/**
			 * Adds the buttons to the button panel
			 */
			buttonpanel.add(mainmenu, BorderLayout.EAST);
			buttonpanel.add(answerButton, BorderLayout.CENTER);
			buttonpanel.add(next, BorderLayout.WEST); 
			
			/**
			 * Adds the main panel and button panel to the frame.
			 */
			add(panel, BorderLayout.CENTER); 
			add(buttonpanel, BorderLayout.SOUTH);
			
 
			
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
		     this.setSize(275, 350);
		     this.setLocationRelativeTo(null);
		     this.setVisible(true);
			
			 String question = fcm.getFirst() + " " + fcm.getSayOperator() + " "
						+ fcm.getSecond();
			 voice.say(question);
		}
		/**
		 * Sets the label including the name and background color.  Also sets 
		 * the font and alignment
		 * @param label
		 */
		public void setLabel(JLabel label){ 
			label.setBackground(Color.WHITE); 
			label.setOpaque(true); 
			label.setHorizontalAlignment(SwingConstants.RIGHT);
			label.setFont(new Font("Helvetica", Font.BOLD, 60));
		}
		
		//Main method: used for testing
		public static void main(String[] args){
			FlashCard flashcard = new FlashCard("single"); 
			flashcard.createGUI();
		}

}

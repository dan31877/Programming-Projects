package PalindromeParenthesis;
import java.util.Scanner;

/**
 * Daniel Anderson
 * CS 342   Due: 3/5/2013
 * Program 1 
 * 
 * Parenthesis.java 
 * 
 * Description: compares the number of left and right parenthesis  
 * in a String, given by the user or provided by the program
 */

public class Parenthesis {

	/**
	 * Main method: Creates a Parenthesis object and runs the 
	 * runParenthesisStack() method.
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Parenthesis parenthesis = new Parenthesis(); 
		parenthesis.runParethesisStack();

	}
	
	/**
	 * runParenthesisStack - Left parenthesis are pushed to the stack 
	 * while right parenthesis are popped.  Popping an empty stack means 
	 * there are too many right. Reaching the end with items in the stack 
	 * means there are too many left. If it reaches the end with an empty 
	 * list, the number of right and left match.
	 */
	public void runParethesisStack(){ 
		LinkedStack stack =  new LinkedStack();
		String demo = "(((())))";
		String parenthesis = this.getUserEntry("Enter a string to test if " + 
				"the number of left parenthesis is equal to the number of \nright or type d for demo");

		if( parenthesis.equalsIgnoreCase("D")){
			parenthesis = demo; 
			System.out.println(parenthesis);
		}
		
		try{ 
			for (int i=0; i<parenthesis.length(); i++){
				Character c = parenthesis.charAt(i);
				String s = c.toString();
				
				if( s.equals("("))
					stack.push(s);
				else if( s.equals(")")){
					if( stack.isEmpty()){
						throw new Exception("There are more right parenthesis than left parenthesis");
					}
					else
						stack.pop();
				}
	
			}
				
			if( stack.isEmpty())
				System.out.println("The number of right and left parenthesis match.");
			else
				System.out.println("There are more left parenthesis than right parenthesis.");
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * leadingRightParenthesis - True for a leading right parenthesis, 
	 * false for a leading left parenthesis
	 * 
	 * @param parenthesis
	 * @return boolean
	 */
	private boolean leadingRightParenthesis(String parenthesis){ 
		
		boolean rtn = false; 
		for( int i=0; i<parenthesis.length(); i++){ 
			Character c = parenthesis.charAt(i);
			String s = c.toString();
			if( s.equals(")")){
				rtn = true; 
				i = parenthesis.length();
			}		
			else if(s.equals("(")){
				rtn = false; 
				i = parenthesis.length();
			}
		}
		return rtn;
	}

	/**
	 * Method displays a message to the user and returns the user input
	 * in the form of a String.
	 * 
	 * @param message
	 * @return
	 */
	private String getUserEntry(String message){
		Scanner scanner = new Scanner(System.in);
		 System.out.println(message);
		String rtn = scanner.nextLine();
		while(leadingRightParenthesis(rtn)){
			System.out.println("Invalid entry. Please enter a different string.");
			rtn = scanner.nextLine();			
		}
		scanner.close();
			return rtn;
	}
}



package PalindromeParenthesis;
import java.util.Scanner;

/**
 * Daniel Anderson
 * CS 342   Due: 3/5/2013
 * Program 1
 * 
 * Palindrome.java
 * 
 * Description: Checks a String input by the user or a given example 
 * to see if it's a palindrome. 
 */

public class Palindrome {

	/**
	 * Main method: Creates a Palindrome object and runs the 
	 * runPalindromeStack() method.
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Palindrome palindrome = new Palindrome(); 
		palindrome.runPalindromeStack();

	}
	/**
	 * runPalindromeStack - Input(user or given) is capitalized and 
	 * each character is removed from the String, verified that it's 
	 * a letter, and pushed to the stack. Output is popped off the 
	 * stack and is compared to the input to check if it's a palindrome.
	 */
	public void runPalindromeStack(){
		LinkedStack stack =  new LinkedStack();
		String demo = "kayak";
		String palindrome = this.getUserEntry("Enter a string to test if it’s a palindrome or type d for demo.  " +
				"\nNumbers and punctuation will be ignored.");
		String input = ""; 
		
		if( palindrome.equalsIgnoreCase("D")){
			palindrome = demo; 
			System.out.println("Demo: " + palindrome);
		}
		palindrome = palindrome.toUpperCase();
		

		for( int i = 0; i< palindrome.length(); i++){ 
			Character c = palindrome.charAt(i);  
				if(  Character.isLetter(c) ){ 
					String s = Character.toString(c); 
					input += s; 
					stack.push(s);
				}
		}
		String out = ""; 
		
		while( !stack.isEmpty()){ 
			out = out + stack.pop();
		}

		System.out.println("Stack Input: " + input + "  Stack Output: " + 
										out);
		if( input.equals(out))
			System.out.println("This string is a palindrome.");
		else
			System.out.println("This is not a palindrome. ");

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
		scanner.close();
			return rtn;
	}
}

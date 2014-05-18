package ShoppingCart;
import java.util.Scanner;

/**
 * Daniel Anderson
 * CS 232   Due: 12/11/2012
 * Assignment 7
 * ShoppingListGenerator.java
 */

public class UserInterface {
	
	
	Scanner keyboard = new Scanner(System.in);  // New scanner object
	Scanner scanner = new Scanner(System.in);	// New scanner object
	
	/**
	 * Gets an int from the user with a String argument to specify a message.
	 * Verifies the input is an int and asks until an int is entered. 
	 * 
	 * @param message
	 * @return
	 */
	public int getInt(String message){ 
		
		System.out.print(message); 
		while( !keyboard.hasNextInt()){ 
			
			System.out.println("The entry must be an integer.  Please try again."); 
			keyboard.next();
		}
		return keyboard.nextInt();
	}
	
	/** 
	 * Gets a String from the user and verifies there are no numbers.  
	 * 
	 * @param message
	 * @return
	 */
	public String getString(String message){ 
		
		System.out.print(message);
		boolean check = false;
		String str = "";
		while(!check){
			try{
				str = keyboard.next();
				check = true; 
				for(int j=0; j<str.length(); j++){
					if( str.charAt(j)>='0' && str.charAt(j)<='9'){
						check = false; 
						throw new LetterNotFoundException();
						}  
					}
			}catch(LetterNotFoundException e){ 
				System.out.println(e.getMessage());
				System.out.print("Please enter again with no numbers: ");
			
			}
		}
		return str;
	}
	
	/**
	 * Gets a line of input from the user.  Used when the String has a space in it. 
	 * 
	 * @param message
	 * @return
	 */
	public String getLine(String message){ 
		
		System.out.print(message);
		boolean check = false;
		String str = "";
		while(!check){
			try{
				str = scanner.nextLine();
				check = true; 
				for(int j=0; j<str.length(); j++){
					if( str.charAt(j)>='0' && str.charAt(j)<='9'){
						check = false; 
						throw new LetterNotFoundException();
						}  
					}
			}catch(LetterNotFoundException e){ 
				System.out.println(e.getMessage());
				System.out.print("Please enter again with no numbers: ");
			
			}
		}
		return str;
	}
	
	/**
	 * Gets a double from the user using a specific String message. Verifies 
	 * the input is a double.
	 * 
	 * @param message
	 * @return
	 */
	public double getDouble(String message){ 
		
		System.out.print(message);
		while( !keyboard.hasNextDouble()){ 
			
			System.out.print("The amount must be a number. $"); 
			keyboard.next();
		}

			return keyboard.nextDouble();

	}
	
	/**
	 * Closes both scanners
	 */
	public void closeScanner(){ 
		
		scanner.close();
		keyboard.close();
	}
}

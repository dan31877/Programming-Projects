package ShoppingCart;

/**
 * Daniel Anderson
 * CS 232   Due: 12/11/2012
 * Assignment 7
 * LetterNotFoundException.java
 */

public class LetterNotFoundException extends Exception{


	public LetterNotFoundException(){ 
		
		super("Input can't contain numbers. Please try again. ");
	}
	
	public LetterNotFoundException(String message){
		super(message); 
	}
}

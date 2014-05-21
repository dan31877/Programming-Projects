package SpeakAndSpell;
/**
 * Daniel Anderson
 * CS 232   Due: 12/18/2012
 * Final Project
 * InvalidWordException.java
 */

public class InvalidWordException extends Exception{

	
	public InvalidWordException(){ 
		
		super("Word cannot contain numbers. Please try again. ");
	}
	
	public InvalidWordException(String message){
		super(message); 
	}
}

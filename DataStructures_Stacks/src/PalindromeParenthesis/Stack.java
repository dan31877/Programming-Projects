package PalindromeParenthesis;
/**
 * Interface Stack
 * 
 * Description: an interface to determine how Stack objects will be 
 * formatted
 * 
 * @author dcanderson
 */
public interface Stack {
	
	/**
	 * push - creates a Node with the data, adds the Node to the head of
	 * the stack  
	 * @param data
	 */
	public void push(String data);
	
	/**
	 * pop - removes a Node from the head
	 * @return String data
	 */
	public String pop();
	
	/**
	 * isEmpty - Checks the stack to see if it's empty
	 * @return true if the stack is empty
	 */
	public boolean isEmpty();
	
	/**
	 * depth - returns the size of the stack
	 * @return int that is the size of the stack
	 */
	public int depth();
	
	/**
	 * stackTop - returns the data from the first Node without removing it  
	 * @return String data
	 */
	public String stackTop();
	
	/**
	 * clear - Sets head to null and size to 0.
	 */
	public void clear();	
}

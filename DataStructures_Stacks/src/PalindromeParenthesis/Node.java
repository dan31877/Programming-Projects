package PalindromeParenthesis;
/**
 * Class Node 
 * 		@param <T>
 * 
 * Description: Creates a Node object to use in building the LinkedStack
 * 
 * @author dcanderson

 */
public class Node<T> {
	
	// Instance variables
	private T data;
	private Node<T> next;
	
	/**
	 * getData - returns the data
	 */
	public T getData() {
		return data;
	}
	
	/**
	 * setData - Sets the data to the parameter
	 * @param data
	 */
	public void setData(T data) {
		this.data = data;
	}
	
	/**
	 * getData - returns the next Node
	 */
	public Node<T> getNext() {
		return next;
	}
	
	/**
	 * setNode - sets the next Node to the Node passed
	 * @param next
	 */
	public void setNext(Node<T> next) {
		this.next = next;
	}
}

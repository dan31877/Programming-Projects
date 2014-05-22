package LinkedList;

/**
 * Class IntNode
 * 
 * Description: Node of a generic type for any Linked List. 
 * 
 * @author dcanderson
 *
 */

public class GenericNode<T> {
	
	// Instance variables
	private T data;
	private GenericNode<T> next;
	
	/**
	 * Setters and Getters
	 * @return
	 */
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public GenericNode<T> getNext() {
		return next;
	}
	public void setNext(GenericNode<T> next) {
		this.next = next;
	}
}

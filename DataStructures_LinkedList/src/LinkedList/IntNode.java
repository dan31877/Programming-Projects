package LinkedList;

/**
 * Class IntNode
 * 
 * Description: Node of type int for a Linked List of ints. 
 * 
 * @author dcanderson
 *
 */

public class IntNode {

	// Instance variables
	private int data;
	private IntNode next;
	
	/**
	 * Setters and Getters
	 * @return
	 */
	public int getData() {
		return data;
	}
	public void setData(int data) {
		this.data = data;
	}
	public IntNode getNext() {
		return next;
	}
	public void setNext(IntNode next) {
		this.next = next;
	}
}

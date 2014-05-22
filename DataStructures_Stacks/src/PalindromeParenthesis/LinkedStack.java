package PalindromeParenthesis;

/**
 * Class LinkedStack (implements Stack interface)
 *
 * Description: creates a LinkedStack using Nodes
 * 
 * @author dcanderson
 */
public class LinkedStack implements Stack{
	
	// Variables
	private Node<String> head; 
	private int stackSize = 0; 
	
	/**
	 * Default Constructor
	 */
	public LinkedStack(){ 
		this.clear();
	}

	/**
	 * push - creates a Node with the data, adds the Node to the 
	 * head of the stack.  
	 * @param data
	 */
	public void push(String data) {
		Node<String> temp = new Node<String>(); 
		temp.setData(data);
		temp.setNext(head); 
		head = temp; 
		stackSize++; 
	}

	/**
	 * pop - removes a Node from the head
	 * @return String data
	 */
	public String pop() {
		try{
			if (this.isEmpty())
				throw new Exception("Pop: the stack is empty.");
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
		String rtn = head.getData(); 
		head = head.getNext();
		stackSize--; 
		return rtn;
	}

	/**
	 * isEmpty - Checks the stack to see if it's empty
	 * @return true if the stack is empty
	 */  
	public boolean isEmpty() {
		return (stackSize == 0);
	}

	/**
	 * depth - returns the size of the stack
	 * @return int that is the size of the stack
	 */
	public int depth() {
		return stackSize;
	}

	/**
	 * stackTop - returns the data from the first Node without removing it  
	 * @return String data
	 */  
	public String stackTop() {
		return head.getData();
	}

	/**
	 * clear - Sets head to null and size to 0.
	 */
	public void clear() {
		head = null; 
		stackSize = 0; 
	}
	
	// Returns a String version of the Stack
	public String toString(){
		
		String stack = ""; 
		if( isEmpty()){ 
			stack+= "The list is empty."; 
		}
		else{
			stack += "Stack: "; 
			for(Node<String> temp = head; temp != null; temp = 
						temp.getNext()){
				
				stack += temp.getData() + " > ";
			}
			stack += "null"; 
		}
		
		return stack;
	}
}



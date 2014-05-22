package LinkedList;

/**
 * Daniel Anderson
 * 
 * IntLinkedList.java
 * 
 * Description: Loads 10 int nodes into a linked list.
 */

public class IntLinkedList {

	// Instance variables
	private IntNode head; 
	private int listSize; 
	
	/**
	 * Default Constructor
	 */
	public IntLinkedList(){ 
		head = null; 
		listSize = 0;
	}
	
	// Gets the ListSize
	public int getListSize() {
		return listSize;
	}
	
	// Adds a Node to the front of the list
	public void addToFront(int data){
		IntNode temp = new IntNode();
		temp.setData(data);
		temp.setNext(head);
		head = temp; 
		listSize++; 
		
	}
	
	/**
	 * Removes the last element of the list.  
	 */
	public void removeLast(){ 
		
		try{ 
			// Throws exception if the list is empty.
			if(listSize == 0){ 
				throw new Exception("Can't remove: empty list.");
			}
			// Special condition, if the list only has one node.
			else if( listSize ==1){ 
				head = null; 
				listSize--; 
			}
			// Scrolls to second to last node and sets the next to null.  
			else{
				IntNode temp = new IntNode(); 
				for(temp = head; temp.getNext().getNext() != null; temp = 
											temp.getNext()){
					//scroll to the second to last item on list.
				}
				temp.setNext(null); 
				listSize--; 
			}
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * String representation of the list.  
	 */
	public String toString(){
		
		String list = ""; 
		if( listSize == 0){ 
			list+= "The list is empty."; 
		}
		else{
			list += "List(size " + this.getListSize() + "): "; 
			IntNode temp = new IntNode();
			for(temp = head; temp != null; temp = temp.getNext()){
				
				list += temp.getData() + " > ";
			}
			list+= "null"; 
		}
		
		return list;
	}
	
	// Main Method to run the program
	public static void main(String[] args) {
		
		// Create a new instance
		IntLinkedList list = new IntLinkedList();
		
		// Add the 10 elements to the list
		int[] elements = {4, 5, 8, 9, 2, 3, 11, 14, 17, 19}; 
		
		for( int i=0; i<elements.length; i++){ 
			list.addToFront(elements[i]); 
		}
		
		// Displays the list on the screen
		System.out.println(list);
		System.out.println();
		
		// Delete the last element and prints the list.
		list.removeLast(); 
		System.out.println("Remove the last item on the list.");
		System.out.println(list);
		System.out.println();
		
		// Deletes the list plus one element. Tests the method on an empty list  
		System.out.println("Repeat the remove method for all items plus one.");
		for( int i=0; i<10; i++){ 
			list.removeLast(); 
			if(list.getListSize() < 3)
				System.out.println(list);
		}
	}
}
package LinkedList;

/**
 * Daniel Anderson
 * 
 * StringLinkedList.java
 * 
 * Description: Loads 10 generic nodes into a linked list as Strings. 
 */

public class StringLinkedList {
	
	// Instance variables
	private GenericNode<String> head; 
	private int listSize; 
	
	/**
	 * Default Constructor
	 */
	public StringLinkedList(){ 
		head = null; 
		listSize = 0;
	}
	
	// Gets the ListSize
	public int getListSize() {
		return listSize;
	}
	
	// Adds a Node to the front of the list
	public void addToFront(String data){
		GenericNode<String> temp = new GenericNode<String>();
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
				GenericNode<String> temp = new GenericNode<String>(); 
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
			GenericNode<String> temp = new GenericNode<String>();
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
		StringLinkedList list = new StringLinkedList();
		
		// Add the 10 elements to the list
		String[] elements = {"elements", "ten", "with", "Strings", "of", 
				"List", "Linked", "a", "is", "This" };

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

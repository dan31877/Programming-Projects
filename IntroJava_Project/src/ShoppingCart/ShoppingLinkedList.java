package ShoppingCart;
import java.text.DecimalFormat;

/**
 * Daniel Anderson
 * CS 232   Due: 12/11/2012
 * Assignment 7
 * ShoppingLinkedList.java
 */

public class ShoppingLinkedList implements Printable{

	private Node list; 			// Creates the first node in the list
	private double totalCost; 	// Sum of all the item prices
	
	// Default Constructor
	public ShoppingLinkedList(){ 
		list = null; 
		
	}
	
	/** Set method: Sets the cost  	*/
	public void setCost( double c){ 
		
		totalCost = c; 
	}
	
	/** Get method: Returns the cost  	*/
	public double getCost(){ 
		
		return totalCost; 
	}
	
	/** Get method: Returns the first node in the Linked List 	*/
	public Node getList(){ 
		
		Node node = list; 
		return node; 
	}
	
	/** Get method: Returns the previous node of a given node  	*/
	public Node getPrevious(Node node){ 
		
		Node temp = list;
		Node node2 = new Node(); 
		while( temp.getNext() != null){
			if( temp.getNext().getData().equals(node.getData()))
				node2 = temp;
			temp = temp.getNext(); 
		}
		
		return node2; 
	}
	
	/** Returns true if the list is empty  	*/
	public boolean isEmpty(){ 
		
		return list == null; 
	}
	
	/** Get method: Returns the size  	*/
	public int getSize(){ 
		
		int size = 0; 
		Node node = list; 
		while(node != null){ 
			node = node.getNext();
			size++; 
		}
		return size;
	}
		
	/** 
	 * Swap method: swaps two nodes when one is the first
	 */
	private void swapFirst(Node n2){ 
		
		if( n2.getData().equals(list.getNext().getData())){
			Node n1 = list; 

			n1.setNext(n2.getNext()); 
			n2.setNext(n1);
			list = n2;
		}
		else{ 
			Node n1 = list;
			Node n2prev = getPrevious(n2);
			Node temp = new Node(); 
			
			temp = n1.getNext(); 
			n1.setNext(n2.getNext());
			n2.setNext(temp); 
			
			temp = n1; 
			list = n2; 
			n2prev.setNext(temp);
		}
		
	}
	
	/** Swaps two adjacent nodes */
	private void swapAdjacent(Node n1){ 
		
		Node n2 = n1.getNext(); 
		Node n1prev = getPrevious(n1);  

		n1.setNext(n2.getNext()); 
		n2.setNext(n1);
		n1prev.setNext(n2);

		
		
	}
	
	/**
	 * Swaps two nodes.  Uses other swap methods based on position. 
	 * @param n1
	 * @param n2
	 */
	public void swap(Node n1, Node n2){ 
		
		if( n1.getData().equals(getList().getData()) || n2.getData().equals(getList().getData())){ 
			if(n1.getData().equals(getList().getData()))
				swapFirst(n2);
			else
				swapFirst(n1);
		}
		else if( n1.getNext().getData().equals(n2.getData()) || n2.getNext().getData().equals(n1.getData())){
			if( n1.getNext().getData().equals(n2.getData()))
				swapAdjacent(n1);
			else
				swapAdjacent(n2);
		}
		else{ 
			Node n1prev = getPrevious(n1);
			Node n2prev = getPrevious(n2);
			Node temp = new Node(); 
			
			temp = n1.getNext(); 
			n1.setNext(n2.getNext());
			n2.setNext(temp); 
			
			temp = n1; 
			n1prev.setNext(n2); 
			n2prev.setNext(temp);
		} 	
	}
	
	/**
	 * Sorts the Linked List using the swap method.
	 */
	public void sort(){
		
		if(isEmpty() || getSize() ==1)
			return;
		else{   
			Node node = list;
			Node node2 = node; 
			while( node!= null){ 
				node2 = node; 
				while( node2 !=null){
					if(node.getData().getPriority() > node2.getData().getPriority()){
						swap(node, node2); 
					}	
					node2 = node2.getNext();  								
					}
					node = node.getNext(); 

				}

			}
		
	}
	
	/**
	 * Adds a node to the end of the list. 
	 * @param obj
	 */
	public void insert(ShoppingItem obj){
		
		if(list == null){
			list = new Node(obj, null);
			totalCost = totalCost + obj.getCost();
		}
		else{ 
			Node node = list; 
			while( node.getNext() != null){ 
				node = node.getNext(); 
			}
			node.setNext(new Node(obj, null)); 
			totalCost = totalCost + obj.getCost();
			//return list; 
		}
		
	}
	
	/**
	 * Removes a specific item
	 * @param obj
	 */
	public void delete(ShoppingItem obj){ 
		if(list == null)
			return;
		Node node = list; 
		Node next = node.getNext(); 
		if( node.getData().equals(obj)){
			list = list.getNext();
			totalCost = totalCost - node.getData().getCost();
		}
		else{
			while( ! next.getData().equals(obj)){ 
				next = next.getNext();
				node = node.getNext();
			}
			node.setNext(node.getNext().getNext());
			totalCost = totalCost - next.getData().getCost();
		}
		
	}
	
	/**
	 * Returns true if two lists are equal.  Compares the sizes of the 
	 * ArrayLists and the names of the items in the ArrayList.
	 * 
	 * @param l
	 * @return
	 */
	public boolean equals(ShoppingLinkedList l){ 
		
		//l.sort();
		
		boolean isEquals = false; 
		
		Node node = list;
		Node node2 = l.getList();
		
		System.out.println("List Size: " + getSize()); 
		System.out.println("List2 Size: " + l.getSize()); 
		
		if( getSize() != l.getSize()){ 
			isEquals = false;
		}
		else{ 
			
			while( node!= null){ 
				node2 = node; 
				while( node2 !=null){
					if(node.getData().equals(node2.getData())){
						isEquals = true; 
					}	
					else{ 
						isEquals = false;
					}
					node2 = node2.getNext();  								
					}
					node = node.getNext();
			}
			
		}
		return isEquals;
		
	}
	
	/**
	 * Method copies the list, then copies each item from the old 
	 * list and puts it in the corresponding index in the new list.
	 * 
	 * @return
	 */
	public ShoppingLinkedList copy(){ 
		
		ShoppingLinkedList listCopy = new ShoppingLinkedList();
		Node node = list; 
		
		while( node != null  ){ 
			
			ShoppingItem item = new ShoppingItem(); 
			item = node.getData().copy(); 
			
			listCopy.insert(item); 
			node = node.getNext();
		
		}
		
		return listCopy;
		
		
	}
	
	/**
	 * Prints the list and the items in the list
	 */
	public void print(){ 
		
		if( list == null){
			System.out.println("Wtf?");
		}
		else{ 
			Node node = list; 
			System.out.printf("%-15s %-10s %-10s %-10s %-10s\n", "Item: ", "Priority: ", 
					"Quantity: ", "Unit Price: ", "Cost: ");
			 
			while( node != null ){ 
				node.getData().print();
				//System.out.print(node.getData() + "\t");
				node = node.getNext();  
			}
			System.out.print("The total cost of all items: $"); 
			this.printCost();
			System.out.println();
		}
	}
	
	/** 
	 * Prints and formats the cost.
	 */
	public void printCost(){ 
		
		DecimalFormat df = new DecimalFormat("##.00");
		System.out.print(df.format(this.getCost()));
		System.out.println();
		
	}
	
	/**
	 * Uses the amount of money to make a list of items to be bought 
	 * today and items that can be bought tomorrow based on priority. 
	 */ 
	public ShoppingLinkedList goShopping(ShoppingLinkedList tomorrow, double amount, BankAccount bankAccount){ 
		
	for( Node node = list; node != null; node = node.getNext()){ 
		
		if( node.getData().getCost() > amount){ 
			
			tomorrow.insert(node.getData()); 
			delete(node.getData());
			
		}
		else{
			amount = amount - node.getData().getCost(); 
			bankAccount.withdraw(node.getData().getCost());
		}
		
	}
	return tomorrow; 
	}
	
	/**
	 * Checks to see if an item is already on the list.
	 * 
	 * @param item
	 * @return
	 */
	public boolean checkItemOnList(ShoppingItem item){ 
		
		boolean itemOnList = false; 
		for( Node node = list; node != null; node = node.getNext()){ 
			
			if(node.getData().equals(item)){
				itemOnList = true; 
			}
		}
		return itemOnList; 
	}
	
	/**
	 * If the item is on the list, asks the user if he wants to 
	 * add to the quantity of the item already on the list.
	 * 
	 * @param item
	 * @param n
	 */
	public void addToQuantity(ShoppingItem item, String n){ 
		
		try{
			for( Node node = list; node != null; node = node.getNext()){ 
				
				if(node.getData().equals(item)){
					if( n.equalsIgnoreCase("n")){
						
						throw new Exception("Item will not be added."); 
	
					}
					else if( n.equalsIgnoreCase("y")){ 
						node.getData().addQuantity(item.getQuantity());
						setCost(getCost() + 
								(node.getData().getPrice()*item.getQuantity()));
						throw new Exception("Quantity of the original entry is increased. ");
	
					}
					else{ 
						 
						throw new Exception("I don't understand. Moving on to the next item.");
						 
					}
				}
			}
		}catch(Exception e){ 
				System.out.println(e.getMessage());				
			}

	}
	
	/**
	 * Private Node class
	 * 
	 * @author dcanderson
	 *
	 */
	private class Node{
		
		private ShoppingItem data;  // ShoppingItem data
		private Node next; 		// next node
		
		// Default Constructor
		private Node(){
			
			data = null; 
			next = null; 
		}
		
		/**
		 * Node constructor that takes a ShoppingItem and a Node
		 * 
		 * @param obj
		 * @param nd
		 */
		private Node(ShoppingItem obj, Node nd){ 
			
			setData(obj); 
			setNext(nd); 
		}
		
		// Sets the data to a ShoppingItem object
		private void setData(ShoppingItem obj){ data = obj; }
		// Returns the ShoppingItem data
		private ShoppingItem getData(){ return data; }
		// Sets the next node
		private void setNext(Node nd){ next = nd;  }
		// Gets the next node
		private Node getNext(){ return next; }
		
		
		
	}
	
	// Main method for testing
	public static void main(String[] args){ 
		ShoppingItem item1 = new ShoppingItem("Milk", 2, 1); 
		ShoppingItem item2 = new ShoppingItem("Eggs", 1);
		ShoppingItem item3 = new ShoppingItem("Bread", 4, 16);
		ShoppingItem item4 = new ShoppingItem("Cheese", 8);
		ShoppingItem item5 = new ShoppingItem("Beer", 6);
		//ShoppingItem item6 = new ShoppingItem("OJ", 4); 
		//ShoppingItem item7 = new ShoppingItem("Yogurt", 3, 6);
		//ShoppingItem item8 = new ShoppingItem("Apples", 9);
		//ShoppingItem item9 = new ShoppingItem("Turkey", 3);
		//ShoppingItem item10 = new ShoppingItem("Paper Towels", 10);
		
		
		ShoppingLinkedList list = new ShoppingLinkedList(); 
		ShoppingLinkedList list2 = new ShoppingLinkedList(); 
		ShoppingLinkedList list3 = new ShoppingLinkedList(); 
		list.setCost(19.5); 
		System.out.print("Total Cost format test $");
		list.printCost(); 
		list.setCost(0.0);
		
		list.insert(item1);
		list.insert(item2);
		list.insert(item3);
		list.insert(item4);
		
		list.print();
		list.sort(); 
		list.print(); 
		
		list.delete(item2); 
		list.print(); 
		
		list2.insert(item1);
		//list2.add(item2);
		list2.insert(item3);
		list2.insert(item4); 
		System.out.println("List equals list2? " + list.equals(list2));
		list2.insert(item5);
		//System.out.println("List equals list2? (Item added)" + list.equals(list2));
		list3 = list2.copy(); 
		
		System.out.println("List2: "); 
		list2.print();
		System.out.println("List3: "); 
		list3.print();
		System.out.println("List2 equals list3? " + list2.equals(list3));
		System.out.println("List equals list3? " + list.equals(list3));
		
	}
}



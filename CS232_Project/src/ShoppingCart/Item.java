package ShoppingCart;
/**
 * Daniel Anderson
 * CS 232   Due: 12/11/2012
 * Assignment 7
 * Item.java
 */
public abstract class Item implements Nameable, Printable {

	private String itemName; 	// Name of the item
	private int quantity = 1; 		// Number of items
	
	// Default Constructor
	public Item(){ 
		
	}
	
	/** 
	 * Constructor that takes a String as a parameter and assigns its value 
	 * 
	 * @param n
	 * @param p
	 */
	public Item(String n, int q){
		
		this.setName(n); 
		this.setQuantity(q);
		
		
	}
	
	/** Set method: Sets the item's name  	*/ 
	public void setName(String n){
		
		itemName = n; 
	}
	
	/** Set method: Sets the item's quantity  	*/
	public void setQuantity(int q){ 
		
		quantity = q; 
	}
	
	/** Displays the item on the screen  */
	public abstract void print();
	
	/** Get method: Returns the item name  	*/
	public String getName(){
		
		return itemName; 
	}
	
	/** Get method: Returns quantity of the item */
	public int getQuantity(){
		
		return quantity; 
	}
	
	/** Method returns true is two items have the same name  */
	public boolean equals(Item item){ 
		
		if(this.getName().equalsIgnoreCase(item.getName()))
			return true; 
		else
			return false;
		
	}
	
	/** Method returns a copy of the ShoppingItem.  */
	public Item copy(Item item){ 
		
		item.setName(this.getName()); 
		
		return item;
		
	}
}

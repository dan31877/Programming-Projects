package ShoppingCart;
import java.text.DecimalFormat;

/**
 * Daniel Anderson
 * CS 232   Due: 12/11/2012
 * Assignment 7
 * ShoppingItem.java
 */

public class ShoppingItem extends Item implements Shoppable{

	private int priority;		// Item's priority
	private double unitPrice;  		// Item's price
	private double cost; 		// Cost includes price times quantity

	// Default Constructor
	public ShoppingItem(){ 
		
		super();
		
	}

	/** 
	 * Constructor that takes a String and an int to assign
	 * name and priority.  Sets quantity to 1.
	 * 
	 * @param n
	 * @param p
	 */

	public ShoppingItem(String n, int p){	
		
		super(); 
		this.setName(n);
		this.setQuantity(1); 
		this.setPriority(p);
		this.setprice();
		this.setCost();
		
		
	}
	/** 
	 * Constructor that takes a String and two ints to assign
	 * name, quantity, and priority.  
	 * 
	 * @param n
	 * @param p
	 */

	public ShoppingItem(String n, int q, int p){	
		
		super(n, q); 
		this.setPriority(p);
		this.setprice();
		this.setCost(); 
		
	}
	
	/** Set method: Sets the priority  	*/
	public void setPriority(int p){ 
		
		priority = p; 
	}
	
	/** Get method: Returns the priority  	*/
	public int getPriority(){
		
		return priority; 
	}
	
	/** 
	 * Set method: Sets the price.  Method uses random and drops 
	 * all except two decimal points for accurate calculations  	
	 */
	public void setprice(){ 
		
		unitPrice = ((Math.random()+1)*5)*100;
		int p = (int)unitPrice;
		unitPrice = (double)p/100; 
		
	}
	
	/** Method to manually set the price */
	public void manualSetPrice(double p){ 
		
		unitPrice = p; 
		
	}
	/** Get method: Returns the price  	*/
	public double getPrice(){
		
		return unitPrice; 
	}
	
	/** Method to set the cost */
	public void setCost(){ 
		
		cost = (this.getPrice())* (this.getQuantity()); 
		
	}
	/** Get method: Returns the cost  	*/
	public double getCost(){
		
		return cost; 
	}
	
	/** Adds to the quantity of the item and recalculates the total cost.   */
	public void addQuantity(int q){ 
		
		this.setQuantity(getQuantity()+q);
		this.setCost();
		
	}
	
	/** Method returns true is two items have the same name and priority  */
	public boolean equals(ShoppingItem item){ 
		
		if(super.equals(item)&& this.priorityEquals(item))
			return true; 
		else
			return false;
		
	}
	
	/** Method returns true is two items have the same priority  */
	public boolean priorityEquals(ShoppingItem item){ 
		
		if(this.getPriority() == item.getPriority())
			return true; 
		else
			return false;
		
	}
	
	/** Method returns a copy of the ShoppingItem.  */
	public ShoppingItem copy(){ 
		
		ShoppingItem item = new ShoppingItem();
		super.copy(item); 
		item.setPriority(this.getPriority());
		item.manualSetPrice(this.getPrice()); 
		item.setQuantity(this.getQuantity());
		item.setCost();
		
		return item;
		
	}
	/** 
	 * Displays the item on the screen.  Designed to look like a 
	 * shopping list 	
	 */
	public void print(){ 
		
		DecimalFormat df = new DecimalFormat("##.00");

		System.out.printf("%-15s %-10s %-10s %-12s %-10s\n", this.getName(), this.getPriority(), this.getQuantity(), 
				 "$" + df.format(this.getPrice()) , "$" + df.format(this.getCost()));

	}
	
	// Main method for testing
	public static void main(String[] args){ 
		
		ShoppingItem item1 = new ShoppingItem("Milk", 1); 
		Item item2 = new ShoppingItem("Eggs", 3, 2);
		Item item3 = new ShoppingItem("Eggs", 2);
		
		item1.print();
		
		System.out.println("\nChanging item1 to Bread with priority 4"); 
		item1.setName("Bread"); 
		item1.setPriority(4);
		
		item1.print(); 		
		item2.print();
		item3.print();
		
		
		Item item4 = item1.copy();
		item1.addQuantity(2); 
		
		System.out.println("\nCopy item1 to item4. Then add 2 in quantity to item1."); 
		
		item4.print();
		item1.print();
		
		System.out.println("Is item1 equal to item2? " + item1.equals(item2)); 
		
		System.out.println("Is item2 equal to item3? " + item2.equals(item3));
		

		
	}
}
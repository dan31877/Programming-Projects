package ShoppingCart;
/**
 * Daniel Anderson
 * CS 232   Due: 12/11/2012
 * Assignment 7
 * Shoppable.java
 */
public interface Shoppable extends Nameable{
	
	/** Set method: Sets the priority  	*/
	public void setPriority(int p);
	
	/** Get method: Returns the priority  	*/
	public int getPriority();
	
	/** 
	 * Set method: Sets the price.  Method uses random and drops 
	 * all except two decimal points for accurate calculations  	
	 */
	public void setprice();
	
	/** Method to manually set the price */
	public void manualSetPrice(double p);
	
	/** Get method: Returns the price  	*/
	public double getPrice();

}

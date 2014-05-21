package ShoppingCart;
/**
 * Daniel Anderson
 * CS 232   Due: 12/11/2012
 * Assignment 7
 * Person.java
 */
public class Person implements Nameable, Printable{
	
	private String name; 
	
	// Default Constructor
	public Person(){ 
		
		name = "Name"; 
	}
	
	/**
	 * Constructor that takes a String parameter and sets to name
	 * @param n
	 */
	public Person(String n){ 
		
		setName(n);
	}
	/** Set method: Sets the person's name  	*/ 
	public void setName(String n){ 
		
		name = n; 
	}
	
	/** Get method: Returns the person's name  	*/
	public String getName(){
		
		return name;
	}

	
	/**  Returns a new Person, a person isn't really a copy*/
	public Person procreate(String n){ 
		
		Person p = new Person(n); 
		return p; 
	}
	
	/** Displays the person's name on the screen  */
	public void print(){
		
		System.out.print(name); 
	}
	


}

package ShoppingCart;
/**
 * Daniel Anderson
 * CS 232   Due: 12/11/2012
 * Assignment 7
 * ShoppingListGenerator.java
 */

public class ShoppingListGenerator {

	/**
	 * Main method
	 * 
	 * @param args
	 */
	public static void main(String[] args) throws Exception{ 
		UserInterface ui = new UserInterface(); // User interface handles user input
		ShoppingIO io = new ShoppingIO();  // Imports the inventory 
		double amount = 0.0; 	// Amount for budget
		// New person
		Person person = new Person((ui.getString("Please enter your name: "))); 
		BankAccount bankAccount = new BankAccount(200.00);  //New bank account
		
		// Initialize variables
		ShoppingLinkedList list = new ShoppingLinkedList(); 
		ShoppingLinkedList tomorrow = new ShoppingLinkedList(); 
		String name = ""; 
		int priority = 0, quantity = 1;
		boolean doneYet = false; 
		
		/**
		 * Displays the balance of the bank account. Gets a double from the user
		 * for the budget
		 */
		System.out.print("Your account balance is $");
		bankAccount.printBalance(); 
		System.out.println();
		// Gets a double from User Interface class 
		amount = ui.getDouble("Please enter the amount in your budget: $"); 
		
		/**
		 *  Prompts user for item names, priorities and quantities then adds 
		 *  them to the list.  It verifies item names don't contain numbers and 
		 *  the ints are ints.  Asks for user input to end the program or move on
		 *  to the next section.  Adds to quantity if two items are equal.
		 */
		System.out.println("We will be constructing a shopping list. " + 
				"Please type the names of the items, using only letters, \nthen type their priorities and the quantity, using integers.  " + 
				"\nType 'done' when the list is complete or 'exit' to exit.");
		

		while( !doneYet){ 
			try{ 
				boolean goOn = false; 
				
				// Checks the item name for numbers. Also checks if input is 
				// 'done' or 'exit'.
				while(!goOn ){
					
					// Gets a String from User Interface class 
					name = ui.getLine("Please enter the item: "); 				
					
					if( name.equalsIgnoreCase("done")){ 
						doneYet = true; 
						throw new Exception("The list is done."); 
					}
					
					// Exit criteria
					else if(name.equalsIgnoreCase("exit")){
						System.exit(0);
					} 
					else
						goOn = true;
				} 
				
				// Gets an int from User Interface class 
				priority = ui.getInt("Please enter a priority: "); 
				
				// Gets an int from User Interface class 
				quantity = ui.getInt("Please enter a quantity: ");
				
				ShoppingItem item = new ShoppingItem(name, quantity, priority); 
				io.getPrice(item); 
				
				/**
				 * Checks if the item is on the list. If so, asks the user if they 
				 * want to increase the quantity of the entry on the list. If the 
				 * item is not there, adds the item to the list.  
				 */
				if( list.checkItemOnList(item)){ 
					String n = ui.getString("This item is already on the list, would you like to increase the quantity you're buying? Y/N: "); 
					list.addToQuantity(item, n); 
				}
				else{ 
					list.insert(item);
				}
				
				// Checks that the item and priority is on the list. Asks the user
				// user if they want to add to the quantity of the item on the list.
				
				
			}catch(LetterNotFoundException e){ 
				System.out.println(e.getMessage());				
				
			}catch(Exception e){ 
				System.out.println(e.getMessage());				
			}
		}
		
		// Sorts the list
		list.sort();
		
		// Checks if the list is empty, exits the program
		if(list.isEmpty()){
			System.out.println("There are no items on the list. Closing program."); 
			System.exit(0); 
		}
		else{ 
			System.out.println("\n" + person.getName() + "'s Shopping List-");
			list.print();
		}		

		tomorrow = list.goShopping(tomorrow, amount, bankAccount);
		
		// Checks if the list is empty after the shopping is done
		if(list.isEmpty())
			System.out.println("There were no items purchased today.");
		
		else{
			System.out.println(person.getName() + "'s Shopping List: Items Purchased-"); 
			list.print(); 
		}
		
		// Checks if tomorrow's list is empty
		if(!tomorrow.isEmpty()){
			System.out.println(person.getName() + "'s Shopping List: Items Not Purchased-");
			tomorrow.print(); 
		}
	
		// Displays the balance of the account
		System.out.print("Your account balance is $");
		bankAccount.printBalance(); 
		System.out.println();
		
		// Closes any open scanners
		ui.closeScanner();
	}
}

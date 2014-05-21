package Database;

/**
 * Daniel Anderson
 * CS 565   Due: 4/8/2014
 * Homework 4
 * AccountDemo.java
 */

public class AccountDemo {


	/** Main method */
	public static void main(String[] args) {
		
		/** Instance variables */
		TransactionTable table = new TransactionTable(); 
		Account account1 = new Account("User1", 1000, table); 
		Account account2 = new Account("User2", 2500, table);
		Account account3 = new Account("User3", 4000, table);
		
		/** Accounts set to test deposit and withdraw methods.  */
		account1.print(); 
		account1.deposit(400);
		account1.withdraw(250); 
		account1.printAccountTransactions(); 
		account1.printBalance();
		
		account2.print(); 
		account2.deposit(1000);
		account2.deposit(600);
		account2.withdraw(2000);
		account2.deposit(700);
		account2.withdraw(100); 
		account2.printAccountTransactions(); 
		account2.printBalance();
		
		account3.print(); 
		account3.deposit(4000);
		account3.withdraw(2500); 
		account3.printAccountTransactions(); 
		account3.printBalance();
		
		/** Print all the transactions and close the Statement */
		System.out.println("All Transactions...");
		table.printTable(); 
		table.close(); 

	}

}

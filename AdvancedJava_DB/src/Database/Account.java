package Database;

/**
 * Daniel Anderson
 * CS 565   Due: 4/8/2014
 * Homework 4
 * Account.java
 */

public class Account {
	
	/** Instance variables */
	private int accountID; 
	private String username; 
	private int accountBalance;
	private TransactionTable table; 
	
	private static int IDMarker = 1000; // Auto increments to set the accountID
	
	/** Constructor: takes a String, int, and TransactionTable */
	public Account(String user, int balance, TransactionTable table){
		this.setAccountID(); 
		this.setUsername(user); 
		this.table = table; 
		this.setAccountBalance(balance);
	}
	
	/** Setters and Getters */
	public int getAccountID() {
		return accountID;
	}
	public void setAccountID() {
		this.accountID = getIDMarker();
		incrementID();
	}
	public static int getIDMarker() {
		return IDMarker;
	}

	public static void incrementID() {
		IDMarker++;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(int accountBalance) {
		this.accountBalance = accountBalance;
	} 
	
	/** Deposit method inserts a row into the TransactionTable and adds to the account balance */
	public void deposit(int amount){ 
		setAccountBalance(this.getAccountBalance() + amount);
		table.transaction(Integer.toString(getAccountID()), "deposit", amount); 
		System.out.println("Deposited $" + amount + " into account #" + this.getAccountID());
	}
	
	/** Withdraw method inserts a row into the TransactionTable and subtracts from the account balance */
	public void withdraw(int amount){
		setAccountBalance(this.getAccountBalance() - amount);
		table.transaction(Integer.toString(getAccountID()), "withdraw", amount);
		System.out.println("Withdrawen $" + amount + " from account #" + this.getAccountID());
	}
	
	/** Prints the account information */
	public void print(){
		System.out.println(this);
		System.out.println();
	}
	
	/** Prints the transactions for the account */
	public void printAccountTransactions(){
		System.out.println();
		table.printByAccount(accountID); 
	}
	
	/** Prints the balance for the account */
	public void printBalance(){ 
		System.out.println("Account Balance for account #" + this.getAccountID()  + ": $" + this.getAccountBalance() + "\n");
		
	}
	
	/** String representation of the account */
	public String toString(){
		return "Account information for account #" + this.getAccountID()  + "\t\tUser: " + this.getUsername() + "\tBalance: $" + this.getAccountBalance(); 
	}

}

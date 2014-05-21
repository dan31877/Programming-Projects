package ShoppingCart;
import java.text.DecimalFormat;

/**
 * Daniel Anderson
 * CS 232   Due: 12/11/2012
 * Assignment 7
 * BankAccount.java
 */

public class BankAccount {

	private double money = 0; // Money in the account
	
	// Default Constructor
	public BankAccount(){
		
		money = 100.00;
	}
	
	/**
	 * Constructor that takes a double 
	 * 
	 * @param d
	 */
	public BankAccount(double d){ 
		
		DecimalFormat df = new DecimalFormat("##.00");
		d = Double.parseDouble(df.format(d)); 
		deposit(d); 
	}
	
	/**
	 * Deposits the amount in the account.
	 * @param d
	 */
	public void deposit( double d){ 
		
		money = money + d;
		
	}
	
	/**
	 * Withdraws the money from the account.  
	 * @param d
	 */
	public void withdraw(double d){ 
		
		try{
			if( d > money)
				throw new Exception("Cannot withdraw more than your account balance."); 
			else
				money = money - d; 
		}catch(Exception e){
			
			System.out.println(e.getMessage()); 
		}
	}
	
	/**
	 * Returns the account balance
	 * @return
	 */
	public double getAccountBalance(){ 
		
		return money; 
	}
	
	/** Displays the balance on the screen */
	public void print(){
		
		System.out.println("Balance: " + money); 
	}
	
	/** Displays the balance on the screen */
	public void printBalance(){
		
		DecimalFormat df = new DecimalFormat("##.00");
		String m = df.format(money);
		System.out.print(m); 
	}
}

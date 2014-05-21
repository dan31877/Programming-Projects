package Database;

/**
 * Daniel Anderson
 * CS 565   Due: 4/8/2014
 * Homework 4
 * TransactionTable.java
 */

import java.sql.*;

public class TransactionTable {
	
	/** Instance variables */
	private String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	
	private String DB_URL = "jdbc:mysql://localhost:3306/cs565";
	private String DB_USERNAME = "cs";
	private String DB_PASSWORD = "java";
	
	private Statement stmt; 
	
	private String table = "Anderson_transactions";
	
	/** Constructor for the TransactionTable */
	public TransactionTable(){ 
		 
		try {
			
			// Establish a connection
			Class.forName(JDBC_DRIVER);
			
			Connection con = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);			
			stmt = con.createStatement();
			
			// Drop the table if it exists
			String sqlDropStatement = "DROP TABLE " + table;
			try {
				stmt.executeUpdate(sqlDropStatement);
			}
			catch (Exception e1)
			{
				System.out.println("Trying to drop the Transaction Table...");
			}
			
			// Create a new table 
			String sqlCreateStatement = "CREATE TABLE " + table + 
				"(TRANSACTION_ID integer auto_increment primary key," +
				"ACCOUNT_ID varchar(16)," + 
				"TRANSACTION_TYPE varchar(32)," + "AMOUNT integer)";

			stmt.executeUpdate(sqlCreateStatement);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** Method inserts withdraw and deposit transactions into the table */
	public void transaction(String acctID, String transactionType, int amount){ 
		
		try{
			String sql = "insert into " + table + "(ACCOUNT_ID, TRANSACTION_TYPE, AMOUNT) values ('" + acctID + "', '" + transactionType
																									+ "', " + amount + ")";
			stmt.executeUpdate(sql);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** Prints the contents of the whole table */
	public void printTable(){ 
		
		try{ 
			String sql = "Select * from " + table;
			ResultSet rs = stmt.executeQuery(sql);
			System.out.println(	"TRANSACTION_ID: \t ACCOUNT_ID: \t TRANSACTION_TYPE: \t AMOUNT: "); 
			
			while (rs.next())
			{
				System.out.printf("%2d\t\t\t %5s\t\t %10s\t\t %6d\n",
						rs.getInt("TRANSACTION_ID"),
						rs.getString("ACCOUNT_ID"),
						rs.getString("TRANSACTION_TYPE"), 
						rs.getInt("AMOUNT"));
			}
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/** Prints the transactions for the individual account in the parameter */
	public void printByAccount(int id){ 
		
		try{ 
			String sql = "Select * from " + table + " where ACCOUNT_ID = '" + Integer.toString(id) + "'";
			ResultSet rs = stmt.executeQuery(sql);
			System.out.println("Transactions...");
			System.out.println(	"\tTRANSACTION_ID: \t ACCOUNT_ID: \t TRANSACTION_TYPE: \t AMOUNT: "); 
			
			while (rs.next())
			{
				System.out.printf("\t%2d\t\t\t %5s\t\t %10s\t\t %6d\n",
						rs.getInt("TRANSACTION_ID"),
						rs.getString("ACCOUNT_ID"),
						rs.getString("TRANSACTION_TYPE"), 
						rs.getInt("AMOUNT"));
			}
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/** Closes the statement */
	public void close(){
		try{ 
			stmt.close();
					
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

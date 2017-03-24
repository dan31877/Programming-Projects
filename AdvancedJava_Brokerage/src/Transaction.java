package cs565.Final;

/**
 * Daniel Anderson
 * CS 565   Due: 4/8/2014
 * Homework 4
 * TransactionTable.java
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.Map.Entry;

public class Transaction {
	
	//Display name, ID, current balance, and the date account
	//Display the current portfolio (using JTable)
	//Display the portfolio at the end of any given day (using JTable)
	//Display the transaction history with the following choices for the transaction type: 
	//Buy/Sell/Deposit/All, and for the date range: From Date-ToDate/SingleDate (using JTable)
	
	/** Instance variables */
	private String customerID; 
	private int transactionDate; 
	private String transactionType; 
	private String stockSymbol; 
	private double quantity; 
	private double price; 
	private Account acct; 
	
	private static final double fee = 5.00; 
	
	/** Database and HashMap variables */
	private static Statement stmt; 
	private static String table = "Anderson_Transactions";
	private static HashMap<Transaction, String> map = new HashMap<Transaction, String>(); 
	private static Object[][] mapToJTable; 
	private static Object[] mapHeader = {"Customer_ID", "TransactionDate", "TransactionType", "StockSymbol", "Quantity", "Price"}; 
	
	/** Default Constructor */
	public Transaction(){ 

	}
	
	/** Constructor for the Transaction if the transaction is a deposit */
	public Transaction(Account acct, String transactionType, double quantity){ 
		this.customerID = acct.getCustomerID(); 
		this.transactionDate = Brokerage.getDate(); 
		this.transactionType = transactionType; 
		this.stockSymbol = "N/A"; 
		this.price = 0.0; 
		this.quantity = quantity; 
		this.acct = acct;
		
		acct.setBalance(acct.getBalance() + quantity);
		
		acct.getTransactionList().add(this);
		map.put(this, this.customerID); 
		
	}
	
	/** Constructor for the Transaction */
	public Transaction(Account acct, String transactionType, String stockSymbol, double quantity){ 
		this.customerID = acct.getCustomerID(); 
		this.transactionDate = Brokerage.getDate(); 
		this.transactionType = transactionType; 
		this.stockSymbol = stockSymbol; 
		this.price = this.getPrice(stockSymbol); 
		this.quantity = quantity; 
		this.acct = acct;
		
		boolean canAdd = false; 
		
		if(transactionType.equalsIgnoreCase("sell") && this.sell(stockSymbol, acct)){
			canAdd = true; 
		}
		else if(transactionType.equalsIgnoreCase("buy") && this.buy(acct)){
			canAdd = true; 
		}
		
		else{
			System.out.println("You've entered an invalid transaction type.");
		}
		
		if( canAdd){
			acct.getTransactionList().add(this);
			map.put(this, this.customerID); 
		} 
		
		else{ 
			System.out.println("You've entered an invalid transaction.");
		}
	}
	
	/** Checks to see if it's possible to make a sale */
	public boolean sell(String symbol, Account acct){ 
		
		double available = this.getStockQuantity(symbol, acct);
		if(this.quantity > available){ 
			return false;
		}
		else{ 
			 
			return true; 
		}

	}
	
	/** Checks to make sure it's possible to buy */
	public boolean buy(Account acct){
		
		double amount = (this.getPrice()*this.getQuantity()) + fee; 
		if( acct.computeBalance() < amount ){
			return false; 
		}
		else{ 
			return true; 
		}
	}
	
	public Account getAcct() {
		return acct;
	}

	public void setAcct(Account acct) {
		this.acct = acct;
	}

	/** Finds the quantity of a Stock the customer owns */ 
	public double getStockQuantity( String symbol, Account acct){ 
		double quantity = 0;
		
		for (int i = 0; i<acct.getTransactionList().size(); i++){ 
			if(acct.getTransactionList().get(i).getStockSymbol().equalsIgnoreCase(symbol)){
				quantity++; 
			}
		}
		
		return quantity; 
	}
	
	// Setters and Getters 
	
	public String getStockSymbol() {
		return stockSymbol;
	}

	public void setStockSymbol(String stockSymbol) {
		this.stockSymbol = stockSymbol;
	}

	public int getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(int transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public static Object[] getMapHeader() {
		return mapHeader;
	}

	public static void setMapHeader(Object[] mapHeader) {
		Transaction.mapHeader = mapHeader;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public static double getFee() {
		return fee;
	}
	
	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	/** Gets the price from the Stock table */
	public double getPrice(String symbol){ 
		
		stmt = Brokerage.getStmt();
		double result = 0; 
		try{ 
			String sql = "Select StockPrice from Anderson_Quotes where StockSymbol = '" + symbol + "'";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()){ 
				result = rs.getDouble("StockPrice");
			}
		} catch (Exception e1){
			System.out.println("Price not found...");
		}
		return result; 
	}
	///////////////////////////////////
	//								 //
	// Database and HashMap			 //
	//								 //
	///////////////////////////////////

	/** Prints the contents of the whole table */
	public static void printTable(){ 
		
		try{ 
			String sql = "Select * from " + table;
			ResultSet rs = stmt.executeQuery(sql);
			System.out.println(	"Customer_ID: \t TransactionDate: \t TransactionType: \t StockSymbol: \t Quantity: \t Price: "); 
			
			while (rs.next())
			{
				System.out.printf("%5s\t\t\t %10d\t\t %5s\t\t %5s\t\t %6.1f\t\t %6.2f\n",
						rs.getString("Customer_ID"),
						rs.getInt("TransactionDate"), 
						rs.getString("TransactionType"),
						rs.getString("StockSymbol"),
						rs.getDouble("Quantity"),
						rs.getDouble("Price"));
			}
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/** Searches the HashMap by id and adds to the list */
	public static void repopulateAcctList(){
		
		Set<Transaction> keys = map.keySet(); 
		Iterator<Transaction> maperator = keys.iterator();
		Account acct; 
		
		while(maperator.hasNext()){
			acct = maperator.next().getAcct(); 
			acct.getTransactionList().clear(); 
			String id = acct.getCustomerID(); 
	        for (Entry<Transaction, String> entry : map.entrySet()) {
	            if (entry.getValue().equals(id)) {
	                acct.getTransactionList().add(entry.getKey());
	            }
	        }
		}
	}
	
	/** Creates the Transactions table */
	public static void createTable(){ 
		// Drop the table if it exits
		stmt = Brokerage.getStmt();
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
			"(Customer_ID varchar(32)," + "TransactionDate integer," + "TransactionType varchar(32)," + 
			"StockSymbol varchar(32)," + "Quantity double," + "Price double)";
		
		try {
			stmt.executeUpdate(sqlCreateStatement);
		}
		catch (Exception e1)
		{
			System.out.println("Trying to drop the Transaction Table...");
		}
	}
	
	/** Add contents of the Map to the database */
	public static void addMapToDB(){ 
		
		// Create a key set and in Iterator for the Set
		Set<Transaction> keys = map.keySet(); 
		Iterator<Transaction> maperator = keys.iterator(); 
		
		stmt = Brokerage.getStmt();
		try{
			String sql = "Truncate table " + table;
			stmt.executeUpdate(sql);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		while(maperator.hasNext()){ 
			
			Transaction t = maperator.next(); 
			try{
				String sql = "insert into " + table + "(Customer_ID, TransactionDate, TransactionType, StockSymbol, Quantity, Price) values " +
						"('" + t.getCustomerID() + "', '" + t.getTransactionDate() + "', '" + t.getTransactionType() + "', '" + 
						t.getStockSymbol() + "', '" + t.getQuantity() + "', '" + t.getPrice() + "')";
				stmt.executeUpdate(sql);
			
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/** Add contents of database to the Map */
	public static void addDBtoMap(){ 
		
		map.clear(); 
		stmt = Brokerage.getStmt();
		Transaction t;
		
		try{ 
			String sql = "Select * from " + table;
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next())
			{
				t = new Transaction(); 
				 t.setCustomerID(rs.getString("Customer_ID"));
				 t.setTransactionDate(rs.getInt("TransactionDate")); 
				 t.setTransactionType(rs.getString("TransactionType")); 
				 t.setStockSymbol(rs.getString("StockSymbol"));
				 t.setQuantity(rs.getDouble("Quantity")); 
				 t.setPrice(rs.getDouble("Price")); 
				 
				 map.put(t, t.getCustomerID());  
			}
					
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** Displays all the Transactions in the Map */
	public static void printMap(){
		
		Set<Transaction> keys = map.keySet(); 
		Iterator<Transaction> maperator = keys.iterator(); 
		System.out.println(	"\nTRANSACTION MAP - "); 
		System.out.println(	"Customer_ID: \t TransactionDate: \t TransactionType: \t StockSymbol: \t Quantity: \t Price: "); 
		while(maperator.hasNext()){ 
			System.out.println(maperator.next());
		}
		
	}
	
	/** Adds the TransactionList to a double array to be populated into the JTable */ 
	public static Object[][] MapToDblArray(){ 
		
		mapToJTable = new Object[map.size()][6]; 
		Transaction t; 
		int i = 0; 
		
		Set<Transaction> keys = map.keySet(); 
		Iterator<Transaction> maperator = keys.iterator(); 
 
		while(maperator.hasNext()){ 
			t = maperator.next(); 
			mapToJTable[i][0] = t.getCustomerID(); 
			mapToJTable[i][1] = t.getTransactionDate();  
			mapToJTable[i][2] = t.getTransactionType(); 
			mapToJTable[i][3] = t.getStockSymbol(); 
			mapToJTable[i][4] = t.getQuantity(); 
			mapToJTable[i][5] = t.getPrice();
			i++; 
		
		}	
		return mapToJTable; 
	}
	
	
	/**  String representation of a Transaction object */
	public String toString(){
		return this.getCustomerID() + "\t" + this.getTransactionDate() + "\t" + this.getTransactionType() + "\t" + this.getStockSymbol()
				+ "\t" +  this.getQuantity() + "\t" + this.getPrice(); 
	}
	
	///////////////////////////////////
	//								 //
	// FILE IO						 //
	//								 //
	///////////////////////////////////
	
	/**  String representation of Transaction object to load into a text file */
	public String toFile(){
		return this.getCustomerID() + "," + this.getTransactionDate() + "," + this.getTransactionType() + "," + this.getStockSymbol()
				+ "," +  this.getQuantity() + "," + this.getPrice(); 
	}
	
	/**  Processes the data from the file into an Account object*/
	private static void processInputData(String data)
	{
		StringTokenizer st = new StringTokenizer(data, ",");
		String custID = st.nextToken(); 
		Account acct = new Account(); 
		
		
		for( int i = 0; i < Account.getAccountList().size(); i++){ 
			if(Account.getAccountList().get(i).getCustomerID().equalsIgnoreCase(custID)){
				acct = Account.getAccountList().get(i); 
			}
		} 
		String transactionDate = st.nextToken(); 
		String transactionType = st.nextToken(); 
		String stockSymbol = st.nextToken(); 
		String quantity = st.nextToken(); 
		String price = st.nextToken(); 
		Transaction t; 
		
		if(transactionType.equalsIgnoreCase("deposit")){
			t = new Transaction(acct, transactionType, Double.parseDouble(quantity));
		}
		else{
			t = new Transaction(acct, transactionType, stockSymbol, Double.parseDouble(quantity));
			t.setTransactionDate(Integer.parseInt(transactionDate));
			t.setPrice(Double.parseDouble(price));
		}

	}
	
	/**  Writes the AccountList into a file */
	public static void writeToFile()
	{
		Set<Transaction> keys = map.keySet(); 
		Iterator<Transaction> maperator = keys.iterator(); 
		
		String outputFileName = "transactions.txt";
		PrintWriter writer = null;
		// Create the PrintWriter object
		try {
			writer = new PrintWriter(outputFileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		// Write one line at a time to the file
		while (maperator.hasNext())
		{
			Transaction t = maperator.next();  
			writer.println(t.toFile()); 
		}
	    // Close the output
		writer.close();
		System.out.printf("Data file %s written\n", outputFileName);
	}	
	
	/** Reads from a text file and loads into AccountList */
	public static void readFromFile()
	{
		map.clear(); 
		String inputFileName = "transactions.txt";
		FileReader fileReader = null;
		// Create the FileReader object
		try {
			fileReader = new FileReader(inputFileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		BufferedReader reader = new BufferedReader(fileReader);
		String input;
		// Read one line at a time until end of file
		try {
			input = reader.readLine();
			while (input != null)
			{
				processInputData(input);
				input = reader.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Close the input
		try {
			fileReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		repopulateAcctList();
	}
	
	/** Closes the statement */
	public void close(){
		try{ 
			stmt.close();
					
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Object[][] TransTypeDBtoDblArray(String transType){ 
		
		
		int rowCount=0, i=0; 
		stmt = Brokerage.getStmt();
		addMapToDB(); 
		
		Object[][] queryArray; 
		
		String sql = "Select count(*) from " + table + " where TransactionType = '" + transType + "'"; 
		
		try{ 
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next())
			{
				 rowCount = Integer.parseInt(rs.getString("count(*)"));
				 System.out.println(rowCount);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		sql = "Select * from " + table + " where TransactionType = '" + transType + "'"; 
		queryArray = new Object[rowCount][6];
		try{
			  
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next())
			{
					
				queryArray[i][0] = rs.getString("Customer_ID"); 
				queryArray[i][1] = rs.getInt("TransactionDate"); 
				queryArray[i][2] = rs.getString("TransactionType"); 
				queryArray[i][3] = rs.getString("StockSymbol"); 
				queryArray[i][4] = rs.getDouble("Quantity"); 
				queryArray[i][5] = rs.getDouble("Price");
				i++;
			}
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		return queryArray;
	}
	
	/** Returns a double array of the search to load into the JTable */
	public static Object[][] TransDateDBtoDblArray(int startDate, int endDate){ 
		
		
		int rowCount=0, i=0; 
		stmt = Brokerage.getStmt();
		Object[][] queryArray; 
		
		String sql = "Select count(*) from " + table + " where TransactionDate >= " + startDate + " and TransactionDate <= " + endDate; 
		
		try{ 
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next())
			{
				 rowCount = Integer.parseInt(rs.getString("count(*)"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		sql = "Select * from " + table + " where TransactionDate >= " + startDate + " and TransactionDate <= " + endDate; 
		queryArray = new Object[rowCount][6];
		try{
			  
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next())
			{
				queryArray[i][0] = rs.getString("Customer_ID"); 
				queryArray[i][1] = rs.getInt("TransactionDate"); 
				queryArray[i][2] = rs.getString("TransactionType"); 
				queryArray[i][3] = rs.getString("StockSymbol"); 
				queryArray[i][4] = rs.getDouble("Quantity"); 
				queryArray[i][5] = rs.getDouble("Price");
				i++;
			}
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		return queryArray;
	}
	
	/** Returns a double array of the Stock Quotes table filtering by account  */
	public static Object[][] StockDBtoDblArray(Account acct){ 
		
		
		int rowCount=0, i=0; 
		String custID = acct.getCustomerID();
		stmt = Brokerage.getStmt();
		Object[][] queryArray; 
		
		String sql = "Select count(*) from Anderson_Quotes where StockSymbol in (Select distinct StockSymbol from Anderson_Transactions where Customer_ID = '" + custID + "')"; 
		
		try{ 
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next())
			{
				 rowCount = Integer.parseInt(rs.getString("count(*)"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		sql = "Select * from Anderson_Quotes where StockSymbol in (Select distinct StockSymbol from Anderson_Transactions where Customer_ID = '" + custID + "')";; 
		queryArray = new Object[rowCount][2];
		try{
			  
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next())
			{
				queryArray[i][0] = rs.getString("StockSymbol"); 
				queryArray[i][1] = rs.getDouble("StockPrice");
				i++;
			}
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		return queryArray;
	}
	
	/** Returns a double array of the Stock History table filtering by account  */
	public static Object[][] StockHistDBtoDblArray(Account acct){ 
		
		
		int rowCount=0, i=0; 
		String custID = acct.getCustomerID();
		stmt = Brokerage.getStmt();
		Object[][] queryArray; 
		
		String sql = "Select count(*) from Anderson_History where StockSymbol in (Select distinct StockSymbol from Anderson_Transactions where Customer_ID = '" + custID + "')"; 
		
		try{ 
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next())
			{
				 rowCount = Integer.parseInt(rs.getString("count(*)"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		sql = "Select * from Anderson_History where StockSymbol in (Select distinct StockSymbol from Anderson_Transactions where Customer_ID = '" + custID + "')";; 
		queryArray = new Object[rowCount][3];
		try{
			  
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next())
			{
				queryArray[i][0] = rs.getString("StockSymbol"); 
				queryArray[i][1] = rs.getInt("SDate");
				queryArray[i][2] = rs.getDouble("StockPrice");
				i++;
			}
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		return queryArray;
	}
	
	/** Returns a double array of the Transaction table filtering by account  */
	public static Object[][] TransDBtoDblArray(Account acct){ 
		
		
		int rowCount=0, i=0; 
		String custID = acct.getCustomerID();
		stmt = Brokerage.getStmt();
		Object[][] queryArray; 
		
		String sql = "Select count(*) from " + table + " where Customer_ID ='" + custID + "'"; 
		
		try{ 
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next())
			{
				 rowCount = Integer.parseInt(rs.getString("count(*)"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		sql = "Select * from " + table + " where Customer_ID ='" + custID + "'"; 
		queryArray = new Object[rowCount][6];
		try{
			  
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next())
			{
				queryArray[i][0] = rs.getString("Customer_ID"); 
				queryArray[i][1] = rs.getInt("TransactionDate"); 
				queryArray[i][2] = rs.getString("TransactionType"); 
				queryArray[i][3] = rs.getString("StockSymbol"); 
				queryArray[i][4] = rs.getDouble("Quantity"); 
				queryArray[i][5] = rs.getDouble("Price");
				i++;
			}
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		return queryArray;
	}
	
	/** Returns a double array of the Date Search filtering by account  */
	public static Object[][] TransDateDBtoDblArray(Account acct, int startDate, int endDate){ 
		
		
		int rowCount=0, i=0; 
		String custID = acct.getCustomerID();
		stmt = Brokerage.getStmt();
		Object[][] queryArray; 
		
		String sql = "Select count(*) from " + table + " where Customer_ID ='" + custID + "' and TransactionDate >= " + startDate + " and TransactionDate <= " + endDate; 
		
		try{ 
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next())
			{
				 rowCount = Integer.parseInt(rs.getString("count(*)"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		sql = "Select * from " + table + " where Customer_ID ='" + custID + "' and TransactionDate >= " + startDate + " and TransactionDate <= " + endDate; 
		queryArray = new Object[rowCount][6];
		try{
			  
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next())
			{
				queryArray[i][0] = rs.getString("Customer_ID"); 
				queryArray[i][1] = rs.getInt("TransactionDate"); 
				queryArray[i][2] = rs.getString("TransactionType"); 
				queryArray[i][3] = rs.getString("StockSymbol"); 
				queryArray[i][4] = rs.getDouble("Quantity"); 
				queryArray[i][5] = rs.getDouble("Price");
				i++;
			}
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		return queryArray;
	}
	
	/** Returns a double array of the Type search filtering by account  */
	public static Object[][] TransTypeDBtoDblArray(Account acct, String transType){ 
		
		
		int rowCount=0, i=0; 
		String custID = acct.getCustomerID(); 
		stmt = Brokerage.getStmt();
		addMapToDB(); 
		
		Object[][] queryArray; 
		
		String sql = "Select count(*) from " + table + " where Customer_ID = '" + custID + "' and TransactionType = '" + transType + "'"; 
		
		try{ 
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next())
			{
				 rowCount = Integer.parseInt(rs.getString("count(*)"));
				 System.out.println(rowCount);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		sql = "Select * from " + table + " where Customer_ID ='" + custID + "' and TransactionType = '" + transType + "'"; 
		queryArray = new Object[rowCount][6];
		try{
			  
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next())
			{
					
				queryArray[i][0] = rs.getString("Customer_ID"); 
				queryArray[i][1] = rs.getInt("TransactionDate"); 
				queryArray[i][2] = rs.getString("TransactionType"); 
				queryArray[i][3] = rs.getString("StockSymbol"); 
				queryArray[i][4] = rs.getDouble("Quantity"); 
				queryArray[i][5] = rs.getDouble("Price");
				i++;
			}
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		return queryArray;
	}
	
	//Main Method - for testing 
	public static void main(String[] args){
		
		Brokerage br = new Brokerage("go"); 
		br.connect(); 
		
		Account acct1 = new Account("ID1001", "Dan", 2000.00); 
		Account acct2 = new Account("ID1002", "Charyn", 1800.00); 
		Account acct3 = new Account("ID1003", "Karen", 4000.00); 
		Account acct4 = new Account("ID1004", "Keith", 5000.00);
		Account acct5 = new Account("ID1005", "Chris", 3000.00);
		
		Stock stock = new Stock(); 
		stock.createQuotes(); 
		stock.createHistory(); 
		
		stock.populateQuotes(); 
		stock.populateHistory(); 
		
		Transaction.createTable(); 
		System.out.printf("Acct2 balance: %6.2f\n", acct2.computeBalance()); 
		Transaction t1 = new Transaction(acct1, "buy", "AET", 3);
		Transaction t2 = new Transaction(acct2, "buy", "EMC", 2);
		Transaction t3 = new Transaction(acct2, "sell", "EMC", 1);
		Transaction t4 = new Transaction(acct3, "deposit", 500); 
		Transaction t5 = new Transaction(acct4, "buy", "CSCO", 4);
		Transaction t6 = new Transaction(acct5, "buy", "INTC", 5);
		Transaction t7 = new Transaction(acct4, "sell", "BAC", 1);
		Transaction t8 = new Transaction(acct3, "deposit", 1000); 
		Transaction t9 = new Transaction(acct2, "buy", "INTC", 5);
		Transaction t10 = new Transaction(acct4, "buy", "AMD", 4);
		Transaction t11 = new Transaction(acct2, "sell", "CSCO", 2);
		Transaction t12 = new Transaction(acct5, "deposit", 1500); 
		
		//acct2.printTransactions(); 
		//acct2.getTransactionList().clear(); 
		//acct2.printTransactions(); 
		//Transaction.repopulateAcctList(); 
		//acct2.printTransactions();
		
		System.out.printf("Acct2 balance: %6.2f\n", acct2.computeBalance());
		Transaction.printMap(); 
		Transaction.addMapToDB(); 
		Transaction.printTable();
		
		Transaction.writeToFile(); 
		Transaction.readFromFile();
		acct2.printTransactions(); 
		
	}
}


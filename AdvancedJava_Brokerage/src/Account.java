package cs565.Final;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Account {

	/** Database and List variables */
	private static String table = "Anderson_Accounts";
	private static ArrayList<Account> accountList = new ArrayList<Account>(); 
	private static Statement stmt;
	private static Object[] colNames = {"Customer_ID", "CustomerName", "OpeningDate", "OpeningBalance"}; 
	private static Object[][] accountToJTable; 
	private static Object[][] TListToJTable;
	private static DecimalFormat dec = new DecimalFormat("#.00");
	
	/** Instance variables */
	private String customerID; 
	private String customerName; 
	private int openingDate; 
	private double openingBalance; 
	private double balance; 
	private ArrayList<Transaction> transactionList = new ArrayList<Transaction>(); 
	
	/** Default Constructor */
	public Account(){ 
		
	}
	
	
	/** Creates an Account object and adds to the ArrayList */
	public Account(String id, String name, double balance){ 
		this.customerID = id; 
		this.customerName = name; 
		this.openingDate = Brokerage.getDate(); 
		this.openingBalance = balance; 

		accountList.add(this); 
	}
	
	/** Setters and Getters */
	public static ArrayList<Account> getAccountList() {
		return accountList;
	}

	public static void setAccountList(ArrayList<Account> accountList) {
		Account.accountList = accountList;
	}

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public int getOpeningDate() {
		return openingDate;
	}

	public void setOpeningDate(int openingDate) {
		this.openingDate = openingDate;
	}

	public double getOpeningBalance() {
		return openingBalance;
	}

	public void setOpeningBalance(double openingBalance) {
		this.openingBalance = openingBalance;
	}
	
	public double getBalance() {
		return balance;
	}


	public static Object[] getColNames() {
		return colNames;
	}


	public static void setColNames(Object[] colNames) {
		Account.colNames = colNames;
	}


	public static DecimalFormat getDec() {
		return dec;
	}


	public static void setDec(DecimalFormat dec) {
		Account.dec = dec;
	}


	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public ArrayList<Transaction> getTransactionList() {
		return transactionList;
	}


	public void setTransactionList(ArrayList<Transaction> transactionList) {
		this.transactionList = transactionList;
	}
	
	/** Calculates the balance of the account dynamically */
	public double computeBalance(){ 
		
		double balance = this.getOpeningBalance(); 
		
		for(int i = 0; i < this.getTransactionList().size(); i++){
			if( this.getTransactionList().get(i).getTransactionType().equalsIgnoreCase("sell")){
				balance = balance + ((this.getTransactionList().get(i).getPrice()*this.getTransactionList().get(i).getQuantity()) - Transaction.getFee());
			}
			else if( this.getTransactionList().get(i).getTransactionType().equalsIgnoreCase("buy")){
				balance = balance - ((this.getTransactionList().get(i).getPrice()*this.getTransactionList().get(i).getQuantity()) + Transaction.getFee());
			}
		}

		return balance; 
	}
	
	/** Finds the account given an ID */
	public static Account findAccount(String custID){ 
		
		Account acct = new Account(); 
		
		for( int i=0; i < accountList.size(); i++){ 
			String id = accountList.get(i).getCustomerID(); 
			if( custID.equalsIgnoreCase(id)){
				acct = accountList.get(i); 
				i = accountList.size(); 
			}
		}
		return acct; 
	}
	
	// TEST - Prints the transaction list
	public void printTransactions(){
		
		System.out.println(	"\nACCT TRANSACTIONS - "); 
		System.out.println(	"Customer_ID: \t TransactionDate: \t TransactionType: \t StockSymbol: \t Quantity: \t Price: ");
		for( int i=0; i < transactionList.size(); i++){ 
			System.out.println(transactionList.get(i)); 
		}
		
	}
	///////////////////////////////////
	//								 //
	// Database and ArrayList		 //
	//								 //
	///////////////////////////////////
	
	/** Creates a new table in the database, drops any existing */ 
	public static void createTable(){ 
		// Drop the table if it exists
		
		stmt = Brokerage.getStmt(); 
		String sqlDropTrans = "DROP TABLE Anderson_Transactions";
		String sqlDropStatement = "DROP TABLE " + table;
		try {
			stmt.executeUpdate(sqlDropTrans);
			stmt.executeUpdate(sqlDropStatement);
		}
		catch (Exception e1)
		{
			System.out.println("Trying to drop the Table...");
		}
					
		// Create a new table 
		String sqlCreateStatement = "CREATE TABLE " + table + 
			"(Customer_ID varchar(32) primary key," + "CustomerName varchar(32)," + 
			"OpeningDate integer," + "OpeningBalance double)";
		 
		
		try {
			stmt.executeUpdate(sqlCreateStatement);
			Transaction.createTable();
		}
		catch (Exception e1)
		{
			System.out.println("Cannot create Table...");
		}
	}
	
	/** Add contents of ArrayList to the database */
	public static void addListToDB(){ 
		
		stmt = Brokerage.getStmt();
		
		for(int i = numbRows(); i < accountList.size(); i++){ 
			Account acct = accountList.get(i); 
			
			try{
				String sql = "insert into " + table + "(Customer_ID, CustomerName, OpeningDate, OpeningBalance) values " +
						"('" + acct.getCustomerID() + "', '" + acct.getCustomerName() + "', '" + acct.getOpeningDate() + "', '" + acct.openingBalance + "')";
				stmt.executeUpdate(sql);
			
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	/** Adds the AccountList to a double array to be populated into the JTable */ 
	public static Object[][] listToDblArray(){ 
		
		accountToJTable = new Object[accountList.size()][4]; 
		
		for(int i = 0; i < accountList.size(); i++){ 
			accountToJTable[i][0] = accountList.get(i).getCustomerID();
			accountToJTable[i][1] = accountList.get(i).getCustomerName(); 
			accountToJTable[i][2] = accountList.get(i).getOpeningDate(); 
			accountToJTable[i][3] = dec.format(accountList.get(i).getOpeningBalance());
		}
		
		//System.out.println(accountToJTable.length);
		return accountToJTable; 
	}
	
	/** Adds the TransactionList to a double array to be populated into the JTable */ 
	public void TlistToDblArray(){ 
		
		TListToJTable = new Object[transactionList.size()][6]; 
		
		for(int i = 0; i < transactionList.size(); i++){ 
			TListToJTable[i] = new Object[]{transactionList.get(i).getCustomerID(), transactionList.get(i).getTransactionDate(), 
					transactionList.get(i).getTransactionType(), transactionList.get(i).getStockSymbol(), transactionList.get(i).getQuantity(),
					transactionList.get(i).getPrice()};
		}	
	}
	
	/** Add contents of database to the ArrayList */
	public static void addDBtoList(){ 
		
		accountList.clear(); 
		stmt = Brokerage.getStmt();
		Account acct;
		
		try{ 
			String sql = "Select * from " + table;
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next())
			{
				acct = new Account(); 
				 acct.setCustomerID(rs.getString("Customer_ID"));
				 acct.setCustomerName(rs.getString("CustomerName")); 
				 acct.setOpeningDate(rs.getInt("OpeningDate")); 
				 acct.setOpeningBalance(rs.getDouble("OpeningBalance")); 
				 
				 accountList.add(acct); 
			}
					
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**  Queries the database to calculate the number of rows in the table */
	public static int numbRows(){ 
		stmt = Brokerage.getStmt();
		int rowCount = 0; 
		
		try{ 
			String sql = "Select count(*) from " + table;
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next())
			{
				 rowCount = Integer.parseInt(rs.getString("count(*)"));
			}
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return rowCount; 
	}
	/** Closes the statement */
	public void close(){
		try{ 
			stmt.close();
					
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Prints the contents of the whole table - for testing  
	public static void printTable(){ 
		
		try{ 
			String sql = "Select * from " + table;
			ResultSet rs = stmt.executeQuery(sql);
			System.out.println(	"Customer_ID: \t CustomerName: \t OpeningDate: \t OpeningBalance: "); 

			while (rs.next())
			{
				System.out.printf("%5s\t\t %10s\t\t %2d\t\t %6.2f\n",
						rs.getString("Customer_ID"),
						rs.getString("CustomerName"), 
						rs.getInt("OpeningDate"),
						rs.getDouble("OpeningBalance"));
			}
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//Prints from the AccountList - for testing  
	public static void printList(){ 
		
		System.out.println("\nAccountList - ");
		System.out.println(	"Customer_ID: \t CustomerName: \t OpeningDate: \t OpeningBalance: "); 
		for( int i=0; i < accountList.size(); i++){ 
			System.out.println(accountList.get(i)); 
		}
	}
	
	/** String representation of an Account object */
	public String toString(){
		
		return this.getCustomerID() + "\t" + this.getCustomerName() + "\t" + this.getOpeningDate() + "\t" + this.getOpeningBalance(); 
	
	}
	
	///////////////////////////////////
	//								 //
	// FILE IO						 //
	//								 //
	///////////////////////////////////
	
	/**  String representation of an Account object to load into a text file */
	public String toFile(){
		return this.getCustomerID() + "," + this.getCustomerName() + "," + this.getOpeningDate() + "," + this.getOpeningBalance(); 
	}
	
	/**  Processes the data from the file into an Account object*/
	private static void processInputData(String data)
	{
		StringTokenizer st = new StringTokenizer(data, ",");
		String customerID = st.nextToken();
		String customerName = st.nextToken();
		String openingDate = st.nextToken(); 
		String openingBalance 	= st.nextToken();
		Account acct = 
			new Account(customerID, customerName, Double.parseDouble(openingBalance));
		acct.setOpeningDate(Integer.parseInt(openingDate)); 
		//System.out.println(currentEmployee.displayInfo());
	}
	
	/**  Writes the AccountList into a file */
	public static void writeToFile()
	{
		String outputFileName = "accounts.txt";
		PrintWriter writer = null;
		// Create the PrintWriter object
		try {
			writer = new PrintWriter(outputFileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		// Write one line at a time to the file
		for(int i = 0; i < accountList.size(); i++){ 
			writer.println(accountList.get(i).toFile()); 
		}
	    // Close the output
		writer.close();
		System.out.printf("Data file %s written\n", outputFileName);
	}	
	
	/** Reads from a text file and loads into AccountList */
	public static void readFromFile()
	{
		accountList.clear(); 
		String inputFileName = "accounts.txt";
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
	}
	
	//Main Method - for testing 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Brokerage br = new Brokerage("go"); 
		br.connect(); 
		Account.createTable();
		
		Account acct1 = new Account("ID1001", "Dan", 2000.00); 
		Account acct2 = new Account("ID1002", "Charyn", 1800.00); 
		Account acct3 = new Account("ID1003", "Karen", 4000.00); 
		Account acct4 = new Account("ID1004", "Keith", 5000.00);
		Account acct5 = new Account("ID1005", "Chris", 3000.00);
		
		 
		Account.printList(); 
		Account.writeToFile(); 
		Account.readFromFile(); 
		Account.printList();
		
		Account.addListToDB(); 
		Account.addDBtoList(); 
		Account.addListToDB();
		Account.printTable(); 
		
		System.out.println("Number of Rows: " + Account.numbRows());
		
		System.out.println(Account.listToDblArray().length); 
	}

}

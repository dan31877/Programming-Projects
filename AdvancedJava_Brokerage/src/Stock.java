package cs565.Final;

import java.sql.ResultSet;
import java.sql.Statement;

public class Stock {

	private String quotes = "Anderson_Quotes";
	private String history = "Anderson_History";

	// EMC, Bank of America, Aetna, Cisco, AMD, Intel: prices from 4/24/14
	private String[] symbols = {"EMC", "BAC", "AET", "CSCO", "AMD", "INTC"}; 
	private double[] price = {25.91, 16.32, 68.91, 26.42, 24.25, 26.75}; 
	private Object[] quotesHeader = {"Symbols", "Price"}; 
	private Object[] historyHeader = {"Symbols", "Date", "Price"};
	private Object[][] quotesDArray = new Object[6][2];
	private Object[][] historyDArray = new Object[30][3];
	
	private Statement stmt = Brokerage.getStmt();
	
	private int date = Brokerage.getDate(); 
	
	/** Setters and Getters */
	public Object[] getQuotesHeader() {
		return quotesHeader;
	}

	public void setQuotesHeader(Object[] quotesHeader) {
		this.quotesHeader = quotesHeader;
	}

	public Object[] getHistoryHeader() {
		return historyHeader;
	}

	public void setHistoryHeader(Object[] historyHeader) {
		this.historyHeader = historyHeader;
	}

	public Object[][] getQuotesDArray() {
		return quotesDArray;
	}

	public void setQuotesDArray(Object[][] quotesDArray) {
		this.quotesDArray = quotesDArray;
	}

	public Object[][] getHistoryDArray() {
		return historyDArray;
	}

	public void setHistoryDArray(Object[][] historyDArray) {
		this.historyDArray = historyDArray;
	}
	
	/** Populates the stock quotes table with random stocks and their prices */
	public void populateQuotes(){ 
		
		for(int i = 0; i<price.length; i++){
			
			quotesDArray[i] = new Object[]{symbols[i], price[i]};
			try{

				String sql = "insert into " + quotes + "(StockSymbol, StockPrice) values " +
						"('" + symbols[i] + "', '" + price[i] + "')";
				stmt.executeUpdate(sql);
			
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/** Populates the StockHistory table with randomly generated price fluctuations. */
	public void populateHistory(){ 
		
		int plusMinus = 0; 
		int daysBack = 5;
		int percentage = 0; 
		double prc = 0; 
		int dt = 0; 
		int arrayCount = 0;
		
		for(int i = 0; i < daysBack; i++){ 
			for( int j = 0; j < symbols.length; j++){ 
				plusMinus = (int)(Math.random()*2) + 1;
				percentage = (int)(Math.random()*6); 
				dt = date - i; 
				
				if(plusMinus == 1){ 
					prc = price[j] + price[j]*percentage*0.01; 
				}
				else{ 
					prc = price[j] - price[j]*percentage*0.01;
				}  
				historyDArray[arrayCount++] = new Object[]{symbols[j], dt, Account.getDec().format(prc)}; 
				
				try{
					String sql = "insert into " + history + " (StockSymbol, SDate, StockPrice) values " +
							"('" + symbols[j] + "', '" + dt + "', '" + prc + "')";
					stmt.executeUpdate(sql);
				
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		
	}
	
	/** Creates the StockQuotes table */
	public void createQuotes(){ 
		// Drop the table if it exists
		String sqlDropStatement = "DROP TABLE " + quotes;
		try {
			stmt.executeUpdate(sqlDropStatement);
		}
		catch (Exception e1)
		{
			System.out.println("Trying to drop the Table...");
		}
					
		// Create a new table 
		String sqlCreateStatement = "CREATE TABLE " + quotes + 
			"(StockSymbol varchar(32) primary key," + "StockPrice double)";
		
		try {
			stmt.executeUpdate(sqlCreateStatement);
		}
		catch (Exception e1)
		{
			System.out.println("Cannot create Table...");
		}
	}
	
	/** Creates the StockHistory table */
	public void createHistory(){ 
		// Drop the table if it exists
		String sqlDropStatement = "DROP TABLE " + history;
		try {
			stmt.executeUpdate(sqlDropStatement);
		}
		catch (Exception e1)
		{
			System.out.println("Trying to drop the Table...");
		}
					
		// Create a new table 
		String sqlCreateStatement = "CREATE TABLE " + history + 
				"(StockSymbol varchar(32)," + "SDate integer," + "StockPrice double)";
		
		try {
			stmt.executeUpdate(sqlCreateStatement);
		}
		catch (Exception e1)
		{
			System.out.println("Cannot create Table...");
		}
	}
	
	/** Prints the contents of the whole table */
	public void printTable(){ 
		
		try{ 
			String sql = "Select * from " + quotes;
			ResultSet rs = stmt.executeQuery(sql);
			System.out.println("QUOTES");
			System.out.println(	"StockSymbol: \t StockPrice: "); 
			
			while (rs.next())
			{
				System.out.printf("%4s\t\t\t %5.2f\t\t\n",
						rs.getString("StockSymbol"),
						rs.getDouble("StockPrice"));
			}
			
			sql = "Select * from " + history;
			rs = stmt.executeQuery(sql);
			System.out.println("\nHISTORY");
			System.out.println(	"StockSymbol: \t SDate: \t StockPrice: "); 
			
			while (rs.next())
			{
				System.out.printf("%4s\t\t\t %10d\t\t %6.2f\n",
						rs.getString("StockSymbol"),
						rs.getInt("SDate"),
						rs.getDouble("StockPrice"));
			}
					
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//Main Method - for testing  
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			
			Brokerage br = new Brokerage();  
			br.connect(); 
			Stock stock = new Stock();
			stock.createQuotes(); 
			stock.createHistory(); 
			
			stock.populateQuotes(); 
			stock.populateHistory(); 
			stock.printTable(); 
	}

}

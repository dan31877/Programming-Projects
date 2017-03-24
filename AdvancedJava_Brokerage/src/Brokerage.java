package cs565.Final;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Calendar;
import javax.swing.BoxLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Brokerage extends JFrame{

	/** SQL Connection vaiables */
	private String JDBC_DRIVER = "com.mysql.jdbc.Driver"; 
	private String DB_URL = "jdbc:mysql://localhost:3306/cs565";
	private String DB_USERNAME = "cs";
	private String DB_PASSWORD = "java";
	private static Statement stmt; 
	
	
	/** JFrame Components */
	// Account
	private Account acct; 
	private AccountListener accountListener;
	private TableListener tableListener; 
	private DefaultTableModel acctTableModel;
	private JScrollPane aScroll; 
	private JPanel accountPanel; 
	private JPanel buttonPanel; 
	private JButton add; 
	private JButton save;
	private JButton transactionButton; 
	private JButton stockButton; 
	private JTable account; 
	private JFrame accountFrame; 
	private JTextField customerId; 
	private JTextField customerName;
	private JTextField openDate;
	private JTextField startingPrice;
	private JButton jbtOk;
	
	// Transaction
	private HomeListener homeListener;
	private TransactionListener transactionListener; 
	private ComboListener comboListener; 
	private JPanel transactionPanel; 
	private JPanel userAndButtons; 
	private JScrollPane tScroll; 
	private JPanel transButtons; 
	private DefaultTableModel transTableModel;
	private JTable transaction; 
	private JButton tHome; 
	private JButton refresh; 
	private JTextField startDate; 
	private JLabel start;
	private JTextField endDate; 
	private JLabel end;
	private JButton search; 
	private String[] data = {"buy", "sell", "deposit"};
	private JComboBox<String> myBox;
	private JTable transactionDt; 
	private JTable transactionCombo;
	private JScrollPane tScroll2;
	private JScrollPane tScroll3;
	private JTextField transactionType;
	private JTextField stockSymbol;
	private JTextField quantity;
	private JButton addTrans; 
	private JButton addTransaction; 
	private boolean isSearched; 
	private boolean isSearchedCombo; 
	
	// Stock 
	private Stock stock; 
	private JPanel stockPanel;
	private JPanel quotePanel; 
	private JScrollPane qScroll; 
	private JPanel historyPanel; 
	private JScrollPane hScroll; 
	private JPanel stockButtons; 
	private JLabel historyLabel; 
	private JLabel quoteLabel; 
	private JButton homeStock; 
	private JTable quote; 
	private JTable history; 
	
	// User
	private JPanel userPanel; 
	private JPanel userAndTrans; 
	private JLabel uCustId; 
	private JTextField uID; 
	private JLabel uCustName; 
	private JTextField uName; 
	private JLabel uBalance; 
	private JTextField uBal; 
	private JLabel uOpeningDate; 
	private JTextField uDate;  
	
	// GUI Setup
	private JPanel topPanel; 
	private JPanel labelPanel; 
	private JLabel topLabel; 
	private JMenu menu; 
	private MenuListener menuListener; 
	private JMenuItem loadFromFile; 
	private JMenuItem loadFromDB; 
	private JMenuItem saveToDB; 
	private JMenuItem saveToFile; 
	
	// Constructor used for testing
	public Brokerage(String str){
		
	}
	
	/** Default Constructor */
	public Brokerage(){ 
		
		super("MET Brokerage"); 
		
		// Set up tables
		this.connect(); 
		Account.createTable(); 
		Account.readFromFile();
		//Account.addListToDB(); 
		
		//Transaction.createTable(); 
		Transaction.readFromFile(); 
		Transaction.addMapToDB(); 
		
		stock = new Stock(); 
		stock.createQuotes(); 
		stock.createHistory(); 
		
		stock.populateQuotes(); 
		stock.populateHistory(); 
		
		///////////////////////////////////
		//								 //
		// GUI Setup					 //
		//								 //
		///////////////////////////////////
		
		topPanel = new JPanel(); 
		menuListener = new MenuListener();
	    menu = new JMenu("File");
	    menu.setForeground(Color.WHITE); 
	    loadFromFile = new JMenuItem("Load From File");
	    loadFromFile.addActionListener(menuListener); 
	    menu.add(loadFromFile);
	    loadFromDB= new JMenuItem("Load From Database");
	    loadFromDB.addActionListener(menuListener);
	    menu.add(loadFromDB);
	    saveToDB = new JMenuItem("Save to Database");
	    saveToDB.addActionListener(menuListener);
	    menu.add(saveToDB);
	    saveToFile = new JMenuItem("Save to File");
	    saveToFile.addActionListener(menuListener);
	    menu.add(saveToFile);
        JMenuBar bar = new JMenuBar();
        bar.add(menu);
        bar.setBackground(Color.BLUE); 
        bar.setForeground(Color.WHITE); 
        add(bar, BorderLayout.NORTH);
		
    	///////////////////////////////////
    	//								 //
    	// Account						 //
    	//								 //
    	///////////////////////////////////
        
        accountPanel = new JPanel(); 
        acctTableModel = new DefaultTableModel(Account.listToDblArray(), Account.getColNames());
        accountPanel.setSize(800, 600); 
        buttonPanel = new JPanel(); 
        accountListener = new AccountListener();
        tableListener = new TableListener(); 
        add = new JButton("Add User"); 
        add.addActionListener(accountListener); 
        save = new JButton("Save to DB"); 
        save.addActionListener(accountListener); 
        transactionButton = new JButton("Transaction History");
        transactionButton.addActionListener(accountListener); 
        account = new JTable(acctTableModel);
        account.setPreferredScrollableViewportSize(new Dimension(500, 500)); 
        account.setFillsViewportHeight(true); 
        account.setGridColor(Color.BLACK);
        account.setShowGrid(true); 
        account.addMouseListener(tableListener); 
        buttonPanel.add(add); 
		buttonPanel.add(save);
		//buttonPanel.add(transactionButton);  
		add(account, BorderLayout.CENTER); 
		aScroll = new JScrollPane(account); 
		add(aScroll);
		add(buttonPanel, BorderLayout.SOUTH); 
		
	}
		
	// Setter and Getters
	public static Statement getStmt() {
		return stmt;
	}

	public static void setStmt(Statement stmt) {
		Brokerage.stmt = stmt;
	}
	
	/** Launches the Stock/Portfolio GUI and Tables */ 
	public void launchStock(){
	
		stockPanel = new JPanel(); 
		stockPanel.setLayout(new BoxLayout(stockPanel, BoxLayout.Y_AXIS)); 
		quotePanel = new JPanel(); 
		historyPanel = new JPanel(); 
		stockButtons = new JPanel(); 
		homeStock = new JButton("Back"); 
		homeStock.addActionListener(homeListener);
		stockButtons.add(homeStock); 
		historyLabel = new JLabel("Portfolio: Stock History"); 
		quoteLabel = new JLabel("Portfolio: Stocks Owned"); 
        quote = new JTable(Transaction.StockDBtoDblArray(acct), stock.getQuotesHeader());
        quote.setPreferredScrollableViewportSize(new Dimension(300, 120)); 
        quote.setFillsViewportHeight(true); 
        quote.setGridColor(Color.BLACK);
        quote.setShowGrid(true);
        quotePanel.add(quote); 
        qScroll = new JScrollPane(quote); 
        quotePanel.add(qScroll); 
        history = new JTable(Transaction.StockHistDBtoDblArray(acct), stock.getHistoryHeader());
        history.setPreferredScrollableViewportSize(new Dimension(300, 350)); 
        history.setFillsViewportHeight(true); 
        history.setGridColor(Color.BLACK);
        history.setShowGrid(true);
        historyPanel.add(history); 
        hScroll = new JScrollPane(history); 
        historyPanel.add(hScroll);
        stockPanel.add(quoteLabel); 
        stockPanel.add(quotePanel); 
        stockPanel.add(historyLabel); 
        stockPanel.add(historyPanel);
	}

	/** Launches the Transaction GUI and Tables*/
	public void launchTransaction(){ 
		
		isSearched = false; 
		userPanel = new JPanel(new GridLayout( 2,4)); 
		uCustId = new JLabel("Customer ID: ");
		userPanel.add(uCustId); 
		uID = new JTextField(16); 
		uID.setText(acct.getCustomerID()); 
		uID.setEditable(false);
		userPanel.add(uID); 
		uCustName = new JLabel("Customer Name: "); 
		userPanel.add(uCustName); 
		uName= new JTextField(16); 
		uName.setText(acct.getCustomerName()); 
		uName.setEditable(false);
		userPanel.add(uName);
		uBalance = new JLabel("Balance: ");
		userPanel.add(uBalance); 
		uBal =  new JTextField(16); 
		double bal = acct.computeBalance(); 
		uBal.setText(Double.toString(bal)); 
		uBal.setEditable(false);
		userPanel.add(uBal);
		uOpeningDate = new JLabel("Opening Date: "); 
		userPanel.add(uOpeningDate); 
		uDate = new JTextField(16); 
		uDate.setText(Integer.toString(acct.getOpeningDate())); 
		uDate.setEditable(false);
		userPanel.add(uDate);
		
		userAndButtons = new JPanel();
		userAndTrans = new JPanel(); 
		transactionPanel = new JPanel(); 
		//transactionPanel.setSize(800, 600); 
		homeListener = new HomeListener(); 
		comboListener = new ComboListener(); 
		transactionListener = new TransactionListener(); 
		transButtons = new JPanel(); 
		addTransaction = new JButton("Add"); 
		addTransaction.addActionListener(accountListener);
		tHome = new JButton("Home");
		tHome.addActionListener(homeListener);
		myBox = new JComboBox<String>(data);
		myBox.setSelectedIndex(0); 
		myBox.addActionListener(comboListener); 
		start = new JLabel("Start Date:"); 
		startDate = new JTextField(8); 
		end = new JLabel("End Date:"); 
		endDate = new JTextField(8); 
		search = new JButton("Search"); 
		search.addActionListener(transactionListener);
        stockButton = new JButton("Portfolio");
        stockButton.addActionListener(accountListener); 
        refresh = new JButton("Refresh"); 
        refresh.addActionListener(accountListener);
        transTableModel = new DefaultTableModel(Transaction.TransDBtoDblArray(acct), Transaction.getMapHeader());
        transaction = new JTable(transTableModel); 
		transaction.setPreferredScrollableViewportSize(new Dimension(500, 500)); 
		transaction.setFillsViewportHeight(true); 
		transaction.setGridColor(Color.BLACK);
		transaction.setShowGrid(true); 
		transButtons.add(addTransaction); 
		transButtons.add(start); 
		transButtons.add(startDate); 
		transButtons.add(end); 
		transButtons.add(endDate);
		transButtons.add(search); 
		transButtons.add(myBox); 
		transButtons.add(tHome);
		transButtons.add(stockButton); 
		transButtons.add(refresh); 
	}
	
	/** Provides a connection to the database */
	public void connect(){ 
		
		try{ 
			Class.forName(JDBC_DRIVER);
			Connection con = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);			
			stmt = con.createStatement();			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Connected...");
	}
	
	/** Returns an int representation of the current date */
	public static int getDate(){ 
		
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int day = cal.get(Calendar.DATE);
		return year*10000 + month * 100 + day; 
	}
	
	/** Popup window to add new users */
	 public void AccountDialogBox(){
		  customerId = new JTextField(8); 
		  customerName = new JTextField(8);
		  openDate = new JTextField(8);
		  startingPrice = new JTextField(8);
		  jbtOk = new JButton("Add User");
		    
		  accountFrame = new JFrame("Add New Account");
	      JPanel p1 = new JPanel( new GridLayout( 4,2));
	      p1.add(new JLabel("Customer ID"));
	      p1.add(customerId);
	      p1.add(new JLabel("Customer Name"));
	      p1.add(customerName); 
	      p1.add(new JLabel("Open Date"));
	      openDate.setText(Integer.toString(Brokerage.getDate())); 
	      openDate.setEditable(false); 
	      p1.add(openDate);
	      p1.add(new JLabel("Starting Price"));
	      p1.add(startingPrice); 
	      JPanel p2 = new JPanel( new FlowLayout(FlowLayout.CENTER));
	      p2.add(jbtOk); 
	      jbtOk.addActionListener(accountListener);
	      accountFrame.add(p1, BorderLayout.CENTER); 
	      accountFrame.add(p2, BorderLayout.SOUTH); 
	      accountFrame.setSize(400, 150);
	      accountFrame.setVisible(true);
	      accountFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 }
	
	 /** Popup to add new transactions  */
	 public void TransDialogBox(){
		 
		  customerId = new JTextField(8); 
		  transactionType = new JTextField(8);
		  stockSymbol = new JTextField(8);
		  quantity = new JTextField(8);
		  addTrans = new JButton("Add Transaction");
		    
		  accountFrame = new JFrame("Add New Transaction");
	      JPanel p1 = new JPanel( new GridLayout( 4,2));
	      p1.add(new JLabel("Customer ID"));
	      customerId.setText(acct.getCustomerID()); 
	      p1.add(customerId);
	      p1.add(new JLabel("Transaction Type"));
	      p1.add(transactionType); 
	      p1.add(new JLabel("Stock Symbol"));
	      p1.add(stockSymbol);
	      p1.add(new JLabel("Quantity"));
	      p1.add(quantity); 
	      JPanel p2 = new JPanel( new FlowLayout(FlowLayout.CENTER));
	      p2.add(addTrans); 
	      addTrans.addActionListener(accountListener);
	      accountFrame.add(p1, BorderLayout.CENTER); 
	      accountFrame.add(p2, BorderLayout.SOUTH); 
	      accountFrame.setSize(400, 150);
	      accountFrame.setVisible(true);
	      accountFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      userAndTrans.add(transaction, BorderLayout.CENTER); 
	      userAndTrans.add(userPanel, BorderLayout.SOUTH);
	      tScroll = new JScrollPane(transaction);
	      userAndTrans.add(tScroll);
	      add(userAndTrans, BorderLayout.CENTER); 
	      add(transButtons, BorderLayout.SOUTH); 
	      double bal = acct.computeBalance(); 
	      uBal.setText(Double.toString(bal)); 
	      
	 }
	 
	// Main Method 
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Brokerage firm = new Brokerage(); 
		firm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		firm.setSize(900, 700);
		firm.setVisible(true);
	}

	/** Action Listener mainly used for the buttons  */
	private class AccountListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource() == add){ 
				AccountDialogBox(); 
			}
			else if(e.getSource() == addTransaction){ 
				TransDialogBox(); 
			}
			else if( e.getSource() == save){
				Account.addListToDB(); 
				Transaction.addMapToDB();
				JOptionPane.showMessageDialog(null, "Databases have been updated.");
			}
			else if( e.getSource() == transactionButton){ 
				remove(account); 
				remove(aScroll); 
				remove(buttonPanel);
				startDate.setText(""); 
				endDate.setText(""); 
				userAndTrans.add(transaction, BorderLayout.CENTER); 
				userAndTrans.add(userPanel, BorderLayout.SOUTH);
				tScroll = new JScrollPane(transaction);
				userAndTrans.add(tScroll);
				add(userAndTrans, BorderLayout.CENTER); 
				add(transButtons, BorderLayout.SOUTH); 
				double bal = acct.computeBalance(); 
				uBal.setText(Double.toString(bal)); 
				revalidate();
			}
			else if( e.getSource() == stockButton){
				if(isSearched){ 
					startDate.setText(""); 
					endDate.setText("");
					remove(transactionDt); 
					remove(tScroll2);
					isSearched = false; 
				}else if(isSearchedCombo){ 
					remove(transactionCombo); 
					remove(tScroll3);
					isSearchedCombo = false; 
				}else{
					remove(userAndTrans); 
					remove(tScroll);	
				}
				remove(transButtons);
				launchStock(); 
		        add(stockPanel, BorderLayout.CENTER); 
				add(stockButtons, BorderLayout.SOUTH); 
				revalidate();
			}
			
			else if( e.getSource() == refresh){
				if(isSearched){ 
					startDate.setText(""); 
					endDate.setText("");
					remove(transactionDt); 
					remove(tScroll2);
					isSearched = false; 
				}else if(isSearchedCombo){ 
					remove(transactionCombo); 
					remove(tScroll3);
					isSearchedCombo = false; 
				}else{
					remove(userAndTrans); 
					remove(tScroll);	
				}
				remove(transButtons);
				add(transaction, BorderLayout.CENTER); 
				userAndTrans.add(transaction, BorderLayout.CENTER); 
				userAndTrans.add(userPanel, BorderLayout.SOUTH);
				tScroll = new JScrollPane(transaction);
				userAndTrans.add(tScroll);
				add(userAndTrans, BorderLayout.CENTER);
				add(transButtons, BorderLayout.SOUTH);
				revalidate();
			}
			else if( e.getSource() == jbtOk){
				String custID = customerId.getText(); 
				String custName = customerName.getText();
				int date = Integer.parseInt(openDate.getText());
				double price = Double.parseDouble(startingPrice.getText()); 
				
				Object[] rowData = {custID, custName, date, price}; 
				acctTableModel.addRow(rowData); 
				
				Account acct = new Account(custID, custName, price); 
				acct.setOpeningDate(date); 
				Account.writeToFile(); 
				JOptionPane.showMessageDialog(null, "Account has been added.");
				accountFrame.dispose(); 
				
			}
			
			else if( e.getSource() == addTrans){
				
				String custID = customerId.getText(); 
				String transType  = transactionType.getText();
				String symbol  = stockSymbol.getText();
				double qty = Double.parseDouble(quantity.getText());
				
				Transaction t = new Transaction (acct, transType, symbol, qty); 
				
				Object[] rowData = {custID, t.getTransactionDate(), transType, symbol, qty, t.getPrice() }; 
				transTableModel.addRow(rowData); 
				double bal = acct.computeBalance(); 
				uBal.setText(Double.toString(bal)); 
				Transaction.writeToFile(); 
				JOptionPane.showMessageDialog(null, "Transaction has been added.");
				accountFrame.dispose(); 
				
			}
			else{ 
				JOptionPane.showMessageDialog(null, "Not a valid entry.");
			}
			repaint(); 
		} 
		
	}
	
	/** Action Listener used for the Home Buttons  */
	private class HomeListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if( e.getSource() == tHome){ 
				if(isSearched){ 
					startDate.setText(""); 
					endDate.setText("");
					remove(transactionDt); 
					remove(tScroll2);
					isSearched = false; 
				} else if(isSearchedCombo){ 
					remove(transactionCombo); 
					remove(tScroll3);
					isSearchedCombo = false; 
				}
				else{
					remove(userAndTrans); 
					remove(tScroll);	
				}
				remove(transButtons);
		        add(account, BorderLayout.CENTER); 
		        aScroll = new JScrollPane(account);
		        add(aScroll); 
				add(buttonPanel, BorderLayout.SOUTH);
				revalidate();
			}
			else if( e.getSource() == homeStock){
				remove(stockPanel);
				remove(stockButtons); 
				add(transaction, BorderLayout.CENTER); 
				userAndTrans.add(transaction, BorderLayout.CENTER); 
				userAndTrans.add(userPanel, BorderLayout.SOUTH);
				tScroll = new JScrollPane(transaction);
				userAndTrans.add(tScroll);
				add(userAndTrans, BorderLayout.CENTER);
				add(transButtons, BorderLayout.SOUTH);
				revalidate();
			}
			else{ 
				JOptionPane.showMessageDialog(null, "Not a valid entry.");
			}
			repaint(); 
		} 
		
	}
	
	/** Action Listener for the menu items */
	private class MenuListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource() == loadFromFile){ 
				Account.readFromFile(); 
				Transaction.readFromFile(); 
				Account.addListToDB(); 
				Transaction.addMapToDB(); 
				JOptionPane.showMessageDialog(null, "Databases have been loaded from the files.");
				revalidate();
			}
			else if( e.getSource() == saveToDB){
				Account.addListToDB(); 
				Transaction.addMapToDB(); 
				JOptionPane.showMessageDialog(null, "Databases have been updated.");
				revalidate();
			}
			else if( e.getSource() == loadFromDB){ 
				Account.addListToDB(); 
				Account.addDBtoList(); 
				remove(account); 
				remove(aScroll);  
		        add(account, BorderLayout.CENTER); 
		        aScroll = new JScrollPane(account);
		        add(aScroll); 
				add(buttonPanel, BorderLayout.SOUTH);
				JOptionPane.showMessageDialog(null, "Table loaded from the Database.");
				revalidate();
			}
			else if( e.getSource() == saveToFile){
				Account.addListToDB(); 
				Transaction.addMapToDB(); 
				Account.writeToFile();
				Transaction.writeToFile(); 
				JOptionPane.showMessageDialog(null, "Databases have been written to the files.");
				revalidate();
			}

			else{ 
				JOptionPane.showMessageDialog(null, "Not a valid entry.");
			}
			repaint(); 
		} 
		
	}
	
	/** Action Listener for the searches in the Transaction table  */
	private class TransactionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
 
			if( e.getSource() == search){ 
				isSearched = true; 
				remove(userAndTrans); 
				remove(tScroll);
				//remove(transButtons);
				int startDt = Integer.parseInt(startDate.getText()); 
				int endDt = Integer.parseInt(endDate.getText());
				Transaction.addMapToDB();
				transactionDt = new JTable(Transaction.TransDateDBtoDblArray(acct, startDt, endDt), Transaction.getMapHeader());
				System.out.println(transactionDt.getRowCount());
				transactionDt.setPreferredScrollableViewportSize(new Dimension(500, 500)); 
				transactionDt.setFillsViewportHeight(true); 
				transactionDt.setGridColor(Color.BLACK);
				transactionDt.setShowGrid(true); 
				add(transactionDt, BorderLayout.CENTER); 
				tScroll2 = new JScrollPane(transactionDt);
				add(tScroll2);
				revalidate();
			}
			else{ 
				JOptionPane.showMessageDialog(null, "Not a valid entry.");
			}
			repaint(); 
		} 
		
	}
	
	/** Action Listener for the ComboBox */ 
	private class ComboListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
 
				String type = (String) myBox.getSelectedItem();
				isSearchedCombo = true; 
				remove(userAndTrans); 
				remove(tScroll);
				//remove(transButtons);
				Transaction.addMapToDB();
				transactionCombo = new JTable(Transaction.TransTypeDBtoDblArray(acct, type), Transaction.getMapHeader());
				//System.out.println(transactionDt.getRowCount());
				transactionCombo.setPreferredScrollableViewportSize(new Dimension(500, 500)); 
				transactionCombo.setFillsViewportHeight(true); 
				transactionCombo.setGridColor(Color.BLACK);
				transactionCombo.setShowGrid(true); 
				add(transactionCombo, BorderLayout.CENTER); 
				tScroll3 = new JScrollPane(transactionCombo);
				add(tScroll3);
				revalidate();
	
				repaint(); 
		} 
		
	}
	
	/** Private Class used for MouseListener.  */
	private class TableListener implements MouseListener{
		
		
		/** When the mouse is clicked, determine if the mouse is on a brick and switches the visibility of that brick. */ 
		@Override
		public void mouseClicked(MouseEvent e) {
			 if (e.getClickCount() == 1) {
				 JTable target = (JTable)e.getSource(); 
		         int row = target.getSelectedRow();
		         String colid = (String)target.getValueAt(row, 0);
		         //System.out.println(colid);
		         
		         acct = Account.findAccount(colid); 
		         
		         launchTransaction(); 
		         remove(account); 
		         remove(aScroll); 
		         remove(buttonPanel);
		         startDate.setText(""); 
		         endDate.setText(""); 
		         userAndTrans.add(transaction, BorderLayout.CENTER); 
		         userAndTrans.add(userPanel, BorderLayout.SOUTH);
		         tScroll = new JScrollPane(transaction);
		         userAndTrans.add(tScroll);
		         add(userAndTrans, BorderLayout.CENTER); 
		         add(transButtons, BorderLayout.SOUTH); 
		         double bal = acct.computeBalance(); 
		         uBal.setText(Double.toString(bal)); 
		         revalidate(); 
		         repaint(); 
		        
		         // do some action
		         }
		   }

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
			
	}
}

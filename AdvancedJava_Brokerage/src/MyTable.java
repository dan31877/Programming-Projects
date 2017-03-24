package cs565.Final;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel; 
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class MyTable extends JPanel {

	/**
	 * @param args
	 */
	
	private JTable jt; 
	private int size;  
	
	public MyTable(String name, int s){ 
		
		size = s; 
		jt = new JTable(size, 4);
		jt.getTableHeader().getColumnModel().getColumn(0).setHeaderValue(name);
		jt.setPreferredScrollableViewportSize(new Dimension(150, 300)); 
		jt.setFillsViewportHeight(true); 
		add(new JScrollPane(jt));
		
		
	}
	
	public MyTable(String name, int s, int x, int y){ 
		
		size = s; 
		jt = new JTable(size, 1);
		jt.getTableHeader().getColumnModel().getColumn(0).setHeaderValue(name);
		jt.setPreferredScrollableViewportSize(new Dimension(x, y)); 
		jt.setFillsViewportHeight(true); 
		add(new JScrollPane(jt));
		
		
	}
	
	public MyTable(Object[][] rowData, Object[] colNames){ 
		
		jt = new JTable(new DefaultTableModel(rowData, colNames));
		jt.setPreferredScrollableViewportSize(new Dimension(500, 500)); 
		jt.setFillsViewportHeight(true); 
		jt.setGridColor(Color.BLACK);
		jt.setShowGrid(true); 
		add(new JScrollPane(jt));
		
	}
	
	public void copyArray(Object obj[]){ 
		
		
		for(int i = 0; i<getTableSize() && i<obj.length; i++){ 
			jt.setValueAt(obj[i], i, 0); 
		}
		
	}
	
	public void selectRow(int index){ 
		
		jt.changeSelection(index, 1, false, false); 
		
	}
	
	public int getTableSize(){ 
		return size; 
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Brokerage br = new Brokerage("go"); 
		br.connect(); 
		
		Stock stock = new Stock(); 
		stock.createQuotes(); 
		stock.createHistory(); 
		
		stock.populateQuotes(); 
		stock.populateHistory(); 
		
		Account acct1 = new Account("ID1001", "Dan", 2000.00); 
		Account acct2 = new Account("ID1002", "Charyn", 1800.00); 
		Account acct3 = new Account("ID1003", "Karen", 4000.00); 
		Account acct4 = new Account("ID1004", "Keith", 5000.00);
		Account acct5 = new Account("ID1005", "Chris", 3000.00);
		Account.createTable();
		Transaction.readFromFile(); 
		Transaction.addMapToDB(); 
		System.out.println("Here?");
		
		JFrame jf = new JFrame(); 

		MyTable table = new MyTable(Transaction.TransDBtoDblArray(acct2), Transaction.getMapHeader()); 
		//MyTable table = new MyTable(Transaction.StockHistDBtoDblArray(acct2), stock.getHistoryHeader());
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setSize(550, 550);
		jf.setVisible(true);
		jf.add(table); 
		
	}
 
}
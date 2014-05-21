package PagingGUI;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel; 
import javax.swing.JScrollPane;
import javax.swing.JTable;


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

		JFrame jf = new JFrame(); 
		MyTable table = new MyTable("Table", 1024); 
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setSize(500, 500);
		jf.setVisible(true);
		jf.add(table); 
		
		Integer [] numbers = {1,2,3,5, 6, 7}; 
		Object [] tableinfo =  numbers; 
		table.copyArray(tableinfo); 
		table.selectRow(984); 
		
	}
 
}
package PagingGUI;
	/**
	 * Class:   Final Project for CS575 - Operating Systems
	 * 
	 * Author:  Karen Palmer - the backend program
	 *          Dan Anderson - the GUI demonstration program
	 *          Charyn Eagan - the research and paper
	 * 
	 * Description - PagingTableGUI
	 * 		The GUI creates an interface to simulate a paging table. An address can be entered into the textfield and press the 
	 * 		enter key or the button. The simulator will take the address and search the TLB for the page number and frame. If the 
	 * 		page number is not found on the TLB table, it searched the page table adn copies it to the TLB. If it's not found in the 
	 * 		page table then the values are copied in to both the TLB and page table. 
	 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.*;

public class PageTableGUI extends JFrame{

	/**
	 * @param args
	 */
	private DemoMain dm;
	private ResultsClass results;
	private MyTable tlb; 
	private MyTable page; 
	private MyTable mem; 
	private int pageFltNum = 0; 
	private int tblFltNum = 0; 
	private int pageHitNum = 0; 
	private int tlbHitNum = 0;
	
	private JTextField address; 
	private JLabel label; 
	private JButton button; 
	
	private JTextField tlbFault; 
	private JLabel tlbFaultLabel;
	private JTextField tlbHit; 
	private JLabel tlbHitLabel;
	private JTextField tlbFaultTime; 
	private JLabel tlbFaultLabelTime;
	private int tlbTimeInNanos = 10; 
	
	private JTextField pageFault; 
	private JLabel pageFaultLabel;
	private JTextField pageHit; 
	private JLabel pageHitLabel;
	private JTextField pageFaultTime; 
	private JLabel pageFaultLabelTime;
	private int pageTimeInNanos = 90; 
	
	private JPanel addressbar; 
	private JPanel tlbFltTime; 
	private JPanel pageFltTime;
	private JPanel tlbFltAndTime; 
	private JPanel pageFltAndTime;
	private JPanel tlbFlt; 
	private JPanel pageFlt;
	private JPanel tlbHt; 
	private JPanel pageHt;
	private JPanel faults; 
	
	private String thisAddress; 
	
	public PageTableGUI(){
		super("Page Table Simulator");
		
		tlb = new MyTable("TLB", 8);
		page = new MyTable("Page", 16);
		mem = new MyTable("Main Memory", 64, 400, 350);
		dm = new DemoMain(); 
		dm.setup(); 
		results = dm.result; 
		setTableArrays(results.gettLB(), results.getPageTbl(), results.getmMem());
		pageFltNum = results.getPageFaults(); 
		tblFltNum = results.gettLBFaults();  
		pageHitNum = results.getPageHits();
		tlbHitNum = results.gettLBHits();
		
		addressbar = new JPanel(); 
		address = new JTextField(20);
		label = new JLabel("Enter an address: "); 
		button = new JButton("OK"); 
		addressbar.add(label, BorderLayout.WEST); 
		addressbar.add(address, BorderLayout.CENTER); 
		addressbar.add(button, BorderLayout.EAST); 
		
		tlbFault = new JTextField(Integer.toString(tblFltNum), 4); 
		tlbFaultLabel = new JLabel("TLB Faults: ");
		tlbFaultTime = new JTextField(Integer.toString(tblFltNum * tlbTimeInNanos), 5); 
		tlbFaultLabelTime = new JLabel("TLB Fault Time (10 nanoseconds/fault): ");
		tlbHit = new JTextField(Integer.toString(tlbHitNum), 4); 
		tlbHitLabel= new JLabel("TLB Hits: ");
		tlbFlt = new JPanel(); 
		tlbFlt.add(tlbFaultLabel, BorderLayout.WEST); 
		tlbFlt.add(tlbFault, BorderLayout.EAST);
		tlbFltTime = new JPanel(); 
		tlbFltTime.add(tlbFaultLabelTime, BorderLayout.WEST); 
		tlbFltTime.add(tlbFaultTime, BorderLayout.EAST);
		tlbHt = new JPanel(); 
		tlbHt.add(tlbHitLabel, BorderLayout.WEST); 
		tlbHt.add(tlbHit, BorderLayout.EAST);
		tlbFltAndTime = new JPanel(); 
		tlbFltAndTime.add(tlbHt, BorderLayout.NORTH); 
		tlbFltAndTime.add(tlbFlt, BorderLayout.SOUTH); 
		//tlbFltAndTime.add(tlbFltTime, BorderLayout.SOUTH); 
		
		pageFault = new JTextField(Integer.toString(pageFltNum), 4);
		pageFaultLabel = new JLabel("Page Faults: ");
		pageFaultTime = new JTextField(Integer.toString(pageFltNum * pageTimeInNanos), 5);
		pageFaultLabelTime = new JLabel("Page Fault Time (90 nanoseconds/fault): ");
		pageHit = new JTextField(Integer.toString(pageHitNum), 4); 
		pageHitLabel= new JLabel("Page Hits: ");
		pageFlt = new JPanel(); 
		pageFlt.add(pageFaultLabel, BorderLayout.WEST); 
		pageFlt.add(pageFault, BorderLayout.EAST);
		pageFltTime = new JPanel(); 
		pageFltTime.add(pageFaultLabelTime, BorderLayout.WEST); 
		pageFltTime.add(pageFaultTime, BorderLayout.EAST);
		pageHt = new JPanel(); 
		pageHt.add(pageHitLabel, BorderLayout.WEST); 
		pageHt.add(pageHit, BorderLayout.EAST);
		pageFltAndTime = new JPanel(); 
		pageFltAndTime.add(pageHt, BorderLayout.NORTH); 
		pageFltAndTime.add(pageFlt, BorderLayout.SOUTH); 
		//pageFltAndTime.add(pageFltTime, BorderLayout.SOUTH); 
		
		faults = new JPanel(); 
		faults.add(tlbFltAndTime, BorderLayout.WEST); 
		faults.add(pageFltAndTime, BorderLayout.EAST);
		
		add(addressbar, BorderLayout.NORTH); 
		add(tlb, BorderLayout.WEST); 
		add(page, BorderLayout.CENTER); 
		add(mem, BorderLayout.EAST); 
		add(faults, BorderLayout.SOUTH); 
		
		button.addActionListener(new MyListener()); 
		address.addActionListener(new MyListener()); 
	}
	
	private class MyListener implements ActionListener{
		
		public void actionPerformed( ActionEvent e){ 
			
			thisAddress = "0x" + address.getText(); 
			
			String fault = "An error has occurred."; 
		
			results = dm.processAddress(Integer.decode(thisAddress)); 
			if(results.gettlbFault() && results.getPageFault()){
				fault = "A TLB Fault and a Page Fault has occurred."; 
		        Clip clip;
		        
				try {
					clip = AudioSystem.getClip();
			        clip.open(AudioSystem.getAudioInputStream(new File("buzzer.wav")));
			        clip.start();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					System.out.println("Sound is not functioning.");
				}

			}
			
			else if(!results.gettlbFault() && results.getPageFault()){
				fault = "TLB Hit and Page Miss.";
			}
			else if(results.gettlbFault() && !results.getPageFault()){
				fault = "Page Hit!!";
			}
			
			else if(!results.gettlbFault() && !results.getPageFault()){
				fault = "TLB Hit!!";
			}
			
			JOptionPane.showMessageDialog(null, fault);
			pageFault.setText(Integer.toString(results.getPageFaults())); 
			tlbFault.setText(Integer.toString(results.gettLBFaults()));
			pageHit.setText(Integer.toString(results.getPageHits())); 
			tlbHit.setText(Integer.toString(results.gettLBHits())); 
			tlbFaultTime.setText(Integer.toString(tblFltNum * tlbTimeInNanos)); 
			pageFaultTime.setText(Integer.toString(pageFltNum * pageTimeInNanos));
			
			setTableArrays(results.gettLB(), results.getPageTbl(), results.getmMem());
			highlightRow(results.gettLBNdx(), results.getPageTblNdx(), results.getmMemNdx());
	
		}
	}
	
	public void createGUI(){ 
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800, 600);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	public void highlightRow(int tlbIndex, int pageIndex, int memIndex){ 
		 
		tlb.selectRow(tlbIndex); 
		page.selectRow(pageIndex); 
		mem.selectRow(memIndex); 
		
	}
	
	public void setTableArrays(Object tlbArray[], Object pg[], Object mm[]){ 
		tlb.copyArray(tlbArray); 
		page.copyArray(pg); 
		mem.copyArray(mm); 
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		PageTableGUI sim = new PageTableGUI(); 
		sim.createGUI(); 

	}

}

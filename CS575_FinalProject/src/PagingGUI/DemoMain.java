package PagingGUI;
//package version02;

public class DemoMain {


	/**
	 * Class:   Final Project for CS575 - Operating Systems
	 * 
	 * Author:  Karen Palmer - the backend program
	 *          Dan Anderson - the GUI demonstration program
	 *          Charyn Eagan - the research and paper
	 * 
	 * Description: This is the main driver for the Paging Simulator
	 *              1) Populate a main memory
	 *                 an array of 0x40 classes of MainMemClass
	 *              2) Initialize with 0's a paging table of 0x10 long
	 *                 with class PageTableClass
	 *              3) Initialize with 0's a TLB of 0x08 long
	 *                 with class TLBClass
	 *              4) Pre-populate TLB and most of the paging table 
	 *                 using an array of addresses as input
	 *              3) Process addresses input by user through GUI
	 */
	
	// variables required for execution
	int memVal;
	ResultsClass result = new ResultsClass();
	// initialize MainMemory
	MainMemClass[] memory = new MainMemClass[0x40];
	// initialize the Page Table
	PageTableClass pageTbl[] = new PageTableClass[0x10];
	// initialize the TLB
	TLBClass[] tLB = new TLBClass[0x08];


	public static void main(String [] args){
		
		DemoMain dm = new DemoMain(); 
		ResultsClass r1 = new ResultsClass(); 
		dm.setup(); 
		System.out.println("NEW DATA :");
		r1 = dm.processAddress(0x157); //  0x3b7
		
	}
	public void setup() {
		
		// an array of addresses used to pre populate the TLB and page tables
		int iC = 0;
		int[] address    = new  int[]{  0x015, 0x016, 0x017, 0x14c, 0x14d, 0x14e, 0x14f, 0x150, 
				0x151, 0x3a6, 0x2c3, 0x350, 0x14c, 0x23b, 0x182, 0x030, 0x348, 0x03f, 0x358, 0x14b, 
				0x14c, 0x03f, 0x083, 0x226};

		// initialize the main memory
		for (int i = 0; i < 0x40; i++)   
		{
			memVal = ((i & 0xf) << 4);
			memory[i] = new MainMemClass(memVal);
		}

		// put the main memory into the ResultsClass object as array of Strings
		for (int i = 0; i < 0x40; i++)   
		{
			result.setMValue(i, stringMemoryLine(memory[i]));
		}

		// initialize with 0's the 0x10 rows of the Page Table
		for (int i = 0; i < 0x10; i++)   
		{
			pageTbl[i] = new PageTableClass();
		}

		// initialize with 0's the 0x08 rows of the TLB
		for (int i = 0; i < 0x08; i++)   
		{
			tLB[i] = new TLBClass();
		}

		// pre populate the page table and the TLB using an array of addresses
		for (iC = 0; iC < address.length; iC++)
		{
			System.out.println("");
			result = processAddress(address[iC], memory, tLB, pageTbl, result);
			System.out.println("processed address num " + iC);
			result.print(result);
		}
	}
	
	// processAddress method - runs the other processAddress method but only takes an address.
	public ResultsClass processAddress(int addr){
		
		return processAddress(addr, memory, tLB, pageTbl, result);
	}

	// processAddress method - 
	//   takes in an address along with all the classes necessary to process it
	//   sends back an updated ResultsClass object

	public ResultsClass processAddress(int address, MainMemClass[] memory, TLBClass[] tLB, 
			PageTableClass[] pageTbl, ResultsClass result) 
	{
		int calcOffset = 0;
		int calcFrame  = 0;
		int increment     = 0;

		// determine needed fields from the address
		calcFrame    = ((address & 0xff0) >>> 4);
		calcOffset   =  (address & 0xf);

		// always set address fields
		result.setAddress(address);
		result.setAtAddress(memory[calcFrame].getMMem(calcOffset));

		// search the tlb for frame num - if found update Results class
		increment = 0;
		while (increment < tLB.length && tLB[increment].getFrameNum() != calcFrame)
			increment++;
		if (increment < tLB.length)
		{
			// found in TLB, create ResultsClass and return
			//   set TLB fields - table itself wasn't updated
			result.settLBNdx(increment);
			result.settlbFault(false);
			result.settLBFaults(result.gettLBFaults() + 0);  // not a fault
			result.settLBHits(result.gettLBHits() + 1);      // increment tbl hits counter
			//   set required page table fields - table itself wasn't used
			for (int kk = 0; kk < pageTbl.length; kk++)
			{
				if (pageTbl[kk].getFrameNum() == calcFrame)
					result.setPageTblNdx(kk);
			}
			result.setPageFault(false);
			result.setPageFaults(result.getPageFaults() + 0); // not a fault
			result.setPageHits(result.getPageHits() + 1);     // increment page hits counter
			//   set memory fields
			result.setmMemNdx(calcFrame);	
			return result;
		}

		// search the page table for frame num - if found update Results class
		//   if it was found in the page table, the tlb also needs to be updated
		increment = 0;
		while (increment < pageTbl.length && pageTbl[increment].getFrameNum() != calcFrame)
			increment++;
		if (increment < pageTbl.length)
		{
			// found in Page Table, create ResultsClass and return
			//   set TLB fields - table must be updated
			tLB = updateTLB(address, memory, tLB, pageTbl, result);
			result.settLB(createStringTLB(tLB));
			for (int kk = 0; kk < tLB.length; kk++)
			{
				if (tLB[kk].getFrameNum() == calcFrame)
					result.settLBNdx(kk);
			}
			result.settlbFault(true);
			result.settLBFaults(result.gettLBFaults() + 1); // a TLB fault
			result.settLBHits(result.gettLBHits() + 0);     // not a TLB hit
			//   set page table fields - table itself isn't updated
			for (int kk = 0; kk < pageTbl.length; kk++)
			{
				if (pageTbl[kk].getFrameNum() == calcFrame)
					result.setPageTblNdx(kk);
			}
			result.setPageFault(false);
			result.setPageFaults(result.getPageFaults() + 0); // not a page fault
			result.setPageHits(result.getPageHits() + 1);     // increment page hit counter
			//   set memory fields
			result.setmMemNdx(calcFrame);	
			return result;
		}

		// this logic is executed when the frame number wasn't found in either place
		//   set TLB fields - table must be updated
		tLB = updateTLB(address, memory, tLB, pageTbl, result);
		result.settLB(createStringTLB(tLB));
		for (int kk = 0; kk < tLB.length; kk++)
		{
			if (tLB[kk].getFrameNum() == calcFrame)
				result.settLBNdx(kk);
		}
		result.settlbFault(true);
		result.settLBFaults(result.gettLBFaults() + 1); // a TLB fault
		//   set page table fields - table itself isn't updated
		pageTbl = updatePageTbl(address, memory, tLB, pageTbl, result);
		result.setPageTbl(createStringPageTbl(pageTbl));
		for (int kk = 0; kk < pageTbl.length; kk++)
		{
			if (pageTbl[kk].getFrameNum() == calcFrame)
				result.setPageTblNdx(kk);
		}
		result.setPageFault(true);
		result.setPageFaults(result.getPageFaults() + 1); // a page fault
		//   set memory fields
		result.setmMemNdx(calcFrame);
		return result;
	}

	// convert the integers to String format for easy GUI display
	private String[] createStringTLB(TLBClass[] tLB) {
		String [] newEntry = new String[tLB.length];

		for (int newFIFO = 0; newFIFO < tLB.length; newFIFO++)
		{
			newEntry[newFIFO] = ((String.format("%01x", tLB[newFIFO].getFIFO())) + " | "
					+ (String.format("%03x", tLB[newFIFO].getFrameNum())));
		}
		return newEntry;
	}

	private TLBClass[] updateTLB(int address, MainMemClass[] memory,
			TLBClass[] tLB, PageTableClass[] pageTbl, ResultsClass result) {

		int increment = 0;
		ResultsClass resultOut = result.copy(result);
		// determine needed fields from the address
		int calcFrame    = ((address & 0xff0) >>> 4);

		String tblEntry  = "";

		// update the TLB table itself
		while (increment < tLB.length && tLB[increment].getValid() != 0)
			increment++;
		if (increment < tLB.length)
		{
			// there's an open slot in the TLB
			// set TLB fields - table itself wasn't updated
			tLB[increment].setValid(1);
			tLB[increment].setFrameNum(calcFrame);
			tLB[increment].setFIFO(increment + 1);
			tLB[increment].setTLBNdx(increment);
			return tLB;
		}

		// there's no open slot in the TLB
		for (int newFIFO = 0; newFIFO < tLB.length; newFIFO++)
		{
			if (tLB[newFIFO].getFIFO() == 1)
			{
				tLB[newFIFO].setFIFO(tLB.length);
				tLB[newFIFO].setFrameNum(calcFrame);
				tblEntry = ((String.format("%01x", tLB[newFIFO].getFIFO())) + " | "
						+ (String.format("%03x", tLB[newFIFO].getFrameNum())));
				resultOut.setTValue(newFIFO, tblEntry);
			}
			else
			{
				tLB[newFIFO].setFIFO(tLB[newFIFO].getFIFO() - 1);
				tblEntry = ((String.format("%01x", tLB[newFIFO].getFIFO())) + " | "
						+ (String.format("%03x", tLB[newFIFO].getFrameNum())));
				resultOut.setTValue(newFIFO, tblEntry);
			}
		}
		return tLB;
	}

	// convert the integers to String format for easy GUI display
	private String[] createStringPageTbl(PageTableClass[] pageTbl) {
		String [] newEntry = new String[pageTbl.length];

		for (int newFIFO = 0; newFIFO < pageTbl.length; newFIFO++)
		{
			newEntry[newFIFO] = ((String.format("%01x", pageTbl[newFIFO].getFIFO())) + " | "
					+ (String.format("%03x", pageTbl[newFIFO].getFrameNum())));
		}
		return newEntry;
	}

	private PageTableClass[] updatePageTbl(int address, MainMemClass[] memory,
			TLBClass[] tLB, PageTableClass[] pageTbl, ResultsClass result) {

		int increment = 0;
		ResultsClass resultOut = result.copy(result);
		// determine needed fields from the address
		int calcFrame    = ((address & 0xff0) >>> 4);

		String tblEntry  = "";

		// update the paging table itself
		while (increment < pageTbl.length && pageTbl[increment].getValid() != 0)
			increment++;
		if (increment < pageTbl.length)
		{
			// there's an open slot in the paging table
			// set paging table fields - tale itself wasn't updated
			pageTbl[increment].setValid(1);
			pageTbl[increment].setFrameNum(calcFrame);
			pageTbl[increment].setFIFO(increment + 1);
			tblEntry = ((String.format("%01x", pageTbl[increment].getFIFO())) + " | "
					+ (String.format("%03x", pageTbl[increment].getFrameNum())));
			resultOut.setPValue(increment, tblEntry);
			return pageTbl;
		}

		// there's no open slot in the pageTbl
		for (int newFIFO = 0; newFIFO < pageTbl.length; newFIFO++)
		{
			if (pageTbl[newFIFO].getFIFO() == 1)
			{
				pageTbl[newFIFO].setFIFO(pageTbl.length);
				pageTbl[newFIFO].setFrameNum(calcFrame);
				tblEntry = ((String.format("%01x", pageTbl[newFIFO].getFIFO())) + " | "
						+ (String.format("%03x", pageTbl[newFIFO].getFrameNum())));
				resultOut.setPValue(newFIFO, tblEntry);
			}
			else
			{
				pageTbl[newFIFO].setFIFO(pageTbl[newFIFO].getFIFO() - 1);
				tblEntry = ((String.format("%01x", pageTbl[newFIFO].getFIFO())) + " | "
						+ (String.format("%03x", pageTbl[newFIFO].getFrameNum())));
				resultOut.setPValue(newFIFO, tblEntry);
			}
		}
		return pageTbl;
	}

	// convert the integers to String format for easy GUI display
	public String stringMemoryLine(MainMemClass line) 
	{
		String lineOut = "";
		for (short i = 0; i <= 0xf; i++)
		{
			lineOut = lineOut + ("  " + String.format("%02x",line.getMMem(i)));
		}
		//System.out.println("memory line from the method " + lineOut);
		return lineOut;
	}

	// print one line of memory method
	public void printMemoryLine(MainMemClass line) 
	{
		for (short i = 0; i <= 0xf; i++)
		{
			System.out.print("  " + String.format("%02x",line.getMMem(i)));
		}
		System.out.println("");
	}
	
	// print the whole TLB
	private void printTLB(TLBClass[] tLB) 
	{
		for (int pp = 0; pp < tLB.length; pp++)
			printTLBLine(tLB[pp]);
	}

	// printTLBLine method
	public void printTLBLine(TLBClass line) 
	{
		System.out.print("FIFO # " + line.getFIFO());
		System.out.println("   Frame # " + String.format("%02x",line.getFrameNum()));
	}

	// print the whole Page Table
	private void printPageTable(PageTableClass[] pageTbl) 
	{
		for (int pp = 0; pp < pageTbl.length; pp++)
			printPageTableLine(pageTbl[pp]);
	}

	// printPageTable method
	public void printPageTableLine(PageTableClass line) 
	{
		System.out.print("FIFO # " + line.getFIFO());
		System.out.println("   Frame # " + String.format("%02x",line.getFrameNum()));
	}

}


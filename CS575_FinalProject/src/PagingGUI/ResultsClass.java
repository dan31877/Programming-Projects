package PagingGUI;
//package version02;

public class ResultsClass {

	// Results for display in the GUI
	// address input - page # and displacement
	private int addressIn     = 0;
	private int pageNum       = 0;   // (first 6 bits of address)
	private int disp          = 0;   // (last 4 bits of address)
	private int atAddress     = 0;   // value from main memory at the address input
	// TLB - table itself, index of result, num of TLB faults
	private String[] tLB      = new String[0x8];
	private int tLBNdx        = 0;
	private boolean tlbFault  = false;
	private int tLBFaults     = 0;
	private int tLBHits       = 0;
	// page table - table itself, index of result, num of page faults
	private String[] pageTbl  = new String[0x10];
	private int pageTblNdx    = 0;
	private boolean pageFault = false;
	private int pageFaults    = 0;
	private int pageHits      = 0;
	// main memory - table itself, index of result
	private String[] mMem     = new String[0x40];
	private int mMemNdx       = 0;

	// initialize one row in this class as required for logic
	public ResultsClass()
	{
		// address fields
		addressIn     = 0;
		pageNum       = 0;   // (first 6 bits of address)
		disp          = 0;   // (last 4 bits of address)
		atAddress     = 0;   // value at the address input
		// TLB - table itself, index of result, num of TLB faults
		for (int tlbX = 0; tlbX < tLB.length; tlbX++)
		{
			tLB[tlbX] = "";
		}
		tLBNdx       = 0;
		tlbFault     = false;
		tLBFaults    = 0;
		tLBHits      = 0;
		// page table - table itself, index of result, num of page faults
		for (int pageX = 0; pageX < pageTbl.length; pageX++)
		{
			pageTbl[pageX] = "";
		}
		pageTblNdx   = 0;
		pageFault    = false;
		pageFaults   = 0;
		pageHits     = 0;
		// main memory - table itself, index of result
		for (int memX = 0; memX < mMem.length; memX++)
		{
			mMem[memX] = "";
		} 
		mMemNdx      = 0; 
	}
	
	// setters and getters
	public int getAddress()                         { return addressIn; }
	public int getPageNum()                         { return pageNum; }
	public int getDisp()                            { return disp; }
	public void setAddress(int address) 
	{
		this.addressIn = address;
		this.pageNum   = (address >> 4);
		this.disp      = (address & 0xf); 
	}
	public int getAtAddress() 						{ return atAddress; }
	public void setAtAddress(int AtAddress)         { this.atAddress = AtAddress; }
	public String[] gettLB()                        { return tLB; }
	public void settLB(String[] tLB)                { this.tLB = tLB; }
	public void setTValue(int offset, String value) { tLB[offset] = value; }
	public String getTValue(int offset)             { return this.tLB[offset]; }
	public int gettLBNdx()                          { return tLBNdx; }
	public void settLBNdx(int tLBNdx)               { this.tLBNdx = tLBNdx; }
	public boolean gettlbFault()                    { return tlbFault; }
	public void settlbFault(boolean tlbFault)       { this.tlbFault = tlbFault; }
	public int gettLBFaults()                       { return tLBFaults; }
	public void settLBFaults(int tLBFaults)         { this.tLBFaults = tLBFaults; }
	public int gettLBHits()                         { return tLBHits; }
	public void settLBHits(int tLBHits)             { this.tLBHits = tLBHits; }
	public String[] getPageTbl()                    { return pageTbl; }
	public void setPageTbl(String[] pageTbl)        { this.pageTbl = pageTbl; }
	public void setPValue(int offset, String value) { pageTbl[offset] = value; }
	public String getPValue(int offset)             { return this.pageTbl[offset]; }
	public int getPageTblNdx()                      { return pageTblNdx; }
	public void setPageTblNdx(int pageTblNdx)       { this.pageTblNdx = pageTblNdx; }
	public boolean getPageFault()                   { return pageFault; }
	public void setPageFault(boolean pageFault)     { this.pageFault = pageFault; }
	public int getPageFaults()                      { return pageFaults; }
	public void setPageFaults(int pageFaults)       { this.pageFaults = pageFaults; }
	public int getPageHits()                        { return pageHits; }
	public void setPageHits(int pageHits)           { this.pageHits = pageHits; }
	public String[] getmMem()                       { return mMem; }
	public void setmMem(String[] mMem)              { this.mMem = mMem; }
	public String getMValue(int offset)	            { return this.mMem[offset]; }
	public void setMValue(int offset, String value) { mMem[offset] = value; }
	public int getmMemNdx()                         { return mMemNdx; }
	public void setmMemNdx(int mMemNdx)             { this.mMemNdx = mMemNdx; }
	
	// Copy method
	public ResultsClass copy(ResultsClass obj) 
	{
		ResultsClass other = (ResultsClass) obj;
		// address fields
		other.addressIn    = obj.getAddress();
		other.pageNum      = obj.getPageNum();   // (first 6 bits of address)
		other.disp         = obj.getDisp();      // (last 4 bits of address)
		other.atAddress    = obj.getAtAddress();   // value at the address input
		// TLB - table itself, index of result, num of TLB faults
		other.tLB          = obj.gettLB();
		other.tLBNdx       = obj.gettLBNdx();
		other.tlbFault     = obj.gettlbFault();
		other.tLBFaults    = obj.gettLBFaults();
		other.tLBHits      = obj.gettLBHits();
		// page table - table itself, index of result, num of page faults
		other.pageTbl      = obj.getPageTbl();
		other.pageTblNdx   = obj.getPageTblNdx();
		other.pageFault    = obj.getPageFault();
		other.pageFaults   = obj.getPageFaults();
		other.pageHits     = obj.getPageHits();
		// main memory - table itself, index of result
		other.mMem         = obj.getmMem();
		other.mMemNdx      = obj.getmMemNdx(); 

		return other;
	}

	// print method
	public void print(ResultsClass obj) 
	{
		// address fields
		System.out.println("Address input " + String.format("%03x",obj.getAddress()));
		System.out.println("Page number " + String.format("%03x",obj.getPageNum()));   
		System.out.println("Offset " + String.format("%02x",obj.getDisp()));    
		System.out.println("value in memory " + String.format("%02x",obj.getAtAddress()));   
		// TLB - table itself, index of result, num of TLB faults
//		other.tLB          = obj.gettLB();
		System.out.println("TLB index " + String.format("%02x",obj.gettLBNdx()));
		System.out.println("TLB Fault " + obj.gettlbFault());
		System.out.println("TLB Fault Acc " + obj.gettLBFaults());
		System.out.println("TLB Hit Acc " + obj.gettLBHits());
		// page table - table itself, index of result, num of page faults
//		other.pageTbl      = obj.getPageTbl();
		System.out.println("Page Table index " + String.format("%02x",obj.getPageTblNdx()));
		System.out.println("Page Table Fault " + obj.getPageFault());
		System.out.println("Page Table Fault Acc " + obj.getPageFaults());
		System.out.println("Page Table Hit Acc " + obj.getPageHits());
		// main memory - index of result
		System.out.println("Main Memory index " + String.format("%02x",obj.getmMemNdx())); 
	}

}
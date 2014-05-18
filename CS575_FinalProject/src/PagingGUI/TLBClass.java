package PagingGUI;
//package version01;

public class TLBClass {

	// one line entry in the Cache
	private int valid      = 0;
	private int fIFO       = 0;
	private int frameNum   = 0;
	private int tLBNdx     = 0;

	// initialize one row in the cache as required for logic
	public TLBClass()
	{
		int value = 0x0;
		valid     = value;
		fIFO      = value;
		frameNum  = value;
		tLBNdx    = value;
	}

	// getters and setters
	// set the valid bit
	public void setValid(int value)    { this.valid = value; }
	// get the valid bit
	public int getValid()              { return valid; }
	// set the FIFO indicator
	public void setFIFO(int value)     { this.fIFO = value; }
	// get the FIFO indicator
	public int getFIFO()               { return fIFO; }
	// set the frame number
	public void setFrameNum(int value) { this.frameNum = value; }
	// get the frame number
	public int getFrameNum()           { return frameNum; }
	// set the index (temp field)
	public void setTLBNdx(int value)   { this.tLBNdx = value; }
	// get the index (temp field) 
	public int getTLBNdx()             {return tLBNdx; }

}

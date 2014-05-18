package PagingGUI;
//package version01;

public class PageTableClass {

	// one line entry in the Cache
	private int valid      = 0;
	private int fIFO       = 0;
	private int frameNum   = 0;

	// initialize one row in the cache as required for logic
	public PageTableClass()
	{
		int value = 0x0;
		valid     = value;
		fIFO      = value;
		frameNum  = value;
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

}

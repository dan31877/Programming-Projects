package PagingGUI;
//package version01;

public class MainMemClass {

	// one line entry in the MainMemory
	private int[] mMem = new int[0x10];

	// provide the generic values for one row of Main Memory
	public MainMemClass()
	{
		for (int i = 0; i < 0x10; i++)
		{
			mMem[i] = 0x0;
		}

	}

	// using the location provided in the constructor, create
	//  one row in Main Memory as part of the initialization
	// value in location is the "frame number" followed by a 0
	public MainMemClass(int location)
	{
		int value;

		for (short i = 0; i < 0x10; i++)
		{
			value = (i + location);
			setMMem(i, value);
		}
	}

	// getter and setter
	// get one location in the MainMemoryClass
	public int getMMem(int offset)             { return mMem[offset]; }
	// set one location in the MainMemoryClass
	public void setMMem(int offset, int value) { mMem[offset] = value; }

}

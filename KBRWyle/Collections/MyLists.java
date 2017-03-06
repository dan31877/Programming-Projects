package Collections;

/**
 * Daniel Anderson
 * KBRWyle Coding Challenge 
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Class MyLists to create the lists for the first two questions
public class MyLists {

	// Lists are being used and then shuffled to ensure random list order
	private List<Integer> list1 = new ArrayList<Integer>(); 
	private List<Integer> list2 = new ArrayList<Integer>();
	
	// Constructor to create the two lists
	public MyLists() {

		this.setList1();
		this.setList2();
	}
	public List<Integer> getList1() {
		return list1;
	}
	
	// Sets the first list as shown in the instructions
	public void setList1() {
		for (int i = 1; i<=10; i++){
			this.list1.add(i);
		}
		Collections.shuffle(list1); 
	}
	public List<Integer> getList2() {
		return list2;
	}
	
	// Sets the second list as shown in the instructions
	public void setList2() {
		for (int i = 6; i<=15; i++){
			this.list2.add(i);
		}
		Collections.shuffle(list2);
	}
	
}

package Collections;

/**
 * Daniel Anderson
 * KBRWyle Coding Challenge - Collections First Question
 * 
 * Define the following 2 collections 
 * one contains integers 1 thru 10 (random order) 
 * one contains 6 thru 15 (random order)
 * Print both collections
 * Print the size of both collections
 * 
 */

import java.util.Iterator;
import java.util.List;

// Collections1 class will perform the 
public class Collections1 {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// Get the lists from the MyLists class
		MyLists mylists = new MyLists(); 
		List<Integer> list1 = mylists.getList1(); 
		List<Integer> list2 = mylists.getList2();
		
		// Define list iterators
		Iterator<Integer> iterator1 = list1.iterator(); 
		Iterator<Integer> iterator2 = list2.iterator(); 
		
		// Use the iterator to print through the first list
		System.out.println("List 1: ");
		while(iterator1.hasNext()){ 
			System.out.println(iterator1.next());
		}
		
		// Use the iterator to print through the second list
		System.out.println();
		System.out.println("List 2: ");
		while(iterator2.hasNext()){ 
			System.out.println(iterator2.next());
		}
		
		// Print the sizes of the lists
		System.out.println();
		System.out.println("The size of the first list is " + list1.size());
		System.out.println("The size of the second list is " + list2.size());
	}

}

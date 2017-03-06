package Collections;

/**
 * Daniel Anderson
 * KBRWyle Coding Challenge - Collections Second Question
 * 
 * Combine the above two collections into a third collection 
 * Collection cannot have any duplicates
 * Collection must be sorted
 * Remove the middle entry
 * Print the collection in reverse order
 * Print the size of the collection
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Combines the lists into a set to eliminate duplicates then to a list to sort
public class Collections2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// Get the lists, set, and final list
		MyLists mylists = new MyLists(); 
		Set<Integer> set = new HashSet<Integer>(); 
		List<Integer> list = new ArrayList<Integer>(); 
		
		List<Integer> list1 = mylists.getList1(); 
		List<Integer> list2 = mylists.getList2();
		
		// Add the lists to the set
		set.addAll(list1); 
		set.addAll(list2); 
		
		// Adds the set to a list and sort
		list.addAll(set); 
		Collections.sort(list);
		
		// Remove the middle entry
		list.remove(list.size()/2); 
		
		// Print the list in reverse
		for (int i = list.size()-1; i>=0; i--){ 
			System.out.println(list.get(i));
		}
		
		// Print the size of the list
		System.out.println();
		System.out.println("The size of this list is " + list.size());
	}

}

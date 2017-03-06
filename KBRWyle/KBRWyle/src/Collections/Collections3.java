package Collections;

/**
 * Daniel Anderson
 * KBRWyle Coding Challenge - Collections Third Question
 * 
 * Define a collection that contains a key/value pair
 * Add 5 key/value pairs to the collection
 * Add a duplicate key/value pair (one of the five that was added)
 * Check for duplicate key and print key/value if found (do not add it to the collection)
 * Print the key/value pairs
 * Print the size of the collection when done
 * Comment the code and explain why the specific collection type was chosen.
 */

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Collections3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// Used HashMap for this purpose mainly because I used it many times before 
		Map<Integer, String> map = new HashMap<Integer, String>(); 
		map.put(4, "Hello"); 
		map.put(6, "World"); 
		map.put(3, "How"); 
		map.put(2, "are"); 
		map.put(8, "you?"); 
		
		// Attempted to ass a duplicate key, original key/value was overwritten 
		System.out.println("Adding duplicate key...");
		map.put(3, "How"); 
		System.out.println("Duplicate Key: 3   Value: " + map.get(3)); 
		System.out.println();
		
		// Create keyset and Iterator to print the keys/values of the map
		Set<Integer> keyset = map.keySet(); 
		
		Iterator<Integer> iterator = keyset.iterator(); 
		while(iterator.hasNext()){ 
			int key = iterator.next();
			System.out.println("Key: " + key + "   Value: " + map.get(key));
		}
		
		// Print the size of the map
		System.out.println();
		System.out.println("The size of this map is " + map.size());
	}

}

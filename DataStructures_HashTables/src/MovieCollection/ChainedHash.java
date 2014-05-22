package MovieCollection;

/**
 * Class ChainedHash
 * 
 * Description: HashTable that adds movies to Linked Lists in the array  
 * 
 * @author dcanderson
 *
 */

public class ChainedHash implements HashTable{
 
	/**
	 * Instance variables
	 */
	private final int TABLE_SIZE = 31;
	
	private MovieLList[] table; 
	
	/**
	 * Default Constructor
	 */
	public ChainedHash(){
		table = new MovieLList[TABLE_SIZE]; 
		for(int i = 0; i<TABLE_SIZE; i++){ 
			table[i] = new MovieLList(); 
		}
	}
	
	/**
	 * add - uses search to add the Movie to the hash table
	 * @param movie
	 */
	public void add(Movie movie){ 
		int index = search(movie); 
		if( !table[index].isEmpty())
			System.out.println("Collision : " + movie.getName() + " will be added to the List. \n");
		table[index].addToFront(movie); 
	}
	
	/**
	 * delete - deletes a Movie from the table, uses search
	 * @param movie
	 * @return
	 */
	public Movie delete(Movie movie){ 
		return table[search(movie)].remove(movie); 
	}
	
	/**
	 * search - searches for the Movie, returns the index
	 * @param movie
	 * @return
	 */
	public int search(Movie movie){ 
		int index = movie.getKey() % TABLE_SIZE; 
		return index;
	}
	
	/**
	 * traceSearch - searches for the Movie, displays each access to the 
 * table
	 * @param movie
	 * @return
	 */
	public int traceSearch(Movie movie){
		int index = movie.getKey() % TABLE_SIZE;
		System.out.println("Trace search for movie " + movie.getName() + 
" starting at index " + index + ". ");
		table[index].search(movie);
		System.out.println();
		return index; 
	}
	
	/**
	 * showHash - prints a map of the hash table
	 */
	public void showHash(){ 
		System.out.println("Showing Hash Table:");
		for( int i = 0; i<table.length; i++){ 
			System.out.println("Index " + i + ": " + table[i] + "\t");
		}
		System.out.println();
	}

}

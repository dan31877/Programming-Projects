package MovieCollection;

/**
 * Class OpenHash
 * 
 * Description: OpenHash adds data to a HashTable by using a mod function to 
 * determine the index.  If the index is filled, collisions are handled using
 * a quadratic function. 
 * 
 * @author dcanderson
 *
 */
public class OpenHash implements HashTable{

	/**
	 * Instance variables
	 */
	private final int UNUSED = -1; 
	private final int USED_BEFORE = -2; 
	private final int TABLE_SIZE = 31; 
	private int power = 1; 
	private Movie[] table;
	private boolean adding; 
	
	/**
	 * Default Constructor
	 */
	public OpenHash(){ 
		table = new Movie[TABLE_SIZE];
		
		for( int i = 0; i<table.length; i++){ 
			Movie movie = new Movie("Unused", "Default", 1800); 
			movie.setKey(UNUSED);
			table[i] = movie; 
		}
	}

	/**
	 * add - uses search to add the Movie to the hash table
	 * @param movie
	 */
	public void add(Movie movie) {
		adding = true; 
		int index = search(movie); 
		table[index] = movie; 
	}

	/**
	 * search - searches for the Movie,  or a place to put the movie using 
	 * insertionSearch, returns the index
	 * @param movie
	 * @return
	 */
	public int search(Movie movie) {
		if( adding ){ 
			return insertionSearch(movie); 
		}
		else{ 
			resetPower(); 
			int index = movie.getKey() % TABLE_SIZE;
			try{ 
				while (table[index].getKey() != movie.getKey()){ 
					if( table[index].getKey() == UNUSED)
						throw new Exception("Item not found."); 
					index = quadraticFunction(index); 
				}
			}catch (Exception e){ 
				System.out.println(e.getMessage());
				//index = -1; 
			}
			return index; 
		}
	}

	/**
	 * insertionSearch - returns the index of the next unused space on the 
	 * list for a Movie
	 * @param movie
	 * @return
	 */
	private int insertionSearch(Movie movie){ 
		resetPower(); 
		int collision = 0;
		int index = movie.getKey() % table.length;
		while (table[index].getKey() != UNUSED){ 
			System.out.print("Error placing " + movie.getName()+ ". ");
			System.out.print("Collision " + ++collision + ": Index " + 
index + " was not available. ");
			index = quadraticFunction(index); 
			System.out.println("Trying index " + index + ". ");
		}
		if(collision > 0)
			System.out.println();
		return index; 
	}
	
	/**
	 * traceSearch - searches for the movie, displays each access to the 
	 * table
	 * @param movie
	 * @return
	 */
	public int traceSearch(Movie movie) {
		resetPower(); 
		System.out.println("Trace search on " + movie.getName() + ": ");
		int index = movie.getKey() % TABLE_SIZE;
		try{ 
			while (table[index].getKey() != movie.getKey()){ 
				if( table[index].getKey() == UNUSED)
					throw new Exception("" + movie.getName() + " is not on the table."); 
				System.out.println("" + movie.getName() + " not found at index " + index + ", moving on.");
				index = quadraticFunction(index); 
			}
			System.out.println("" + movie.getName() + " was found at index " + index + ".");
		}catch (Exception e){ 
			System.out.println(e.getMessage());
		}
		System.out.println();
		return index;
	}

	/**
	 * delete - deletes a Movie from the table, uses search
	 * @param movie
	 * @return
	 */
	public Movie delete(Movie movie) {
		Movie rtn; 
		adding = false; 
		int index = search(movie); 
		rtn = table[index]; 
		table[index] = new Movie("Used Before", "Default", 1900);
		table[index].setKey(USED_BEFORE);
		
		return rtn; 
	}

	/**
	 * resetPower - resets the power variable, used in the quadratic 
   	 * function for searches
	 */
	public void resetPower(){ 
		power = 1; 
	}
	
	/**
	 * quadraticFunction - uses a quadratic function for searching to 
	 * handle collisions. 
	 * @param index
	 * @return
	 */
	public int quadraticFunction(int index){
		index += (int)(Math.pow(power++, 2));
		index = index % table.length; 
		return index; 
		
	}

	/**
	 * showHash - prints a map of the hash table
	 */
	public void showHash(){ 
		System.out.println("Showing Hash Table:");
		for( int i = 0; i<table.length; i++){ 
			System.out.println("Index " + i + ": " + table[i]);
		}
		System.out.println();
	}
}

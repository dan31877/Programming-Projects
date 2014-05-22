package MovieCollection;

/**
 * Interface HashTable
 * 
 * Description: Movie class with the Movie name, genre, and year. Also has a  
 * key that is calculated and used for Hashing.  
 * 
 * @author dcanderson
 *
 */
public interface HashTable {

	
	/**
	 * add - uses search to add the Movie to the hash table
	 * @param movie
	 */
	public void add(Movie movie); 
	
	/**
	 * showHash - prints a map of the hash table
	 */
	public void showHash();
	
	/**
	 * search - searches for the Movie
	 * @param movie
	 * @return
	 */
	public int search(Movie movie); 
	
	/**
	 * traceSearch - searches for the movie, displays each access to the 
 * table
	 * @param movie
	 * @return
	 */
	public int traceSearch(Movie movie);
	
	/**
	 * delete - deletes a Movie from the table, uses search
	 * @param movie
	 * @return
	 */
	public Movie delete(Movie movie); 
	
}

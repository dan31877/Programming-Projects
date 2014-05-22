package MovieCollection;

/**
 * Class Movie
 * 
 * Description: Movie class with the Movie name, genre, and year. Also has a 
 * key that is calculated and used for Hashing.  
 * 
 * @author dcanderson
 *
 */

public class Movie {
	
	/**
	 * Instance variables
	 */
	private String name; 
	private String genre; 
	private int year; 
	private int key; 
	
	/**
	 * Default COnstructor
	 */
	public Movie(){ 
		
	}
	
	/**
	 * Constructor that sets the variables of the Movie
	 * @param name
	 * @param genre
	 * @param year
	 */
	public Movie( String name, String genre, int year){
		this.setName(name); 
		this.setGenre(genre);
		this.setYear(year); 
		this.generateKey(name);
	}

	
	/**
	 * getName - returns the Movie name
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * setName - sets the Movie name
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * getGenre - returns the Movie's genre
	 * @return
	 */
	public String getGenre() {
		return genre;
	}

	/**
	 * setGenre - sets the Movie's genre
	 * @param genre
	 */
	public void setGenre(String genre) {
		this.genre = genre;
	}

	/**
	 * getYear - gets the year the Movie was made
	 * @return
	 */
	public int getYear() {
		return year;
	}

	/**
	 * setYear - sets the year of the Movie
	 * @param year
	 */
	public void setYear(int year) {
		this.year = year;
	}
	
	/**
	 * generateKey - uses the first character of the Movie name to generate 
	 * a numeric key to be used in the table
	 * @param name
	 */
	public void generateKey(String name) {
		String tmp = name.toLowerCase(); 
		int c = (int)tmp.charAt(0); 
		if( c>=48 && c<=57){
			this.key = c-48; 
		}
		else if( c>=97 && c<=122){
			this.key = c-96; 
		}
	}
	
	/**
	 * getKey - returns the key
	 * @return
	 */
	public int getKey() {
		return key;
	}
	
	/**
	 * setKey - manually sets the Movie's key
	 * @param key
	 */
	public void setKey(int key){ 
		this.key = key;
	}
	
	/**
	 * equals - compares one Movie to another using Movie name and year
	 * @param movie
	 * @return
	 */
	public boolean equals(Movie movie){
		if( this.name.equalsIgnoreCase(movie.getName())
				&& this.getYear() == movie.getYear()){ 
			return true; 
		}
		else
			return false; 
	}
	
	/**
	 * toString - returns a string representation of the Movie
	 */
	public String toString(){
		String rtn = "";
		rtn = "Movie name: " + this.getName();
		return rtn; 
	}
}
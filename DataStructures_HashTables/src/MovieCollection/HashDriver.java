package MovieCollection;

/**
 * Daniel Anderson
 * CS 342   Due: 4/30/2013
 * Program 1
 * 
 * HashDriver.java
 * 
 * Description: Loads 15 Movies onto two versions of a Hash Table and shows
 * collisions. It deletes 5 Movies and then adds 5 new ones. Trace Search is
 * done to show a success and failure on each. 
 */
public class HashDriver {

	/**
	 * Main Method
	 * @param args
	 */
	public static void main(String[] args) {
		HashDriver driver = new HashDriver(); 
		OpenHash open = new OpenHash(); 
		ChainedHash chain = new ChainedHash(); 
		
		System.out.println("This program loads 15 Movies onto an open hash table then on a chained hash table. It will then \ndelete 5 movies and add 5 new ones. It will then do a trace search on a movie on the \nlist and a movie not on the list.\n");
		System.out.println("Run OpenHash: ");
		driver.doIt(open); 
		System.out.println("Run ChainedHash: ");
		driver.doIt(chain); 
		
	}

	/**
	 * doIt - runs for both open and chained hash tables. Loads 15 movies, 
 * deletes 5, adds 5, and runs two traceSearches. 
	 * @param table
	 */
	public void doIt(HashTable table){ 
		
		Movie movie1 = new Movie("Star Wars", "Sci-Fi", 1977);
		Movie movie2 = new Movie("Toy Story 3", "Animation", 2010);
		Movie movie3 = new Movie("Finding Nemo", "Animation", 2003);
		Movie movie4 = new Movie("Raiders of the Lost Ark", "Comedy", 
1981);
		Movie movie5 = new Movie("Harold & Kumar go to White Castle", 
"Comedy", 2004);
		Movie movie6 = new Movie("The Godfather", "Drama", 1972);
		Movie movie7 = new Movie("The Matrix", "Action", 1999);
		Movie movie8 = new Movie("Forrest Gump", "Comedy", 1994);
		Movie movie9 = new Movie("Monty Python and the Holy Grail", 
"Comedy", 1975);
		Movie movie10 = new Movie("Terminator", "Action", 1984);
		Movie movie11 = new Movie("Psycho", "Horror", 1960);
		Movie movie12 = new Movie("Tombstone", "Western", 1993);
		Movie movie13 = new Movie("Nightmare on Elm Street", 
"Horror",1984);
		Movie movie14 = new Movie("Napolean Dynamite", "Comedy",  2004);
		Movie movie15 = new Movie("Pulp Fiction", "Drama", 1994);
		
		Movie movie21  = new Movie("Superman", "Action", 1978);
		Movie movie22  = new Movie("Batman Begins", "Action", 2005);
		Movie movie23  = new Movie("Back to the Future", "Comedy", 1985);
		Movie movie24  = new Movie("The Matrix Reloaded", "Action", 
2003);
		Movie movie25  = new Movie("Kung Fu Panda", "Animation", 2001);
		
		Movie movie26 = new Movie("Office Space", "Comedy", 1999);
		
		Movie[] addFifteen = { movie1, movie2, movie3, movie4, movie5, 
movie6, movie7, movie8, movie9, movie10, movie11, movie12, movie13, movie14, movie15};

		Movie[] deleteFive = {movie1, movie3, movie10, movie11, movie15};
		
		Movie[] addFive = {movie21, movie22, movie23, movie24, movie25};
		
		System.out.println("Creating the initial table and adding 15 Movies.");
		for( int i = 0; i < addFifteen.length; i++){ 
			table.add(addFifteen[i]); 
		}
		
		table.showHash(); 
		System.out.println("Deleting 5 movies from the table: "); 
		for( int i = 0; i < deleteFive.length; i++){ 
			table.traceSearch(deleteFive[i]); 
			table.delete(deleteFive[i]); 
			System.out.println(deleteFive[i].getName() + " was deleted.\n");
		}
		
		table.showHash(); 
		System.out.println("Adding the follwing 5 new movies to the table: ");
		printArray(addFive); 
		for( int i = 0; i < addFive.length; i++){ 
			table.add(addFive[i]); 
		}
		
		System.out.println("Trace search for movie on the list (should succeed).");
		table.traceSearch(movie24); 
		
		System.out.println("Trace search for movie not on the list (should fail).");
		table.traceSearch(movie26); 
	}
	
	/**
	 * printArray - prints the contents of an array
	 * @param movieArray
	 */
	public void printArray( Movie[] movieArray){
		for( int i = 0; i< movieArray.length; i++){ 
			System.out.printf("%-25s",  movieArray[i].getName()); 
		}
		System.out.println("\n");
	}
}

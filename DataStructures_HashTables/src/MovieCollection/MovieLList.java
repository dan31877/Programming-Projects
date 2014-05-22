package MovieCollection;

/**
 * Class MovieLList
 * 
 * Description: A Linked List of Movies. Used for chain hashing and 
 * includes a MovieNode class  
 * 
 * @author dcanderson
 *
 */
public class MovieLList {
	
	// Instance variables
	private MovieNode head; 
	private int listSize; 
	
	/**
	 * Default Constructor
	 */
	public MovieLList(){ 
		head = null; 
		listSize = 0;
	}
	
	
	/**
	 * getListSize - returns the listSize
	 * @return
	 */
	public int getListSize() {
		return listSize;
	}
	
	/**
	 * isEmpty - returns true if the list is empty
	 * @return
	 */
	public boolean isEmpty(){ 
		return listSize == 0; 
	}
	
	
	/**
	 * addToFront - adds a Movie to the head
	 * @param data
	 */
	public void addToFront(Movie data){
		MovieNode temp = new MovieNode();
		temp.setData(data); 
		temp.setNext(head);
		head = temp; 
		listSize++; 
	}
	
	/**
	 * search - searches the list for the specified Movie. Keeps track of 
	 * the number of Nodes
	 * @param movie
	 * @return
	 */
	public Movie search(Movie movie){ 
		try{ 
			int nodeCount = 1; 
			if(head == null)
				throw new Exception("Your movie " + movie.getName() + 
" was not found."); 
			for( MovieNode mn = head; mn != null; mn = mn.getNext()){ 
				if( mn.getData().equals(movie)){ 
					System.out.println("Your movie, " + 
movie.getName() + ", was found in Node " + nodeCount + ".");
					return movie; 
				}
				System.out.println("" + movie.getName() + " not found in Node " + 
						nodeCount + ", checking Node " + ++nodeCount + ".");
			}
			throw new Exception("Your movie was not found.");
		}catch (Exception e){ 
			System.out.println(e.getMessage());
		}
		return null; 
	}
	
	/**
	 * remove - removes the Movie from the List
	 * @param obj
	 * @return
	 */
	public Movie remove(Movie obj){ 
		try{ 
			if(head == null)
				throw new Exception("Movie was not found.");
			MovieNode node = head; 
			MovieNode next = node.getNext(); 
			if( node.getData().equals(obj)){
				head = head.getNext(); 
				listSize--; 
				return node.getData(); 
			}
			else{
				while( next != null && ! next.getData().equals(obj)){ 
					next = next.getNext();
					node = node.getNext();
				}
				if( next == null)
					throw new Exception("Movie was not found."); 
				else{ 
					node.setNext(node.getNext().getNext());
					listSize--; 
					return next.getData();
				}
			}
		}catch (Exception e){ 
			System.out.println(e.getMessage());
		}
		return null; 
	}
	
	/**
	 * toString - returns a string representation of the list. 
	 * @return 
	 */
	public String toString(){
		
		String list = ""; 
		if( listSize == 0){ 
			list+= "The list is empty."; 
		}
		else{
			list += "List(size " + this.getListSize() + "): "; 
			MovieNode temp = new MovieNode();
			for(temp = head; temp != null; temp = temp.getNext()){
				list += temp.getData().getName() + " > ";
			}
			list+= "null"; 
		}	
		return list;
	}
	
	/**
	 * Class MovieNode
	 * 
	 * Description: Node of Movies to be used in the Linked List  
	 * 
	 * @author dcanderson
	 *
	 */	
	private class MovieNode {
		
		/**
		 * Instance variables
		 */
		private Movie data;
		private MovieNode next;
		
		/**
		 * getData - returns the data in the MovieNode
		 * @return
		 */
		public Movie getData() {
			return data;
		}
		
		/**
		 * setData - sets the data in the MovieNode
		 * @param data
		 */
		public void setData(Movie data) {
			this.data = data;
		}
		
		/**
		 * getNext - returns the next MovieNode
		 * @return
		 */
		public MovieNode getNext() {
			return next;
		}
		
		/**
		 * setNext - sets the next MovieNode
		 * @param next
		 */
		public void setNext(MovieNode next) {
			this.next = next;
		}
	  }

}

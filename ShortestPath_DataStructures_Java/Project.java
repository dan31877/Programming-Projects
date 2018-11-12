import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Scanner;


/**
 * Daniel Anderson
 * CS526   Due: 4/24/2018
 * Project
 * Project.java
 */

public class Project {
	
	
	/**
	 * The loadPoints method reads the file "direct_distance.txt" and uses the information to create all the 
	 * Points which are stored in an array of Point objects. 
	 * Input parameter: none
	 * Output: an array of Point objects
	 * Other: throws FileNotFoundException
	 * Note: File "direct_distance.txt" at line 36 and "graph_input.txt" at line 63
	 */
	public static Point[] loadPoints() throws FileNotFoundException { 
		
		// Define the Points and enter them into the ArrayList
		Point A=null, B=null, C=null, D=null, E=null, F=null, G=null, H=null, I=null, J=null, K=null, L=null, 
				M=null, N=null, O=null, P=null, Q=null, R=null, S=null, T=null, U=null, V=null, W=null, X=null, Y=null, Z=null;
		Point[] pointLetterArray = {A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z};
		ArrayList<Point> pointList = new ArrayList<Point>(); 
		
		// Scan the text file into the scanner
		Scanner ddScanner = new Scanner(new File("direct_distance.txt")); 
		
		// Runs the scanner for each line of the file, splits each line into an array
		while( ddScanner.hasNextLine()) { 
			String expression = ddScanner.nextLine(); 
			String[] expressionArray = expression.split("\\s+"); 
			//System.out.println(expressionArray[0]);
			
			// Finds the position in the pointArray where the current point is
			int index = expressionArray[0].charAt(0) - 'A'; 
			
			// Sets each Point in the array to a new Point with the name and direct distance from the file
			pointList.add(new Point(expressionArray[0], Integer.parseInt(expressionArray[1]))); 
		}
		
		// Get array from ArrayList
		Point[] pointArray = new Point[pointList.size()]; 
		pointArray = pointList.toArray(pointArray); 
		
		// Close the scanner and return pointArray
		ddScanner.close();
		return pointArray; 
	}
	
	/**
	 * The loadMatrix method reads the file "graph_input.txt" and loads the information from the file into 
	 * a two dimensional int array. This information stores the weight of the edges of adjacent Points. 
	 * Input parameter: none
	 * Output: a two dimensional int array giMatrix with the graph input information stored
	 * Other: throws FileNotFoundException
	 */
	public static int[][] loadMatrix() throws FileNotFoundException{ 
		
		// Scan the file, grab the first line
		Scanner giScanner = new Scanner(new File("graph_input.txt"));
		String header = giScanner.nextLine(); 
		
		// Split the first line into an array
		String[] headerArray = header.split("\\s+");
		int row=0, col=0;  // Set row and column equal to 0
		
		// Use the size of the headerArray to define the 2D array
		int[][] giMatrix = new int[headerArray.length - 1][headerArray.length - 1];
		while( giScanner.hasNextLine()) { 
			String expression = giScanner.nextLine();   // Get the next line from the scanner
			String[] expressionArray = expression.split("\\s+");  // Split into an array
			
			// Get the ints from each row, skips the first entry which is a letter
			for( int i=1; i<expressionArray.length; i++) {  
				giMatrix[row][col++] = Integer.parseInt(expressionArray[i]); 
			}
			col = 0;
			row++; 
		}
		
		// Close the scanner and return the matrix
		giScanner.close();
		return giMatrix; 
	}

	  /**
	   * Runs the first algorithm on one point, finds the next adjacent point with the lowest direct 
	   * distance to "Z".
	   * Input parameter: String prev, String current, int[][] giMatrix, Point[] pointArray, 
	   * 	ArrayDeque<Point> totalPath, ArrayDeque<Point> shortestPath
	   * Output: a Point that is the next adjacent point with the lowest direct distance to "Z"
	   */
	public static Point runAlgorithm1(String prev, String current, int[][] giMatrix, Point[] pointArray, 
			ArrayDeque<Point> totalPath, ArrayDeque<Point> shortestPath) {
		
		// Create a new PointLinkedBinaryTree
		PointLinkedBinaryTree tree = new PointLinkedBinaryTree(); 
		
		// Finds the position in the pointArray where the current point is
		int row = current.charAt(0) - 'A'; 
		
		// Creates the current Point, and the previous Point, from the one returned from the pointArray
		Point curr = new Point(pointArray[row]); 
		Point previous = new Point(pointArray[prev.charAt(0) - 'A']); 
		
		// If prev and current are equal, this is the first run and the current Point needs to be added
		if( prev.equals(current)) { 
			totalPath.addLast(curr); 
			shortestPath.addLast(curr); 
		}
		boolean wasVisited = false; 	// Set wasVisited to false
		
		// Checks the matrix for adjacent points and adds them to the tree if they have not already been visited
		for(int i=0; i<giMatrix[row].length; i++) { 
			if( giMatrix[row][i] != 0) { 
				Point point = new Point(pointArray[i], giMatrix[row][i]); 
				
				// Create new iterator, check the totalPath Deque for visited Points
				Iterator<Point> iterator = totalPath.iterator();  
				while( iterator.hasNext() ) { 
					if( iterator.next().equals(point)) {   // If the Point is in the Deque, set wasVisited to true
						wasVisited = true; 
					}
				}
				if( !wasVisited) {      // If wasVisited is false, add to the tree
					tree.add(tree.root(), point); 
				}
				wasVisited = false; 
			}
		}
		
		// If the tree is empty, then it's a dead end
		if( tree.isEmpty()) { 
			shortestPath.removeLast();  // Remove the current Point
			previous.setPrevPoint(true);   // Set prevPoint to true
			return previous;        // Return the previous Point to run again and find a different path
		} else {      // If the tree is not empty, return the first element of the tree
			return tree.getFirst(); 
		}
		
	}
	
	  /**
	   * Runs the second algorithm on one point, finds the next adjacent point to "Z" using the direct distance
	   * and weight.
	   * Input parameter: String prev, String current, int[][] giMatrix, Point[] pointArray, 
	   * 	ArrayDeque<Point> totalPath, ArrayDeque<Point> shortestPath
	   * Output: a Point that is the next adjacent point with the lowest direct distance to "Z"
	   * @return root Position of the tree (or null if tree is empty)
	   */
	public static Point runAlgorithm2(String prev, String current, int[][] giMatrix, Point[] pointArray, 
			ArrayDeque<Point> totalPath, ArrayDeque<Point> shortestPath) {
		
		// Create a new PointLinkedBinaryTree
		PointLinkedBinaryTree tree = new PointLinkedBinaryTree(); 
		
		// Finds the position in the pointArray where the current point is
		int row = current.charAt(0) - 'A'; 
		
		// Creates the current Point, and the previous Point, from the one returned from the pointArray
		Point curr = new Point(pointArray[row]); 
		Point previous = new Point(pointArray[prev.charAt(0) - 'A']); 
		
		// If prev and current are equal, this is the first run and the current Point needs to be added
		if( prev.equals(current)) { 
			totalPath.addLast(curr); 
			shortestPath.addLast(curr); 
		}
		boolean wasVisited = false; 	// Set wasVisited to false
		
		// Checks the matrix for adjacent points and adds them to the tree if they have not already been visited
		for(int i=0; i<giMatrix[row].length; i++) { 
			if( giMatrix[row][i] != 0) { 
				Point point = new Point(pointArray[i], giMatrix[row][i]); 
				
				// Create new iterator, check the totalPath Deque for visited Points
				Iterator<Point> iterator = totalPath.iterator();  
				while( iterator.hasNext() ) { 
					if( iterator.next().equals(point)) {   // If the Point is in the Deque, set wasVisited to true
						wasVisited = true; 
					}
				}
				if( !wasVisited) {      // If wasVisited is false, add to the tree
					tree.addDDAndWeight(tree.root(), point); 
				}
				wasVisited = false; 
			}
		}
		
		// If the tree is empty, then it's a dead end
		if( tree.isEmpty()) { 
			shortestPath.removeLast();  // Remove the current Point
			previous.setPrevPoint(true);   // Set prevPoint to true
			return previous;        // Return the previous Point to run again and find a different path
		} else {      // If the tree is not empty, return the first element of the tree
			return tree.getFirst(); 
		}
		
	}
	/**
	 * The main method creates the pointArray that includes all the Points from the direct distance text file and
	 * creates the giMatrix from the graph input text file. Two deques are created to store the Points for the 
	 * shortest path and total path. The runAlgorithm1 and runAlgorithm2 methods are run from the starting Point 
	 * entered by the user to "Z". The shortest path and total path deques are printed and cleared and the shortest 
	 * path length is displayed.
	 * Input parameter: String[] args, standard parameter for main method
	 * Output: none
	 * Other: throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub

		// Run the methods to return the pointArray and giMatrix
		Point[] pointArray =loadPoints();  // Array of all the Points
		int[][] giMatrix = loadMatrix(); 	// Matrix of graph input text file
		String validString = ""; 		// String valid points
		
		// Use to display valid points
		for(int i=0; i<pointArray.length; i++) { 
			if( i == pointArray.length -1) { 
				validString += pointArray[i].getName(); 
			} else { 
				validString += pointArray[i].getName() + ", ";
			}
		}
		
		// Create two Deques to store the Points for the totalPath and shortestPath 
		ArrayDeque<Point> shortestPath = new ArrayDeque<Point>();
		ArrayDeque<Point> totalPath = new ArrayDeque<Point>();
		
		// Validate the user entered a valid Point
		String startPoint = "";
		Scanner userScanner = new Scanner(System.in); // Create scanner for user input
		
		// Prompt the user for input, save as a String
		System.out.print("Please enter a valid capital letter. Valid letters include \n" + validString + ": ");
		String userInput = userScanner.nextLine(); 
		
		// Loop until user's entry is valid
		boolean isValid = false;  
		while( !isValid) { 
			
			// If user enters more than one letter/number, prompt the user again
			if( userInput.length() > 1) { 
				System.out.print("Please enter only one valid capital letter. Valid letters include \n" + validString + ": ");
				userInput = userScanner.nextLine(); 
			} else { 
				
				// If the user's input is not between A and T or isn't Z
				int charNum = userInput.charAt(0) - 'A'; 
				if( charNum < 0 || ( charNum > (pointArray.length - 2) && charNum != 25) ) { 
					System.out.print( "Your entry, " + userInput + ", is not valid. Please enter a valid capital letter. Valid letters include \n" + validString + ": ");
					userInput = userScanner.nextLine(); 
				}
				else { 
					startPoint = userInput;    // Set the startPoint to the userInput
					isValid = true; 		// Set isValid to true to stop the loop
				}
			}
			
		}
		userScanner.close();    // Close the scanner
		
		System.out.println("\nThis program uses two algorithms to find the shortest distance to point Z from the "
				+ "point you entered, point " +  startPoint +  "\n");
		/**
		 * Runs the runAlgorithm1 method until the currentPoint is "Z"
		 */
		System.out.println("Algorithm 1:\n");
		if( startPoint.equals("Z")) { 
			Point zpoint = new Point(startPoint, 0);
			totalPath.addLast(zpoint);
			shortestPath.addLast(zpoint);
		}
		String currentPoint = startPoint;
		String prevPoint = currentPoint;
		while ( !currentPoint.equals("Z")) {
			
			Point point = runAlgorithm1(prevPoint, currentPoint, giMatrix, pointArray, totalPath, shortestPath);
			totalPath.addLast(point);  // Adds the returned Point to the totalPath deque
			
			// Adds the returned Point to the shortestPath deque if it's not the previous Point
			if( !point.isPrevPoint()) {  
				shortestPath.addLast(point);
				prevPoint = currentPoint; 
			} 
			
			// If previous point is returned, set a new previous point
			else { 
				if( shortestPath.size() < 3)
					prevPoint = currentPoint;
				else {
					Iterator<Point> iterator = shortestPath.iterator(); 
					int i = 0; 
					Point temp = null; 
					while( iterator.hasNext() && i++<shortestPath.size() - 1) { 
						temp = iterator.next(); 
					}
					prevPoint = temp.getName();
				}
			}

			// Set current to the returned Point to run again
			currentPoint = point.getName(); 
			 
		} 
		
		// Prints out the Points from the totalPath deque and clears it
		System.out.print("\tSequence of all nodes: "); 
		while( !totalPath.isEmpty()) { 
			if( totalPath.size() != 1)
				System.out.print(totalPath.removeFirst().getName() + "->"); 
			else
				System.out.print(totalPath.removeFirst().getName()); 
		}
		System.out.println();
		
		// Prints out the Points from the shortestPath deque, clears it, and calculates total weight
		System.out.print("\tShortest path: "); 
		int weight = 0; 
		while( !shortestPath.isEmpty()) {
			weight += shortestPath.peekFirst().getWeight(); 
			if( shortestPath.size() != 1)
				System.out.print(shortestPath.removeFirst().getName() + "->"); 
			else
				System.out.print(shortestPath.removeFirst().getName()); 
		}
		System.out.println();
		
		// Displays the total weight as shortest path length
		System.out.print("\tShortest path length: " + weight + "\n\n"); 
	
	
		/**
		 * Runs the runAlgorithm2 method until the currentPoint is "Z"
		 */
		System.out.println("Algorithm 2:\n");
		if( startPoint.equals("Z")) { 
			Point zpoint = new Point(startPoint, 0);
			totalPath.addLast(zpoint);
			shortestPath.addLast(zpoint);
		}
		currentPoint = startPoint;
		prevPoint = currentPoint; 
		while ( !currentPoint.equals("Z")) {
			Point point = runAlgorithm2(prevPoint, currentPoint, giMatrix, pointArray, totalPath, shortestPath);
			totalPath.addLast(point);  // Adds the returned Point to the totalPath deque
			
			// Adds the returned Point to the shortestPath deque if it's not the previous Point
			if( !point.isPrevPoint()) {  
				shortestPath.addLast(point);
				prevPoint = currentPoint; 
			} 
			// If previous point is returned, set a new previous point
			else { 
				if( shortestPath.size() < 3)
					prevPoint = currentPoint;
				else {
					Iterator<Point> iterator = shortestPath.iterator(); 
					int i = 0; 
					Point temp = null; 
					while( iterator.hasNext() && i++<shortestPath.size() - 1) { 
						temp = iterator.next(); 
					}
					prevPoint = temp.getName();
				}
			}
			
			// Set current to the returned Point to run again
			currentPoint = point.getName(); 
			 
		} 
		
		// Prints out the Points from the totalPath deque and clears it
		System.out.print("\tSequence of all nodes: "); 
		while( !totalPath.isEmpty()) { 
			if( totalPath.size() != 1)
				System.out.print(totalPath.removeFirst().getName() + "->"); 
			else
				System.out.print(totalPath.removeFirst().getName()); 
		}
		System.out.println();
		
		// Prints out the Points from the shortestPath deque, clears it, and calculates total weight
		System.out.print("\tShortest path: "); 
		weight = 0; 
		while( !shortestPath.isEmpty()) {
			weight += shortestPath.peekFirst().getWeight(); 
			if( shortestPath.size() != 1)
				System.out.print(shortestPath.removeFirst().getName() + "->"); 
			else
				System.out.print(shortestPath.removeFirst().getName()); 
		}
		System.out.println();
		
		// Displays the total weight as shortest path length
		System.out.print("\tShortest path length: " + weight + "\n\n"); 
	}


}


/**
 * Daniel Anderson
 * CS526   Due: 4/24/2018
 * Project
 * Point.java
 */

/** 
 * The Point Class defines a point object with the attributes String name, int directDistance, 
 * boolean isPrevPoint, and int weight.
 */
public class Point {
	
	private String name; 			// Name of the point
	private int directDistance; 	// Direct distance of the point to the end point
	private boolean isPrevPoint; 	// Returns true if the Point has been previously used	
	private int weight;				// Weight of the edge between this point and another point
	
	
	/**
	 * This Constructor creates a new Point object using the parameters String name and int directDistance
	 * Input parameter: String name, int directDistance
	 * Output: none 
	 * Postcondition: A new Point object is created
	 */
	public Point(String name, int directDistance) {
		this.name = name;
		this.directDistance = directDistance;
		this.isPrevPoint = false;
		this.weight = 0; 
	}

	/**
	 * This Constructor creates a new Point object using the parameters Point point
	 * Input parameter: Point point
	 * Output: none 
	 * Postcondition: A new Point object is created from the Point passed in
	 */
	public Point(Point point) { 
		this.name = point.getName();
		this.directDistance = point.getDirectDistance();
		this.isPrevPoint = point.isPrevPoint();  
		this.weight = point.getWeight(); 
	}
	
	/**
	 * This Constructor creates a new Point object using the parameters Point point and int weight
	 * Input parameter: Point point, int weight
	 * Output: none 
	 * Postcondition: A new Point object is created from the Point passed in and the weight
	 */
	public Point(Point point, int weight) { 
		this.name = point.getName();
		this.directDistance = point.getDirectDistance();
		this.isPrevPoint = point.isPrevPoint(); 
		this.weight = weight;  
	}

	/**
	 * The getName method returns the name of the Point object
	 * Input parameter: none
	 * Output: String name of the Point
	 */
	public String getName() {
		return name;
	}

	/**
	 * The setName method sets the name of the Point instance to the name passed in as a parameter 
	 * Input parameter: String name
	 * Output: none
	 * Postcondition: The name of the Point instance to the name given
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * The getDirectDistance method returns the directDistance of the Point object
	 * Input parameter: none
	 * Output: int directDistance of the Point
	 */
	public int getDirectDistance() {
		return directDistance;
	}


	/**
	 * The setDirectDistance method sets directDistance of the Point instance to the directDistance passed in as a parameter 
	 * Input parameter: int directDistance
	 * Output: none
	 * Postcondition: The directDistance of the Point instance to the directDistance given
	 */
	public void setDirectDistance(int directDistance) {
		this.directDistance = directDistance;
	}


	/**
	 * The isPrevPoint method returns the isPrevPoint of the Point object
	 * Input parameter: none
	 * Output: boolean isPrevPoint of the Point
	 */
	public boolean isPrevPoint() {
		return isPrevPoint;
	}


	/**
	 * The setPrevPoint method sets the isPrevPoint of the Point instance to either true or false
	 * Input parameter: boolean isPrevPoint
	 * Output: none
	 * Postcondition: The isPrevPoint of the Point instance to either true or false
	 */
	public void setPrevPoint(boolean isPrevPoint) {
		this.isPrevPoint = isPrevPoint;
	}

	/**
	 * The getWeight method returns the weight of the Point object
	 * Input parameter: none
	 * Output: int weight of the Point
	 */	
	public int getWeight() {
		return weight;
	}

	/**
	 * The setWeight method sets the weight of the Point instance to the weight passed in as a parameter 
	 * Input parameter: int weight
	 * Output: none
	 * Postcondition: The weight of the Point instance to the weight given
	 */
	public void setWeight(int weight) {
		this.weight = weight;
	}

	/**
	 * The getDirectDistanceAndWeight method returns the sum of the direct distance and weight
	 * Input parameter: none
	 * Output: int sum of the direct distance and weight
	 */	
	public int getDirectDistanceAndWeight() { 
		return (this.directDistance + this.weight);
	}

	/**
	 * The equals method compares and Object to the Point instance and returns true if the Object 
	 * equals the Point, false otherwise 
	 * Input parameter: Object o
	 * Output: True if the Object equals the Point, false otherwise
	 * Cite: http://www.cafeaulait.org/course/week4/37.html
	 */
	public boolean equals(Object o) {
		  
		if (o instanceof Point) {
			Point p = (Point) o;
			if (this.name.equals(p.name) && this.directDistance == p.directDistance) return true;
		}
		return false;
    
	}
	
	/**
	 * The toString method generates a String representation of the Point instance
	 * Input parameter: none
	 * Output: String representation of the Point
	 */
	public String toString() { 
		return "Point " + this.name; 
	}


}

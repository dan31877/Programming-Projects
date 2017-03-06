package Shapes;

/**
 * Daniel Anderson
 * KBRWyle Coding Challenge
 * 
 * Shape Question - Square
 */

import static org.junit.Assert.*;

import org.junit.Test;

//Tests the getPerimeter method in Square
public class SquarePerimeterTest {

	@Test
	public void test() {
		Square square = new Square(8.0); 
		double perimeter = square.getPerimeter(); 
		assertEquals(32.0, perimeter, 0); 
	}

}

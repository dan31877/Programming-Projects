package Shapes;

/**
 * Daniel Anderson
 * KBRWyle Coding Challenge
 * 
 * Shape Question - Square
 */

import static org.junit.Assert.*;

import org.junit.Test;

// Tests the getArea method in Square
public class SquareAreaTest {

	@Test
	public void test() {
		Square square = new Square(6.0); 
		double area = square.getArea(); 
		assertEquals(36.0, area, 0); 
	}

}

package Shapes;

/**
 * Daniel Anderson
 * KBRWyle Coding Challenge
 * 
 * Shape Question - Triangle
 */

import static org.junit.Assert.*;

import org.junit.Test;

//Test for the getArea method in Triangle class
public class TriangleAreaTest {

	@Test
	public void test() {
		Triangle triangle = new Triangle(3.0, 4.0, 5.0, 2.0); 
		double area = triangle.getArea(); 
		System.out.println(area);
		assertEquals(5.0, area, 0); 
	}

}

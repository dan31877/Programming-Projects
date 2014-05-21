package BrickGUI;

/**
 * Daniel Anderson
 * CS 565   Due: 3/18/2014
 * Homework 3
 * Brick.java
 */

import java.awt.Color;

public class Brick { 
	
	/** Instance variables */ 
	private int startX, startY, width, height; 
	private boolean visible; 
	private Color color;
	
	/** Constructor: takes startX, startY, width, height as arguments*/ 
	public Brick(int startX, int startY, int width, int height){
		
		this.startX = startX;
		this.startY = startY;
		this.width = width; 
		this.height = height;
		this.setVisible(true); 
		
	}
	
	/** Accessors and Mutators */
	public int getStartX() {
		return startX;
	}


	public int getStartY() {
		return startY;
	}


	public int getWidth() {
		return width;
	}


	public int getHeight() {
		return height;
	}


	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	} 
	
	/** inRange: takes values (x, y),  mouse click, and returns true if this point is on the brick. */
	public boolean inRange(int x, int y){
		
		return (x <= (startX + width) && x >= startX && y <= (startY + height) && y >= startY );
	}
	
}

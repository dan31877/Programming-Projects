package BrickGUI;

/**
 * Daniel Anderson
 * CS 565   Due: 3/18/2014
 * Homework 3
 * RedAndBlackSteps.java
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class RedAndBlackSteps extends JFrame implements ActionListener{

	/** Instance variables */ 
	private int startX, startY, brickWidth, brickHeight, brickSpace; 
	private int numberOfRows; 
	private Brick[][] brickPattern; 
	
	private JPanel buttonPanel; 
	private JButton showAll; 
	private JButton hideAll; 
	
	private DrawPanel draw; 
	
	/** Default Constructor */ 
	public RedAndBlackSteps(){
		
		/** Assign values to instance variables. */
		super("Anderson"); 
		this.setStartX(400); 
		this.setStartY(50); 
		this.setBrickWidth(75); 
		this.setBrickHeight(50); 
		this.setBrickSpace(2); 
		this.numberOfRows = 5; 
		brickPattern = new Brick[numberOfRows][]; 
		
		showAll = new JButton("Show All");
		hideAll = new JButton("Hide All"); 
		showAll.addActionListener(this);
		hideAll.addActionListener(this);
		buttonPanel = new JPanel(); 
		buttonPanel.add(showAll);
		buttonPanel.add(hideAll);
		this.add(buttonPanel, BorderLayout.NORTH);
		
		/** Run the allocateBricks method to populate the array. */
		this.allocateBricks(numberOfRows); 
		
		/** Create and add a DrawPanel object. */
		draw = new DrawPanel(this); 
		this.add(draw, BorderLayout.CENTER); 
		
	}
	
	/** Allocate the bricks array with the appropriate Brick objects. Determine the coordinates and set the color for each brick. */
	public void allocateBricks(int rows){ 
		
		int columns = 1; 
		int startXRow = this.getStartX(); 
		int startYCol = this.getStartY();
		
		for(int i = 0; i < rows; i++){ 
			brickPattern[i] = new Brick[columns];
			int xVal = startXRow;
			for( int j = 0; j < columns; j++){ 
				brickPattern[i][j] = new Brick(xVal, startYCol, this.getBrickWidth(), this.getBrickHeight()); 
				if( j == 0 || j == (columns-1))
					brickPattern[i][j].setColor(Color.RED); 
				else
					brickPattern[i][j].setColor(Color.BLACK); 
				xVal += this.getBrickWidth() + this.getBrickSpace();
			}
			columns += 2; 
			startXRow -= (this.getBrickWidth() + this.getBrickSpace());
			startYCol += this.getBrickHeight() + this.getBrickSpace();
		}

		
		
	}
	
	/** Accessors and Mutators */
	public int getStartX() {
		return startX;
	}

	public void setStartX(int startX) {
		this.startX = startX;
	}

	public int getStartY() {
		return startY;
	}

	public void setStartY(int startY) {
		this.startY = startY;
	}

	public int getBrickWidth() {
		return brickWidth;
	}
	
	public Brick[][] getBrickPattern() {
		return brickPattern;
	}

	public void setBrickWidth(int brickWidth) {
		this.brickWidth = brickWidth;
	}

	public int getBrickHeight() {
		return brickHeight;
	}

	public void setBrickHeight(int brickHeight) {
		this.brickHeight = brickHeight;
	}

	public int getBrickSpace() {
		return brickSpace;
	}

	public void setBrickSpace(int brickSpace) {
		this.brickSpace = brickSpace;
	}

	public int getNumberOfRows() {
		return numberOfRows;
	}

	public void setNumberOfRows(int numberOfRows) {
		this.numberOfRows = numberOfRows;
	}

	/** Action set for the showAll and hideAll buttons. Sets each brick's visibility and repaints.  */
	public void actionPerformed(ActionEvent e) {
		
		boolean showOrHide = true; 
		if(e.getSource() == showAll){ 
			showOrHide = true; 
		}
		else if(e.getSource() == hideAll){ 
			showOrHide = false; 
		}
		
		for(int i = 0; i < numberOfRows; i++){ 
			for( int j = 0; j < brickPattern[i].length; j++){ 
				brickPattern[i][j].setVisible(showOrHide); 
			}
		}
		repaint();
		
	}
	
	/**  Main Method: Creates the JFrame. */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		RedAndBlackSteps bricks = new RedAndBlackSteps(); 
		bricks.setSize(900, 600);
		bricks.setVisible(true);
		bricks.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

	}
	
	/** Private Class used for Draw Panel.  */
	private class DrawPanel extends JPanel implements MouseListener{
		
		/** Creates an instance of RedAndBlackSteps */
		private RedAndBlackSteps bricks; 
		
		/** Constructor: takes RedAndBlackSteps as a parameter and adds the MouseListener. */ 
		public DrawPanel(RedAndBlackSteps bricks){ 
			this.bricks = bricks; 
			this.addMouseListener(this);
		}
		
		/** paintComponent: Draws the pattern of the bricks using their properties. */ 
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			for(int i = 0; i < bricks.getBrickPattern().length; i++){ 
				for( int j = 0; j < bricks.getBrickPattern()[i].length; j++){ 
					
					if(bricks.getBrickPattern()[i][j].isVisible()){
						g.setColor(bricks.getBrickPattern()[i][j].getColor()); 
						g.fillRect(bricks.getBrickPattern()[i][j].getStartX(), bricks.getBrickPattern()[i][j].getStartY(), 
								bricks.getBrickPattern()[i][j].getWidth(), bricks.getBrickPattern()[i][j].getHeight());
					}
					else{
						g.setColor(Color.GREEN);
						g.drawRect(bricks.getBrickPattern()[i][j].getStartX(), bricks.getBrickPattern()[i][j].getStartY(), 
								bricks.getBrickPattern()[i][j].getWidth(), bricks.getBrickPattern()[i][j].getHeight());
					}
				}
			}

		}
		
		/** When the mouse is clicked, determine if the mouse is on a brick and switches the visibility of that brick. */ 
		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			Point p = arg0.getPoint(); 
			int y = (int)p.getY(); 
			int x = (int)p.getX(); 

			/** Checks the Y value first to determine if the click is in a particular row. If so, checks all the bricks in that row. */
			for(int i = 0; i< bricks.getBrickPattern().length; i++){
				if(y >= bricks.getBrickPattern()[i][0].getStartY() && y <= bricks.getBrickPattern()[i][0].getStartY() + 
						bricks.getBrickPattern()[i][0].getHeight()){ 
					for( int j = 0; j < bricks.getBrickPattern()[i].length; j++){
						if(bricks.getBrickPattern()[i][j].inRange(x, y)){
							bricks.getBrickPattern()[i][j].setVisible(!bricks.getBrickPattern()[i][j].isVisible()); 
							super.repaint(); 
						}
					}
				}
			}
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

	}


}

package ControlStructure;

/**
 * Daniel Anderson
 * KBRWyle Coding Challenge
 * 
 * Control Structure Question
 */
public class ControlStructure {

	// Method given by the code challenge
	public static void givenMethod(){ 
		
		for (int i = 1; i <= 5; i++) {
			for (int j = 1; j <= (5 - i); j++) {
				System.out.print(".");
			}
			for (int k = 1; k <= i; k++) {
				System.out.print(i);
			}
			System.out.println();
		}
	}
	
	// Method that only uses a single construct
	// Rewritten to take int n rather than 5
	public static void singleConstructMethod(int n){ 
		
		// Single for loop to iterate through all values
		for(int i = 0; i < n * n; i++)
		{
		
		   // Divide values into rows and columns
		   int row = i / n;
		   int column = i % n;
		   
		   // Divide row to start printing values
		   int k = n - (row+1); 

		   if( column < k ){ 
			   System.out.print(".");
		   }
		   else{ 
			   System.out.print(row+1);
		   }
		   
		   // Return at the end of each row
		   if( column == (n-1) ){ 
			   System.out.println();
		   }
			   
		}
	
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// Prints output using supplied method
		System.out.println("Method supplied in code challenge: ");
		ControlStructure.givenMethod();
		System.out.println(); 
		
		// Prints output using single construct method
		System.out.println("Method with one construct: ");
		ControlStructure.singleConstructMethod(5);
		System.out.println();		

	}

}

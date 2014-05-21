package SpeakAndSpell;
/**
 * Daniel Anderson
 * CS 232   Due: 12/18/2012
 * Final Project
 * FlashCardMath.java
 */

public class FlashCardMath {
	
	// String variables to be used in the flashcards
	private String selection;
	private String firstnum; 
	private String secondnum; 
	private String sayOperator; 
	private String operator; 
	private String answer; 
	
	// Default Constructor
	public FlashCardMath(String string){ 
		
		// Sets selection and uses the String to generate a math sentence
		this.selection = string; 
		this.generate(string); 
	}
	
	// Get methods
	public String getSelection(){
		return selection; 
	}
	public String getFirst(){
		return firstnum; 
	}
	public String getSecond(){
		return secondnum; 
	}
	public String getOperator(){
		return operator; 
	}
	public String getSayOperator(){
		return sayOperator; 
	}
	public String getAnswer(){
		return answer; 
	}
	
	/**
	 * Uses a String to pick which math method to use
	 * @param str
	 */
	public void generate(String str){ 
		
		if(str.equalsIgnoreCase("single"))
			this.singleAddSubtract(); 
		else if(str.equalsIgnoreCase("double"))
			this.doubleAddSubtract();
		else if(str.equalsIgnoreCase("doublesingle"))
			this.doubleSingleAddSubtract();
		else if(str.equalsIgnoreCase("multiply"))
			this.multiply(); 
		else 
			this.divide();
	}	
	
	/**
	 * Adds or subtracts two random single digit numbers
	 */
	public void singleAddSubtract(){ 
		
		int answer; 
		int first = (int)(Math.random()*10); 
		int second = (int)(Math.random()*5) + (int)(Math.random()*5); 
		if(second>first){
			int temp = first;
			first = second; 
			second = first; 
		}
		int operator = (int)(Math.random()*2); 
		if( operator == 1){
			answer = first + second; 
			this.operator = "+"; 
			this.sayOperator = "plus"; 
		}
		else {
			answer = first - second; 
			this.operator = "-";
			this.sayOperator = "minus"; 
		}
		this.firstnum = Integer.toString(first); 
		this.secondnum = Integer.toString(second); 
		this.answer = Integer.toString(answer); 
	
		
	}
	
	/**
	 * Adds or subtracts two random double digit numbers
	 */
	public void doubleAddSubtract(){ 
		
		int answer; 
		int first = (int)(Math.random()*90 + 10); 
		int second = (int)(Math.random()*45 + 5) + (int)(Math.random()*45 + 5); 
		if(second>first){
			int temp = first;
			first = second; 
			second = first; 
		}
		int operator = (int)(Math.random()*2); 
		if( operator == 1){
			answer = first + second; 
			this.operator = "+"; 
			this.sayOperator = "plus";
		}
		else {
			answer = first - second; 
			this.operator = "-";
			this.sayOperator = "minus";
		}
		this.firstnum = Integer.toString(first); 
		this.secondnum = Integer.toString(second); 
		this.answer = Integer.toString(answer); 	
	}
	
	/**
	 * Adds or subtracts a mix of single and double digit numbers
	 */
	public void doubleSingleAddSubtract(){ 
		
		int answer; 
		int first = (int)(Math.random()*90 + 10); 
		int second = (int)(Math.random()*15); 
		if(second>first){
			int temp = first;
			first = second; 
			second = first; 
		}
		int operator = (int)(Math.random()*2); 
		if( operator == 1){
			answer = first + second; 
			this.operator = "+"; 
			this.sayOperator = "plus";
		}
		else {
			answer = first - second; 
			this.operator = "-";
			this.sayOperator = "minus";
		}
		this.firstnum = Integer.toString(first); 
		this.secondnum = Integer.toString(second); 
		this.answer = Integer.toString(answer); 
	}
	
	/**
	 * Multiplies two random numbers together.
	 */
	public void multiply(){ 
		
		int first = (int)(Math.random()*12); 
		int second = (int)(Math.random()*12); 
		int answer = first*second;
		
		this.firstnum = Integer.toString(first); 
		this.secondnum = Integer.toString(second); 
		this.answer = Integer.toString(answer);
		this.operator = "x"; 		
		this.sayOperator = "times"; 
	}

	/**
	 * Divides the first number by the second. Ensures the second isn't zero by adding 5 to a new
	 * random number.  Ensures there is no remainder.
	 */
	public void divide(){ 
		
		int answer; 
		int first = (int)(Math.random()*144); 
		int second = (int)(Math.random()*12); 
		if(second==0){
			second = (int)(Math.random()*12 + 5); 
		}
		if(first%second != 0)
			first = first + (second - first%second);
		answer = first/second; 
		
		this.firstnum = Integer.toString(first); 
		this.secondnum = Integer.toString(second); 
		this.answer = Integer.toString(answer);
		this.operator = "÷"; 
		this.sayOperator = "divided by"; 
	}
	
	/**
	 * Prints a math sentence using the current variables.
	 */
	public void print(){ 
		
		System.out.println(selection + ": " + firstnum + " " + operator + " " + 
				secondnum + " " + " = " + answer); 
		
	}
	
	//Main method: used for testing
	public static void main(String[] args){ 
		
		String[] array = {"single", "double", "triple", "multiply", "divide"};
		
		for(int i=0; i<array.length; i++){ 
			FlashCardMath fcm = new FlashCardMath(array[i]); 
			fcm.print();
			fcm.generate(array[i]); 
			fcm.print(); 
		}
		
	}

}

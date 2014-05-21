package ShoppingCart;
import java.util.HashMap;
import java.util.Scanner;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;          
import java.io.FileNotFoundException;

/**
 * Daniel Anderson
 * CS 232   Due: 12/11/2012
 * Assignment 7
 * ShoppingIO.java
 */

public class ShoppingIO implements Printable {
    
	
	private String fileName = ("Inventory.txt");
    private HashMap<String, String> map = new HashMap<String, String>(); 
    private Scanner inputStream = null;
    private PrintWriter outputStream = null;
    //System.out.println("The file " + fileName + "\ncontains the following lines:\n");
    
    /**
     * Default constructor: sets the map
     */
    public ShoppingIO(){ 
    	
    	map = getMap(); 
    }
    
    /**
     * Saves the file as a HashMap
     * @return
     */
    public HashMap<String, String> getMap(){ 
		
        try
        {
            inputStream = new Scanner (new File (fileName));
        }
        catch (FileNotFoundException e)
        {
            System.out.println ("Error opening the file " +
                    fileName);
            System.exit (0);
        }
        while (inputStream.hasNextLine ())
        {
            String line = inputStream.nextLine();
            int index = line.indexOf(','); 
            String name = line.substring(0, index); 
            String price = line.substring(index+1, line.length());
            map.put(name, price); 
            
            //System.out.println(name);

        }
        inputStream.close ();
        return map;
    	
    }
    
    /**
     * Prints the map
     */
    public void print(){ 
    	
    	int count; 
        for( count = 0; count<map.size(); count++){ 
        	String[] keys = map.keySet().toArray(new String[map.size()]);
        	
        	System.out.println ( keys[count] + "  " + map.get(keys[count]));
        	
        }
    	System.out.println("Map size: " + count); 
    }
	
    /**
     * Checks the map for a match and returns the price.  If there is 
     * no match, uses the append method to add the item to the list 
     * and file.
     * 
     * @param item
     * @return
     */
    public double getPrice(ShoppingItem item){
    	
    	double price = 0; 
    	boolean isOnList = false; 
    	for( int count = 0; count<map.size(); count++){
    		String[] keys = map.keySet().toArray(new String[map.size()]);
    		
    		if( keys[count].equalsIgnoreCase(item.getName())){ 
    			String p = map.get(keys[count]);
    			price = Double.parseDouble(p); 
    			isOnList = true; 
    			item.manualSetPrice(price);
    			item.setCost();
    			break;
    		} 
    		else{
    			price = item.getPrice(); 
    		}
    	}
    	if(!isOnList)
    		appendData(item.getName(), item.getPrice());
    	//System.out.println(isOnList);
    	return price;
    	
    }
    
    /**
     * Appends Data to the end of the map and file. 
     * 
     * @param str
     * @param d
     */
    private void appendData(String str, double d){
        try
        {
            outputStream = new PrintWriter (new FileOutputStream(fileName, true));
        }
        catch (FileNotFoundException e)
        {
            System.out.println ("Error opening the file " +
                    fileName);
            System.exit (0);
        }
        
        String price = Double.toString(d);
        map.put(str, price); 
        outputStream.println (str + "," + price);
        outputStream.close ();
    	
    }
	
    // Main Method for Testing
    public static void main (String [] args)
    {
      
        ShoppingIO io = new ShoppingIO(); 
        //HashMap<String, String> map = creater.getMap(); 
        ShoppingItem item1 = new ShoppingItem("Milk", 1); 
        ShoppingItem item2 = new ShoppingItem("Car", 2); 
        ShoppingItem item3 = new ShoppingItem("Car", 2);
        
        io.getPrice(item1);
        item1.print();
        
        io.getPrice(item2);
        item2.print();
        
        io.getPrice(item3);
        item3.print();
        
        
    }

}


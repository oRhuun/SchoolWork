// If you want to get user input within the program then you must use the Scanner class
// The Scanner class allows input from a variety of sources
// The Scanner API online will reveal to you the number of ways to get input

// We use the import statement to import classes that are not included in the standard Java package
// By the way, the Scanner class is a class in the "util" package

import java.util.Scanner;

public class GetUserInput
{

	public static void main(String[] args)
	{
	
		// Below we create a Scanner object and we name it "myInputObject"
		// Scanner is the data type and is always written first before the variable name
		// The "new" operator calls the constructor for the object 
		// The input "System.in" represents the standard keyboard input
		// Scanner object can be used to get input from the keyboard, files, or Strings.
	
		Scanner myInputObject = new Scanner(System.in);
	
	
		// Solicit user input	
	
		System.out.println("What is your name?");
		
		// Read the input from the command line after the user hits enter
		
		String yourName = myInputObject.nextLine();  
		
		
		
		// Solicit more user input
		
		System.out.println("What is your age?");
		
		// Read the input from the command line after the user hits enter
		
		int yourAge = myInputObject.nextInt(); 
		
		
		
		 // Solicit more user input
		
		System.out.println("How much did you spend on lunch today?");
		
		// Read the input from the command line after the user hits enter
		
		double yourLunchCost = myInputObject.nextDouble(); 
		
		
		// Write code that prints out a summary of the person's name, age, and amount they spent on lunch
		
		// Your code goes below here
		System.out.println("Name: " + yourName + 
							"\nAge: " + yourAge + 
							"\nMoney Spent on Lunch you Filthy Animal: $" + yourLunchCost +
							"\n");
	
	}
	
	
}
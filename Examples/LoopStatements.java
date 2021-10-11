
import java.util.Scanner;


public class LoopStatements
{

	public static void main(String[] args)
	{
	
		Scanner scanObject = new Scanner(System.in);
	
	
		// "for" loops are used if you know you want to do something a pre-determined number of times
	
		// simple for loop with three parts (index variable declaration and initialization, loop exit criteria(boolean), index variable increment or decrement
		for(int i = 0; i < 10; i++)
			System.out.println("We are on number " + i);
			
		// another for loop example
		for(int i = 10; i > 10; i--)
			System.out.println("We are on number " + i);
			
		// for loop example in which we increment by 2
		for(int i = 0; i < 10; i+=2)
			System.out.println("We are on number " + i);
			
		
		// for loop using a variable in the loop exit criteria statement
		int someValue = 14;
		for(int i = 5; i < someValue; i+=3)
			System.out.println("We are on number " + i);
	
		// crazy for loop that actually works (I'm not sure why you would do this.)
		int i = 0; 
		for( ; ; )
		{
			if (i >= 10)
				break; // the "break" statement will allow you to exit out of the current loop
			i++;
			System.out.println("We are on number " + i);
		}
		
		
		// "while" loops are used if you don't know how many times the loop will need to go
		
		// simple "while" loop
		String input = "";
		while (! input.equals("stop"))
		{
			System.out.println("If you want this loop to stop then type \"stop\" .");
			input = scanObject.nextLine(); // don't forget this line, othewise you will have an infinite loop
		}
			
		
		// "do while" loops are like while loops except they execute at least one time
		
		// simple "do while" loop
		do
		{
			System.out.println("If you want this loop to stop then type \"dkehidhlk\" .");
			input = scanObject.nextLine(); 
		}while(! input.equals("dkehidhlk")); // notice the semicolon
		
		
		// nested loops
		for(int j = 0; j < 10; j++)
			for(int k = 0; k < 3; k++)
				System.out.println(" j * k = " + (j * k));
				
		// more nested loops
		// There is a lot going on in the code below. Make sure you understand it.
		String input2 = "";
		int counter = 3;
		while (! input2.equals("no more"))
		{
			counter++;
			for(int m = 3; m < Math.pow(counter, 2); m+=4)
				System.out.println("m is equal to " + m);
				
			System.out.println("This outer loop has run " + (counter - 3) + " times.");
			
			System.out.println("If you want this loop to stop then type \"no more\" .");
			input2 = scanObject.nextLine();
		}
		
		
		// processing a string using a while loop and a scanner object
		// Scanners can be used with the standard input, (System.in), with File objects, and with String objects
		// Look at the Java Scanner API to know all the ways to create a Scanner object using the Scanner constructors in the API
		String somePhrase = "Look deep into nature, and then you will understand everything better.";
		Scanner scanObject2 = new Scanner(somePhrase);
		int index = 0;
		String temp = "";
		String token = "";
		String goal = "an";
		
		while(scanObject2.hasNext())
		{
			token = scanObject2.next();
			index = token.indexOf(goal);
			if(index > -1) 
			 	temp = temp + " " + (token.replaceAll("an", "PQ"));
			else
			 	temp = temp + " " + token;
		}
		
	
	}
}


public class StringFun
{
	//This program illustrates the use of String objects
	//In java, all Strings are objects and are not primitive data types
	//some java data types are below
	//primitive - int, double, float, char, boolean
	//object - Strings and many others including classes that you write
	//In other words, any class you write defines a new data type !


	public static void main(String[] args)
	{
		
		// Below exemplifies one way to create a string. 
		
		String myString = "The snow is falling.";
		
		// Below exemplifies another way to create a string.
		// This method is the general method for creating objects from a class
		// When creating objects we call a special method called a "constructor"
		// The constructor is a method contained in the class that initializes all the
		// instance variables of the object and allocates memory for the object
		// Notice the "new" keyword which calls the constructor
		// Notice where the data type "String" is written in the following statement
		
		String myString2 = new String ("The snow is blowing.");

		
		// Below we use the "+" symbol with strings to concatenate them together 
		// Notice the string which contains 2 spaces in the middle of the two other strings
		
		System.out.println(myString + "  " + myString2);
		
		// Strings are objects and objects have characteristics and behaviors or actions
		// To find out more about an objects characteristics one should either read through
		// the entire implementation of the class or go to the API online
		// The API is the "Application Programming Interface"
		// Google "Java String class" and then take a look.
		// Look for the fields, constructors, and methods because there is a lot that you can do with a string
		// There are many ways to construct a string as you can see in the number of 
		// different constructor methods that are displayed in the API but we will only use a few of them
		
		
		// Below we will use some of the methods available in the String class
		
		String s1 = "The sun is bright.";
		
		String s2 = "Water slides are fun.";
		
		// notice how we call a method on an object  <variable name>.<method name>();
		// The parentheses can have input parameters 
		
		
		// Print out the following and verify that it is correct
		int s1Length = s1.length();  
		
		// What does this method call produce?
		// Print this out to find out what it produces.
		// What is the input to this method? What is the output?
		String s3 = s1.substring(4); 

		// What does this method call produce?
		// Print this out to find out what it produces.
		// What is the input to this method? What is the output?
		String s4 = s1.substring(3, 10); 
		
		// What does this method call produce?
		// Print this out to find out what it produces.
		// What is the input to this method? What is the output?
		int s5 = s1.indexOf("un"); 
		
		// Are there other String methods from the API that you might be able to use?
		// Below use at least 2 more String methods that you find in the API
		
		// code goes here
		System.out.println(s1 + "\n" + s1Length);
		System.out.println(s3);
		System.out.println(s4);
		System.out.println(s5);	
		
		System.out.println(s1.toUpperCase());
		System.out.println(s1.toLowerCase());
	}
}
//In Java programs are written using classes
//A class is a template for object construction
//Think of a class as a blueprint for object construction
//We always capitalize the name of a class
//The class name and the file name MUST be the same

public class MyFirstClass	{
	public static void main(String[] args)	{
	
		//public is a visibility modifier
		//static means that an object doesn't have to be created for us to use the code
		//void means that this "method" doesn't return anything
		//remember that a "method" is like a "function"
		//main is the name of the "method"
		//the main method has one input parameter called "args"
		//the data type of "args" is a String array
		//the main method is the starting point for all programs
		//the JFM (Java Virtual mMachine) calls the main method
		//the String array input is for command line input
		
		System.out.println("I love the big nasty, Jack Schweizer!");
		
		//System.out refers to an object that represents the standard output from the computer system
		//This object has a method called "println"
		//"println" will output the given String to the screen
		//Notice the dot notation
		//ObjectName.methodName()
		
	}
}
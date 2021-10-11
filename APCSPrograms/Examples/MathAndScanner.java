import java.util.Scanner; //package imported to get input easier; not a part of the standard java package

public class MathAndScanner {
	public static void main(String args[]){
		
		//create a scanner object using System.in
		//System.in is an object that represents the standard input (keyboard)
		Scanner myScannerThing = new Scanner(System.in);		
		
		//solicit the user
		System.out.println("Hey, how old are you?");
		int age = myScannerThing.nextInt();
		
		//raise 3 to the power of 2 using Math class
		//Math is a static class - Doesn't need to be instantiated in order to use the methods
		//See the rest of them in the Math class API
		double d1 = Math.pow(age, 2);	//	3^2 = 9
		
		System.out.println("Your age squared is " + d1);


	}
}
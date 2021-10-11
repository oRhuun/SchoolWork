
//this program illustrates the use of variables and constants
//we look at variable declaration and variable assignment
//we also look at using arithmetic operations to do calculations

public class OperatorFun
{



	// write some additional code at the bottom to test some of the following statements
	
	public static void main(String[] args)
	{
		int someNumber; 	// variable declaration --> data-type variable-name
		
		someNumber = 14;	// variable assignment --> variable-name = value
		
		int someOtherNumber = 22; // variable declaration and assignment in one line
		
		int a = 9, b = 0, c = 0; // declare 3 variables and make an assignment to one of them
		
		double x, y, z = 1.43, q = 4.5, r; // declare 5 variables and make an assignment to two of them
		
		someOtherNumber = 50; // assign a different value to a variable
		
		final int HEARTRATE = 70; // final indicates a constant, constants are written using uppercase letters
								// constants cannot be assigned a different value once they are assigned
		
		
		double result = someNumber + a * z; // order of operations applies like it does in mathematical expressions
		
		double result1 = (someNumber + a) * z; // the parentheses change the value of the expression
		
		double result2 = someNumber / 3; // integer division truncates the decimal part of the calculation
										// integer division can be tricky and cause you to obtain values you didn't expect
		
		double result3 = someNumber % 5; // modulus operator yields the remainder, not the quotient
		
		double result4 = someNumber / 5 * 3; // what is the difference between the result you get in result4 and result5?
		
		double result5 = someNumber / 5.0 * 3; // why are result4 and result5 different?
		
		double d1;
		
		int n1 = 7;
		
		d1 = n1; // is this allowed?
		
		d1 = 4.74;
		
		//n1 = d1; // is this allowed? no doubles cant be stored in ints
		
		n1 = (int)d1; // is this allowed? (what does this mean)
		
		char someCharacter = '4';
		
		//n1 = someCharacter; // is this allowed? No chars can't be stored in ints
		
		//someCharacter = n1; // is this allowed? No ints can't be stored in chars
		
		
		// Write your additional code below this line
		System.out.println(result + "\n" + result1 + "\n" + result2 + "\n" + result3
		 	+ "\n" + result4+ "\n" + result5);
	
	}
	
	
}
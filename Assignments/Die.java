import java.util.Random;


public class Die
{
	private int faceValue;
	private int numberSides;
	
	// We are going to OVERLOAD the Die class constructor. We are giving the user more than one way to create a Die object.
	
	public Die() // default, no-argument constructor 
	{
		faceValue = 1;
		numberSides = 6;
	}
	
	public Die(int n) // constructor that allows you to specify the number of sides on the die
	{
		faceValue = 1;
		numberSides = n;
	}

	public void roll()
	{
		Random generator = new Random();
		faceValue = generator.nextInt(numberSides) + 1; // Why do we need to do this?
	}
	
	public int getFaceValue()
	{
		return faceValue;
	}
	
	public int getNumberSides()
	{
		return numberSides;
	}


}
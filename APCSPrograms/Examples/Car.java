
public class Car extends RoadVehicle implements Mover
{

	private int mpg;
	private boolean safetyInspection;
	private boolean doesRun = true;


	public Car(int nw, String et, int a, int cw, String m, String v, String c, boolean nr, int mp, boolean si)
	{
		super(nw, et, a, cw, m, v, c, nr); // a call to the parent class's constructor
		mpg = mp; // a variable that is specific to car objects
		safetyInspection = si; // a variable that is specific to car objects
	}
	
	public String toString ()  // example of overriding a method inherited from the parent class
	{
		return "This is a car.";
	}
	
	public int getMPG ()
	{
		return mpg;
	}
	
	public boolean getSafetyInspection ()
	{
		return safetyInspection;
	}
	
	public void setSafetyInspection (boolean s)
	{
		safetyInspection = s;
	}
	
	public String move()
	{
		return "I am moving in my car and my color is " + color; // where did the color variable come from?
	}
	
	public boolean isRunning() // if this method is not implemented in this class then we cannot make objects from this class
	{
		if (doesRun == false)
			return false;
		else
			return true;
			
		// I could have just returned doesRun
	}

}
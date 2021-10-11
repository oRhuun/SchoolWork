
public class Truck extends RoadVehicle implements Mover
{

	private int carryCapacity; // cubic feet
	private boolean doesRun = true; // does the truck actually run


	public Truck(int nw, String et, int a, int cw, String m, String v, String c, boolean nr, int cc)
	{
		super(nw, et, a, cw, m, v, c, nr); // a call to the parent class's constructor
		carryCapacity = cc;
	}
	
	public Truck() //example of overloaded constructor method
	{
		super (18, "diesel", 1, 12, "Ford", "1HGCM82633A004352", "green", false);
		carryCapacity = 2400; // a variable that is specific to truck objects
	}
	
	public String toString ()  // example of overriding a method inherited from the parent class
	{
		return "This is a truck.";
	}
	
	public int getCarryCapacity ()
	{
		return carryCapacity;
	}
	
	public String move()
	{
		return "I am trucking and my color is " + color; // where did the color variable come from?
	}
	
	public boolean isRunning() // if this method is not implemented in this class then we cannot make objects from this class
	{
		if (doesRun == false)
			return false;
		else
			return true;
			
		// I could have just returned doesRun
	}
	
	public void setRunning(boolean r)
	{
		doesRun = r;
	}

}
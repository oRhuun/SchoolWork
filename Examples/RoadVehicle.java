

public abstract class RoadVehicle
{

	//abstract classes cannot be instantiated 
	//abstract classes do not have to contain abstract methods (although this class does have abstract methods)
	//if a class has an abstract method it must be declared abstract

	protected int numberWheels;
	protected String engineType; //2 stroke gas, 4 stroke gas, diesel, electric
	protected int age;
	protected int curbWeight;
	protected String maker;
	protected String vin; // vehicle identification number
	protected String color;
	protected boolean needsRepair;
	
	// protected means that these variables are inherited by subclasses
	// private members are not inherited
	
	public RoadVehicle (int nw, String et, int a, int cw, String m, String v, String c, boolean nr)
	{
		numberWheels = nw;
		engineType = et;
		age = a;
		curbWeight = cw; 
		maker = m;
		vin = v;
		color = c;
		needsRepair = nr;
	}
	
	public String toString()
	{	return "This is a road vehicle.";}
	
	protected int getNumberWheels()
	{	return numberWheels; }
	
	protected String getEngineType()
	{ 	return engineType; }
	
	protected int getAge()
	{	return age; }
	
	protected int getCurbWeight()
	{	return curbWeight; }
	
	protected String getMaker()
	{	return maker; }
	
	protected String getVin()
	{	return vin; }
	
	protected String getColor()
	{	return color; }
	
	protected abstract boolean isRunning(); // this tells us whether the vehicle is functional, this method must be implemented in non-abstract subclasses
	
}
	
	


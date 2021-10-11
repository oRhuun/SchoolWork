// get user input and convert kilometers to miles

public class ConvertToMiles	{

	public static void main(String[] args)	{
	
		//declare variables kilometers and miles
		String kilometers = args[0];
		double km = Double.parseDouble(kilometers); //convert the String kilometers to the double km
		double miles;

		//process, convert kilometers to miles
		miles = km * 0.621371;
		
		//output - display miles
		System.out.println("Miles: " + miles);
		
	}
}
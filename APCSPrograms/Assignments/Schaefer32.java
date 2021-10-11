//Take the radius of a sphere and output diameter circumference, surface area, and volume
import java.util.Scanner;

public class Schaefer32 {
	public static void main(String args[]){
		
		//declare scanner
		Scanner keyboard = new Scanner(System.in);
		
		//solicit user for radius - the good stuff
		System.out.println("What is the radius of your sphere?");
		double radius = keyboard.nextInt();
		
		//calculate the good stuff
		double diameter = 2 * radius;
		double circumference = 2 * Math.PI * radius;
		double surfaceArea = 4 * Math.PI * Math.pow(radius, 2);
		double volume = 4/3 * Math.PI * Math.pow(radius, 3);
		
		//Tell those babies the good stuff	
		System.out.println("Your sphere:\nDiameter: " + diameter + 
							"\nCircumference: " + circumference +
							"\nSurface Area: " + surfaceArea +
							"\nVolume: " + volume);
	}
}
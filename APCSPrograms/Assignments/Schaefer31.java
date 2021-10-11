//take the length of an edge and print the cubes surface area
import java.util.Scanner;

public class Schaefer31 {
	public static void main(String args[]) {
		
		//create a scanner object
		Scanner keyboard = new Scanner(System.in);
		
		//solicit the user for the cube edge
		System.out.println("What is the length of the edge of your cube?");
		int edgeLength = keyboard.nextInt();
		
		//find surface area of cube from the edge length
		int sideArea = edgeLength * edgeLength;
		int surfaceArea = sideArea * 6;
		
		//shout at the user
		System.out.println("Surface Area: " + surfaceArea);
		
	}
}
		
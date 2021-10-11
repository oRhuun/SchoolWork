
public class VehicleDriver // pardon the pun
{


	public static void main(String[] args)
	{
	
		System.out.println ("*************************************************************************************");
		System.out.println ("");
		
	
		Car c1 = new Car (4, "gas", 7, 1800, "Nissan", "1234567891234", "blue", false, 35, true);
		Truck t1 = new Truck (18, "diesel", 4, 14, "Mercedes Benz", "a1b2c3d4ij4578", "orange", false, 3000);
		
		System.out.println ("PRINTING OUT INFORMATION OF CAR AND TRUCK OBJECTS");
		System.out.println ("");
		System.out.println ("The age of the car is " + c1.getAge()); // getAge() is inherited from RoadVehicle
		System.out.println ("The age of the truck is " + t1.getAge()); // getAge() is inherited from Road Vehicle 
		System.out.println ("My mpg is " + c1.getMPG()); // getMPG() is a method of the Car class
		System.out.println ("My carrying capacity is " + t1.getCarryCapacity()); // getCarryCapacity() is a method of the Truck class
		System.out.println ("It is " + c1.isRunning() + " that car c2 is running"); // isRunning() is inherited from RoadVehicle
		System.out.println ("It is " + t1.isRunning() + " that truck t3 is running"); // isRunning() is inherited from RoadVehicle
		System.out.println (c1.move()); // move() is "inherited" from the Mover interface
		System.out.println (t1.move()); // move() is "inherited" from the Mover interface
		
		System.out.println ("*************************************************************************************");
		System.out.println ("");
		
		Car c2 = new Car (4, "electric", 7, 1400, "Ford", "1234567ac451234", "yellow", false, 58, true);
		Truck t2 = new Truck (18, "diesel", 2, 10, "Chevrolet", "a1b2c3582j4578", "pink", false, 2200);
		
		System.out.println ("PRINTING OUT INFORMATION OF ROADVEHICLE OBJECTS");
		System.out.println ("");
		System.out.println ("The age of the car is " + c2.getAge()); // getAge() is inherited from RoadVehicle
		System.out.println ("The age of the truck is " + t2.getAge()); // getAge() is inherited from Road Vehicle 
		System.out.println ("My mpg is " + c2.getMPG()); // getMPG() is a method of the Car class
		System.out.println ("My carrying capacity is " + t1.getCarryCapacity()); // getCarryCapacity() is a method of the Truck class
		System.out.println ("It is " + c2.isRunning() + " that car c2 is running"); // isRunning() is inherited from RoadVehicle
		System.out.println ("It is " + t2.isRunning() + " that truck t3 is running"); // isRunning() is inherited from RoadVehicle
		System.out.println (c2.move()); // move() is "inherited" from the Mover interface
		System.out.println (t2.move()); // move() is "inherited" from the Mover interface
		
		System.out.println ("*************************************************************************************");
		System.out.println ("");
		
		Car c3 = new Car (3, "electric", 6, 1400, "Ford", "1234567ac451234", "yellow", false, 58, true);
		Truck t3 = new Truck (18, "diesel", 2, 10, "Chevrolet", "a1b2c3582j4578", "red", true, 2200);
		
		System.out.println ("PRINTING OUT INFORMATION OF MOVER OBJECTS");
		System.out.println ("");
		 System.out.println ("The age of the car is " + c3.getAge()); // getAge() is inherited from RoadVehicle
		 System.out.println ("The age of the truck is " + t3.getAge()); // getAge() is inherited from Road Vehicle 
		 System.out.println ("My mpg is " + c3.getMPG()); // getMPG() is a method of the Car class
		 System.out.println ("My carrying capacity is " + t3.getCarryCapacity()); // getCarryCapacity() is a method of the Truck class
		 System.out.println ("It is " + c3.isRunning() + " that car c2 is running"); // isRunning() is inherited from RoadVehicle
		 System.out.println ("It is " + t3.isRunning() + " that truck t3 is running"); // isRunning() is inherited from RoadVehicle
		System.out.println (c3.move()); // move() is "inherited" from the Mover interface
		System.out.println (t3.move()); // move() is "inherited" from the Mover interface
		
		System.out.println ("*************************************************************************************");
		System.out.println ("");
		
		RoadVehicle c4 = new Car (4, "electric", 4, 1400, "Ford", "57fj4567ac45a234", "white", false, 58, true);
		RoadVehicle t4 = new Truck (12, "diesel", 8, 15, "Chevrolet", "b437j2c3582j4578", "black", false, 2000);
		RoadVehicle c5 = new Car (4, "electric", 4, 1400, "Ford", "1234567ac45a234", "white", false, 58, true);
		RoadVehicle t5 = new Truck (12, "diesel", 8, 15, "Chevrolet", "39ds237j2c3582j4578", "black", false, 2000);
		
		
		RoadVehicle[] garageOne = new RoadVehicle[4];
		garageOne[0] = c4;
		garageOne[1] = t4;
		garageOne[2] = c5;
		garageOne[3] = t5;
		
		System.out.println ("POLYMORPHIC BEHAVIOR THROUGH CLASS INHERITANCE !!! ");
		for (RoadVehicle myVehicle : garageOne)	// for each loop in java
			System.out.println(myVehicle.getVin());
			
		System.out.println ("*************************************************************************************");
		System.out.println ("");
			
		Mover c6 = new Car (4, "electric", 4, 1400, "Ford", "1234567ac45a234", "gray", false, 58, true);
		Mover t6 = new Truck (14, "diesel", 8, 15, "Chevrolet", "a137j2c3582j4578", "light blue", false, 2000);
		Mover c7 = new Car (4, "electric", 4, 1400, "Ford", "1234567ac45a234", "bright green", false, 58, true);
		Mover t7 = new Truck (20, "diesel", 8, 15, "Chevrolet", "a137j2c3582j4578", "bright orange", false, 2000);
			
		Mover[]  garageTwo = new Mover[4];
		garageTwo[0] = c6;
		garageTwo[1] = t6;
		garageTwo[2] = c7;
		garageTwo[3] = t7;
		
		System.out.println ("POLYMORPHIC BEHAVIOR THROUGH INTERFACE INHERITANCE !!! ");
		for (Mover myMover : garageTwo)	// for each loop in java
			System.out.println(myMover.move());
			
		System.out.println ("");
		
	
	}
	
	
	
}
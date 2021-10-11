import java.util.Scanner;

public class Schaefer131{
	public static void main(String[] args){
	
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Enter your first Integer");
		int num1 = keyboard.nextInt();
		System.out.println("Enter your second Integer");
		int num2 = keyboard.nextInt();
		
		int large = 0;
		int small = 0;

		
		if(num1 > num2) {
			large = num1;
			small = num2;
		}else if(num1 < num2) {
			large = num2;
			small = num1;
		}

		System.out.println("The greatest common factor is " + findGcf(large, small));
	}
	
	public static int findGcf(int n1, int n2){
		int gcf = 0;
		int temp = 0;
		
		if(n1 % n2 == 0)
			gcf = n2;
		else {
			temp = n1 % n2;
			gcf = findGcf(n2, temp);
		}
		
		return gcf;
	
	}
}
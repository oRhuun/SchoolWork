import java.util.Random;

public class MathFun {
	public static void main(String[] args) {
	
		double d1 = Math.abs(-7);
		double d2 = Math.pow(3,2);
		double d3 = Math.max(45, 82);
		double d4 = Math.min(45,82);
		double d5 = Math.sqrt(48);
		double d6 = Math.sqrt(-4);
	
		System.out.println(d1 + "\n" +
							d2 + "\n" +
							d3 + "\n" +
							d4 + "\n" +
							d5 + "\n" +
							d6 + "\n" +
							Math.PI);
	
	Random generator = new Random();
	
	int n1 = generator.nextInt(12); //0-11, nextInt does 1 less than the specified value
	System.out.println(n1);
	
	double d7 = generator.nextDouble() * 9 + 1; //doubles starting with 1, through 9
	System.out.println(d7);
	
	}
}
public class Factorial {
	public static void main(String[] args) {
		
		int result = multiply(4);
		System.out.println("The result is " + result);
		
	}
	
	public static int multiply(int n) {
	
	if(n == 0)						//base case
		return 1;
	else							//recursive case
		return n * multiply(n-1);
	
	}
}
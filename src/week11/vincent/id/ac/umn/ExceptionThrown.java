package week11.vincent.id.ac.umn;

public class ExceptionThrown {

	static int dividedByZero(int a, int b) {
		int i = a/b;
		return i;
	}
	
	static int computeDivision(int a, int b) {
		int res = 0;
		try {
			res = dividedByZero(a,b);
		}
		
		catch(NumberFormatException ex) {
			System.out.println("NumberFormatException is occured");
		}
		return res;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a = 1;
		int b = 0;
		try {
			int i = computeDivision(a,b);
		}
		catch(ArithmeticException ex) {
			System.out.println(ex.getMessage());
		}
	}

}

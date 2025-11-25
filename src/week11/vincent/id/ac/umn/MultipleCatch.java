package week11.vincent.id.ac.umn;

import java.util.Scanner;
public class MultipleCatch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scn = new Scanner(System.in);
		try
		{
			int n = Integer.parseInt(scn.nextLine());
			
			int result = 10/n;
			System.out.println(result);
		}
		catch (ArithmeticException ex)
		{
			System.out.println("Arithmetic " + ex);
		}
		catch (NumberFormatException ex)
		{
			System.out.println("Number Format Exception " + ex);
		}
	}

}

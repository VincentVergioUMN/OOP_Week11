package week11.vincent.id.ac.umn;

public class CustomizeException {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			int[] arr = new int[4];
			int i = arr[4];
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}

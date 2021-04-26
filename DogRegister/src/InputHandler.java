
//Emmy Eriksson.

import java.util.Scanner;

public class InputHandler {
	private Scanner scan = new Scanner(System.in);

	public int readInt() {

		int i = scan.nextInt();
		scan.nextLine();

		return i;

	}

	public String readString() {

		return scan.nextLine();
	}

	public double readDouble() {

		double i = scan.nextDouble();
		scan.nextLine();

		return i;
	}

}
	



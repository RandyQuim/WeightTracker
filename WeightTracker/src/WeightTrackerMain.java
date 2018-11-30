import java.util.Scanner;

public class WeightTrackerMain {
	private static Scanner input;

	public static void main(String[] args) {
		FileIO file = new FileIO();
		Function function = new Function(file);
		input = new Scanner(System.in);
		String choice = queryInput();

		while (!(choice.equals("q"))) {
			if (choice.equals("w")) {
				file.openWriter();
				System.out.print("\nPlease enter your weight in lbs: ");
				String weight = function.dateTime() + " Weight: " + input.next() + " lbs. ";
				file.write(weight);
			} else if (choice.equals("h")) {
				file.openReader();
				file.readFile();
				System.out.println();
			} else if (choice.equals("f")) {
				System.out.print("\nEnter 'a' to get the average weight over the last week, \nor 't' to get the total weight loss to date: ");
				System.out.println("\n" + function.userFunction(input.next()));
			}

			choice = queryInput();
		}

		System.out.print("\nProgram ended...");
		input.close();
		file.close();
	}

	private static String queryInput() {
		System.out.print("Enter 'w' to add a weight, 'h' to see history, \n'f' for further functions, or 'q' to quit: ");
		return input.next().toLowerCase();
	}
}

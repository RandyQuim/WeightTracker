import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Function {
	private FileIO file;
	private LocalDateTime dateTime;
	private ArrayList<Double> weights;
	private int stringsToSkip;

	public Function(FileIO file) {
		this.file = file;
		this.weights = new ArrayList<Double>();
		this.dateTime = null;
		this.stringsToSkip = 0;
	}

	public String userFunction(String choice) {
		String result = "";
		currentDate();
		if (choice.equals("a")) {
			double difference = Double.valueOf(average()) - currentWeight();
			result = "The week's average is: " + average() + "\nCurrent weight is: " + currentWeight()
					+ "\nWith a difference of: " + String.valueOf(difference) + "\n";
		} else if (choice.equals("t")) {
			result = "Total weight loss to date: " + totalWeightLoss() + "\n";
		}

		return result;
	}

	public String dateTime() {
		currentDate();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("a");
		return dateTime.getMonth() + " " + dateTime.getDayOfMonth() + " " + dateTime.getYear() + " At: "
				+ dateTime.getHour() + ":" + dateTime.getMinute() + " " + dateTime.format(format);
	}

	private String average() {
		weights.clear();
		findWeekDates();
		double total = 0.0;
		for (int i = 0; i < weights.size(); i++) {
			total += weights.get(i);
		}

		return String.valueOf(total / weights.size());
	}

	private void findWeekDates() {
		weights = new ArrayList<Double>();
		file.openReader();
		stringsToSkip = 4;
		while (file.nextString()) {

			if (file.read().equals(String.valueOf(dateTime.getMonth()))
					&& Integer.parseInt(file.read()) >= (dateTime.getDayOfMonth() - 7)) {
				weights.add(findWeight());
			}
		}
	}

	private void currentDate() {
		this.dateTime = LocalDateTime.now();
	}

	private String totalWeightLoss() {
		stringsToSkip = 6;
		file.openReader();
		double first = findWeight();
		double last = currentWeight();
		return String.valueOf(first - last);
	}

	private double currentWeight() {
		file.openReader();
		double currentWeight = 0.0;
		while (file.nextString()) {

			if (file.read().equals(String.valueOf(dateTime.getMonth()))
					&& file.read().equals(String.valueOf(dateTime.getDayOfMonth()))) {
				stringsToSkip = 4;
				currentWeight = findWeight();
			}
		}

		return currentWeight;
	}

	private double findWeight() {
		for (int i = 0; i <= stringsToSkip; i++) {
			file.read();
		}

		return Double.parseDouble(file.read());
	}
}

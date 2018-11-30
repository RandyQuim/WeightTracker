import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class FileIO {
	private Scanner fileIn;
	private PrintWriter fileOut;
	
	public FileIO() {
		this.fileIn = null;
		this.fileOut = null;
	}
	
	public void openWriter() {
		try {
			fileOut = new PrintWriter(new FileOutputStream("weightTracker.txt", true));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void openReader() {
		try {
			if (fileIn != null) {
				fileIn.close();
			}
			fileIn = new Scanner(new FileInputStream("weightTracker.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void write(String weight) {
		fileOut.println(weight);
		fileOut.flush();
	}
	
	public String readLine() {			
		return fileIn.nextLine();
	}
	
	public String read() {
		return fileIn.next();
	}
	
	public void readFile() {
		System.out.println();
		while (nextString()) {
			System.out.println(readLine());
		}
	}
	
	public boolean nextString() {
		return fileIn.hasNext();
	}
	
	public void close() {
		if (fileIn != null) {
			fileIn.close();
		}
		if (fileOut != null) {
			fileOut.close();
		}
	}
}

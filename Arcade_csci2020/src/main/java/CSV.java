package mainApp;

import java.util.ArrayList;
import java.util.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.util.Arrays;

public class CSV {
	public ArrayList<ArrayList<String>> outer = new ArrayList<ArrayList<String>>();
	public ArrayList<String> inner = new ArrayList<String>();
	public int row, col;

	public void read(String userName, String currentFile, String level) throws IOException {
		// System.out.print(currentFile);
		File file = new File(currentFile);
		try (Scanner scan = new Scanner(file)) {
			while (scan.hasNextLine()) {
				String line = scan.nextLine();
				String[] details = line.split(",");
				if (details[0].equals(userName)) {
					col = row;
				}
				for (int i = 0; i < details.length; i++) {
					inner.add(details[i]);
				}
				outer.add(inner);
				inner = new ArrayList<String>();
				row++;
			}
		}
		boolean cheack = false;
		try (Scanner scan2 = new Scanner(file)) {
			while (scan2.hasNextLine()) {
				String line = scan2.nextLine();
				String[] details = line.split(",");
				for (int i = 0; i < details.length; i++) {
					if (details[0].equals(userName) && !details[i].equals(level)) {
						cheack = true;
					}
				}
				if (details[0].equals(userName) && !details[details.length - 1].equals(level) && cheack == true) {
					outer.get(col).add(level);

				}
			}
		}
	}

	public void save(String currentFile) {
		File file = new File(currentFile);
		try {
			PrintWriter out = new PrintWriter(file);
			for (int i = 0; i < outer.size(); i++) {
				String[] arrayOfStrings = outer.get(i).toArray(new String[outer.get(i).size()]);
				for (int j = 0; j < arrayOfStrings.length; j++) {
					out.write(arrayOfStrings[j] + ",");
				}
				out.write("\n");
			}
			out.close();
		} catch (IOException e) {
		}
	}
}
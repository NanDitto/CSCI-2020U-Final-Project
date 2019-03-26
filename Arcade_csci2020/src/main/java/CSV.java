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
/*
 Used to read or store content from the file
*/
public class CSV {
	public ArrayList<ArrayList<String>> outer = new ArrayList<ArrayList<String>>(); 
	public ArrayList<String> inner = new ArrayList<String>();
	public int row, col;

	public void read(String userName, String currentFile, String level) throws IOException {
		File file = new File(currentFile); // reading the file
		try (Scanner scan = new Scanner(file)) {
			while (scan.hasNextLine()) {
				String line = scan.nextLine();
				String[] details = line.split(","); // spliting all the content in the line by a commma and storing in an array
				if (details[0].equals(userName)) {
					col = row;
				}
				for (int i = 0; i < details.length; i++) {
					inner.add(details[i]); // adding items to the arraylist so we can add to the file later
				}
				outer.add(inner); /// adding the sub arraylist to the outer subarray
				inner = new ArrayList<String>();
				row++; // counter  used to get the number of rows
			}
		}
		boolean cheack = false;
		try (Scanner scan2 = new Scanner(file)) { // reading the file agian 
			while (scan2.hasNextLine()) {
				String line = scan2.nextLine();
				String[] details = line.split(",");
				for (int i = 0; i < details.length; i++) {
					// checking to see if the level needs to be stored or not
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
		File file = new File(currentFile); // reading the file
		try {
			PrintWriter out = new PrintWriter(file); // opeining the file and writing to it
			for (int i = 0; i < outer.size(); i++) {
				String[] arrayOfStrings = outer.get(i).toArray(new String[outer.get(i).size()]); // changing the sub arrylist into an array
				for (int j = 0; j < arrayOfStrings.length; j++) {
					out.write(arrayOfStrings[j] + ","); // writing to the file
				}
				out.write("\n");
			}
			out.close();
		} catch (IOException e) {
		}
	}
}
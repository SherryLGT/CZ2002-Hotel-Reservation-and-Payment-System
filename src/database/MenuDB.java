package database;

import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.FileInputStream;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.StringTokenizer;

import entity.Menu;

public class MenuDB {
	public static final String SEPARATOR = "|";

	public ArrayList readMenu(String filename) throws IOException {
		// UtilityDB.read String from text file
		ArrayList stringArray = (ArrayList) UtilityDB.read(filename);
		ArrayList alr = new ArrayList();// to store Menu data

		for (int i = 0; i < stringArray.size(); i++) {
			String st = (String) stringArray.get(i);
			// get individual 'fields' of the string separated by SEPARATOR
			StringTokenizer star = new StringTokenizer(st, SEPARATOR); // pass in the string to the string tokenizer using delimiter ","

			int id = Integer.parseInt(star.nextToken().trim());
			String name = star.nextToken().trim(); // first token
			String description = star.nextToken().trim(); // second token
			double price = Double.parseDouble(star.nextToken().trim()); // third
																		// token

			// create Professor object from file data
			Menu menu = new Menu(id, name, description, price);
			// add to Menu list
			alr.add(menu);
		}
		return alr;
	}

	public void saveMenu(String filename, List al) throws IOException {
		List alw = new ArrayList();// to store Professors data

		for (int i = 0; i < al.size(); i++) {
			Menu menu = (Menu) al.get(i);
			StringBuilder st = new StringBuilder();
			st.append(menu.getID());
			st.append(SEPARATOR);
			st.append(menu.getName().trim());
			st.append(SEPARATOR);
			st.append(menu.getDescription().trim());
			st.append(SEPARATOR);
			st.append(menu.getPrice());
			alw.add(st.toString());
		}
		UtilityDB.write(filename, alw);
	}
}

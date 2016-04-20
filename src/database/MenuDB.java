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

/**
 * DB Class for data access for the menu controller.
 * 
 * @author Tan Wanyi Cherry
 * @version 1.0
 * @since 2016-03-30
 */

public class MenuDB {
	
	/**
	 * Delimiter for data in text file.
	 */
	public static final String SEPARATOR = "|";
	
	/**
	 * Reading of menu item data from text file.
	 * 
	 * @param filename
	 *            To specify the name of text file to read.
	 * @return arraylist the list of menu item data taken from the text file.
	 */
	public ArrayList readMenu(String filename) throws IOException {
		
		ArrayList stringArray = (ArrayList) UtilityDB.read(filename);
		ArrayList alr = new ArrayList();

		for (int i = 0; i < stringArray.size(); i++) {
			String st = (String) stringArray.get(i);
			
			StringTokenizer star = new StringTokenizer(st, SEPARATOR); 

			int id = Integer.parseInt(star.nextToken().trim());
			String name = star.nextToken().trim(); 
			String description = star.nextToken().trim(); 
			double price = Double.parseDouble(star.nextToken().trim()); 
																	

			Menu menu = new Menu(id, name, description, price);
			alr.add(menu);
		}
		return alr;
	}
	
	
	/**
	 * Saving of menu item data into the text file.
	 * 
	 * @param filename
	 *            To specify the name of text file to read.
	 * @param al
	 *            The list of menu items to store into the text file.
	 */
	public void saveMenu(String filename, List al) throws IOException {
		List alw = new ArrayList();

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

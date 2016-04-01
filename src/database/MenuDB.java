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

    // an example of reading
	public static ArrayList readMenu(String filename) throws IOException {
		// read String from text file
		ArrayList stringArray = (ArrayList)read(filename);
		ArrayList alr = new ArrayList() ;// to store Menu data

        for (int i = 0 ; i < stringArray.size() ; i++) {
				String st = (String)stringArray.get(i);
				// get individual 'fields' of the string separated by SEPARATOR
				StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter ","
				
				int id = Integer.parseInt(star.nextToken().trim());
				String  name = star.nextToken().trim();	// first token
				String  description = star.nextToken().trim();	// second token
				double  price = Double.parseDouble(star.nextToken().trim()); // third token
				// create Professor object from file data
				Menu menu = new Menu(id, name, description, price);
				// add to Menu list
				alr.add(menu) ;
			}
			return alr ;
	}

  // an example of saving
public static void saveMenu(String filename, List al) throws IOException {
		List alw = new ArrayList() ;// to store Professors data

        for (int i = 0 ; i < al.size() ; i++) {
				Menu menu = (Menu)al.get(i);
				StringBuilder st =  new StringBuilder() ;
				st.append(menu.getID());
				st.append(SEPARATOR);
				st.append(menu.getName().trim());
				st.append(SEPARATOR);
				st.append(menu.getDescription().trim());
				st.append(SEPARATOR);
				st.append(menu.getPrice());
				alw.add(st.toString()) ;
			}
			write(filename,alw);
	}

  /** Write fixed content to the given file. */
  public static void write(String fileName, List data) throws IOException  {
    PrintWriter out = new PrintWriter(new FileWriter(fileName));

    try {
		for (int i =0; i < data.size() ; i++) {
      		out.println((String)data.get(i));
		}
    }
    finally {
      out.close();
    }
  }

  /** Read the contents of the given file. */
  public static List read(String fileName) throws IOException {
	List data = new ArrayList() ;
    Scanner scanner = new Scanner(new FileInputStream(fileName));
    try {
      while (scanner.hasNextLine()){
        data.add(scanner.nextLine());
      }
    }
    finally{
      scanner.close();
    }
    return data;
  }
}

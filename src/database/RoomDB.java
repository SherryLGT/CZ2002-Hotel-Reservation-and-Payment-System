package database;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import entity.Guest;
import entity.Room;

public class RoomDB {

	public static final String SEPARATOR = "|";

    /*
     * Read Room
     */
	
	@SuppressWarnings("rawtypes")
	public static ArrayList readRoom(String filename) throws IOException {
		
		// Read String from text file
		ArrayList stringArray = (ArrayList)read(filename);
		// To store Room date
		ArrayList alr = new ArrayList() ;

        for (int i = 0 ; i < stringArray.size() ; i++) {
			String st = (String)stringArray.get(i);
			// Get individual 'fields' of the string separated by SEPARATOR
			StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter ","
			
			Room room = new Room();
			room.setRoomNo(Integer.parseInt(star.nextToken().trim()));
			
			// room.setDetails(star.nextToken().trim());
			
			// Missing out array 
			
			room.setStatus(star.nextToken().trim());
			
			
			// Add to Guest list
			alr.add(room) ;
		}
		return alr ;
	}

  /** Write fixed content to the given file. */
  @SuppressWarnings("rawtypes")
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
  @SuppressWarnings({ "rawtypes", "unchecked" })
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

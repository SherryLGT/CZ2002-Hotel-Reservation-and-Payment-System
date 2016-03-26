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

public class GuestDB {
	public static final String SEPARATOR = "|";

    /*
     * Read Guest
     */
	@SuppressWarnings("rawtypes")
	public static ArrayList readGuest(String filename) throws IOException {
		
		// read String from text file
		ArrayList stringArray = (ArrayList)read(filename);
		ArrayList<Guest> guestList = new ArrayList<Guest>() ;// to store guest data

        for (int i = 0 ; i < stringArray.size() ; i++) {
			String st = (String)stringArray.get(i);
			// get individual 'fields' of the string separated by SEPARATOR
			StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter ","
			
			Guest guest = new Guest();
			guest.setName(star.nextToken().trim());
			
			guest.getCard().setType(star.nextToken().trim());	
			guest.getCard().setNum(star.nextToken().trim());	
			guest.getCard().setCvv(star.nextToken().trim());	
			guest.getCard().setExp(star.nextToken().trim());	
			
			guest.getAddress().setAdd1(star.nextToken().trim());
			guest.getAddress().setAdd2(star.nextToken().trim());
			guest.getAddress().setCity(star.nextToken().trim());
			guest.getAddress().setState(star.nextToken().trim());
			guest.getAddress().setZip(star.nextToken().trim());
			
			guest.setCountry(star.nextToken().trim());
			
			guest.getIdentity().setLic(star.nextToken().trim());
			guest.getIdentity().setPp(star.nextToken().trim());
			
			guest.setNationality(star.nextToken().trim());
			
			guest.setContact(Integer.parseInt(star.nextToken().trim()));
			
			// add to Guest list
			guestList.add(guest) ;
		}
		return guestList ;
	}

	/*
	 * Save Guest
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void saveGuest(String filename, List al) throws IOException {
		List alw = new ArrayList() ;// to store Guest data

        for (int i = 0 ; i < al.size() ; i++) {
        	Guest guest = (Guest)al.get(i);
			StringBuilder st =  new StringBuilder() ;
			st.append(guest.getName().trim());
			st.append(SEPARATOR);
			st.append(guest.getCard().getType().trim());
			st.append(SEPARATOR);
			st.append(guest.getCard().getNum().trim());
			st.append(SEPARATOR);
			st.append(guest.getCard().getCvv().trim());
			st.append(SEPARATOR);
			st.append(guest.getCard().getExp().trim());
			st.append(SEPARATOR);
			st.append(guest.getAddress().getAdd1().trim());
			st.append(SEPARATOR);
			st.append(guest.getAddress().getAdd2().trim());
			st.append(SEPARATOR);
			st.append(guest.getAddress().getCity().trim());
			st.append(SEPARATOR);
			st.append(guest.getAddress().getState().trim());
			st.append(SEPARATOR);
			st.append(guest.getAddress().getZip().trim());
			st.append(SEPARATOR);
			st.append(guest.getCountry().trim());
			st.append(SEPARATOR);
			st.append(guest.getIdentity().getLic().trim());
			st.append(SEPARATOR);
			st.append(guest.getIdentity().getPp().trim());
			st.append(SEPARATOR);
			st.append(guest.getNationality().trim());
			st.append(SEPARATOR);
			st.append(Integer.toString(guest.getContact()).trim());
			alw.add(st.toString());
		}
		write(filename,alw);
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

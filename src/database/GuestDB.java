package database;

import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.FileInputStream;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.StringTokenizer;
import entity.Guest;
import entity.Guest.Address;
import entity.Guest.CreditCard;
import entity.Guest.Identity;

public class GuestDB {
	public static final String SEPARATOR = "|";

	/*
	 * Read Guest
	 */

	@SuppressWarnings("rawtypes")
	public static ArrayList readGuest(String filename) throws IOException {

		// Read String from text file
		ArrayList stringArray = (ArrayList)read(filename);

		// To store Guest data
		ArrayList<Guest> alr = new ArrayList<Guest>() ;

		for (int i = 0 ; i < stringArray.size() ; i++) {
			String st = (String)stringArray.get(i);

			// Get individual 'fields' of the string separated by SEPARATOR
			StringTokenizer star = new StringTokenizer(st , SEPARATOR);	// pass in the string to the string tokenizer using delimiter ","

			
			
			Guest guest = new Guest();

			while(star.hasMoreTokens())
			{
			
			guest.setName(star.nextToken().trim());

			guest.setGender(star.nextToken().trim());

			CreditCard cc = guest.new CreditCard();
			guest.setCard(cc);
			guest.getCard().setType(star.nextToken().trim());	
			guest.getCard().setNum(star.nextToken().trim());	
			guest.getCard().setCvv(star.nextToken().trim());	
			guest.getCard().setExp(star.nextToken().trim());	

			Address add = guest.new Address();
			guest.setAddress(add);
			guest.getAddress().setAdd1(star.nextToken().trim());
			guest.getAddress().setAdd2(star.nextToken().trim());
			guest.getAddress().setCity(star.nextToken().trim());
			guest.getAddress().setState(star.nextToken().trim());
			guest.getAddress().setZip(star.nextToken().trim());

			guest.setCountry(star.nextToken().trim());

			Identity ident = guest.new Identity();
			guest.setIdentity(ident);
			guest.getIdentity().setLic(star.nextToken().trim());
			guest.getIdentity().setPp(star.nextToken().trim());

			guest.setNationality(star.nextToken().trim());

			guest.setContact(star.nextToken().trim());

			// Add to Guest list
			alr.add(guest);
			
			}
			
		}

		return alr;

	}

	/*
	 * Save Guest
	 */

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void saveGuest(String filename, List al) throws IOException {

		// To store Guest data
		List als = new ArrayList() ;

		for (int i = 0 ; i < al.size() ; i++) {
			Guest guest = (Guest)al.get(i);
			StringBuilder st =  new StringBuilder();
			
			st.append(guest.getName().trim());
			st.append(SEPARATOR);
			
			st.append(guest.getGender().trim());
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
			
			st.append(guest.getContact().trim());
			als.add(st.toString());
		}
		write(filename,als);
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

	public static void main(String[] aArgs)  {
		GuestDB guestDB = new GuestDB();
		String filename = "guest.txt";

		try {

			// read file containing Guest records
			ArrayList<Guest> al = GuestDB.readGuest(filename);

			for (int i = 0 ; i < al.size() ; i++) {
				Guest guest = (Guest)al.get(i);
				System.out.println("Name " + guest.getName());
				System.out.println("Gender " + guest.getGender());
				System.out.println("Credit Card Type: " + guest.getCard().getType());
				System.out.println("Credit Card No.: " + guest.getCard().getNum());
				System.out.println("Credit Card CVV: " + guest.getCard().getCvv());
				System.out.println("Credit Card Expiry Date:  " + guest.getCard().getExp());
				System.out.println("Address Line 1: " + guest.getAddress().getAdd1());
				System.out.println("Address Line 2: " + guest.getAddress().getAdd2());
				System.out.println("City: " + guest.getAddress().getCity());
				System.out.println("State: " + guest.getAddress().getState());
				System.out.println("Zip: " + guest.getAddress().getZip());
				System.out.println("Country: " + guest.getCountry());
				System.out.println("Driving License: " + guest.getIdentity().getLic());
				System.out.println("Passport No.: " + guest.getIdentity().getPp());
				System.out.println("Nationality: " + guest.getNationality());
				System.out.println("Contact: " + guest.getContact());

			}

			// Write Guest records to file
			GuestDB.saveGuest(filename, al);

		}catch (IOException e) {
			System.out.println("IOException > " + e.getMessage());
		}
	}

}

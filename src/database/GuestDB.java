package database;

import java.io.IOException;

import java.util.List;
import java.util.ArrayList;
import java.util.StringTokenizer;
import entity.Guest;
import entity.Guest.Address;
import entity.Guest.CreditCard;
import entity.Guest.Identity;

/**
 * DB Class for data access for guest controller.
 * 
 * @author Nicole Liow Wei Xuan
 * @version 1.0
 * @since 2016-03-30
 */

public class GuestDB {

	/**
	 * Delimiter for data in text file.
	 */
	public static final String SEPARATOR = "|";

	/**
	 * Reading of guest data from text file.
	 * 
	 * @param filename
	 *            To specify the name of text file to read.
	 * @return arraylist the list of guest data taken from the text file.
	 */
	public static ArrayList readGuest(String filename) throws IOException {

		// Read String from text file
		ArrayList stringArray = (ArrayList) UtilityDB.read(filename);

		// To store Guest data
		ArrayList alr = new ArrayList();

		for (int i = 0; i < stringArray.size(); i++) {
			String st = (String) stringArray.get(i);

			// Get individual 'fields' of the string separated by SEPARATOR
			StringTokenizer star = new StringTokenizer(st, SEPARATOR); // pass in the string to the string tokenizer using delimiter "|"

			Guest guest = new Guest();

			while (star.hasMoreTokens()) {

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

	/**
	 * Saving of guest data to the text file.
	 * 
	 * @param filename
	 *            To specify the name of text file to read.
	 * @param al
	 *            The list of guest data to store into the text file.
	 */
	public static void saveGuest(String filename, List al) throws IOException {

		// To store Guest data
		List als = new ArrayList();

		for (int i = 0; i < al.size(); i++) {
			Guest guest = (Guest) al.get(i);
			StringBuilder st = new StringBuilder();

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
		UtilityDB.write(filename, als);
	}
}

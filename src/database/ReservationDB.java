package database;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import entity.Guest;
import entity.Guest.Identity;
import entity.Reservation;
import entity.Room;

/**
 * DB Class for data access for reservation controller.
 * 
 * @author Toh Ling Li Geraldine
 * @version 1.0
 * @since 2016-03-22
 */

public class ReservationDB {

	/**
	 * Delimiter for data in text file.
	 */

	private static final String SEPARATOR = "|";

	/**
	 * Reading of reservation data in text file.
	 * 
	 * @param filename
	 *            To specify the name of text file to read.
	 * @return arraylist the list of guest data taken from the text file.
	 */
	public ArrayList readReservation(String filename) throws IOException {

		DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");

		// Read String from text file
		ArrayList stringArray = (ArrayList) UtilityDB.read(filename);

		// To store Reservation data
		ArrayList alr = new ArrayList();

		for (int i = 0; i < stringArray.size(); i++) {
			String st = (String) stringArray.get(i);

			// Get individual 'fields' of the string separated by SEPARATOR
			StringTokenizer star = new StringTokenizer(st, SEPARATOR);  // Pass in the string to the string tokenizer using delimiter ","

			String reservationID = star.nextToken().trim();

			// Guest ID
			Guest guest = new Guest();
			String id = star.nextToken().trim();
			Identity ident = guest.new Identity();
			ident.setLic(id);
			ident.setPp(id);
			guest.setIdentity(ident);

			// Room Number
			Room room = new Room();
			room.setRoomNo(star.nextToken().trim());

			int billType = Integer.parseInt(star.nextToken().trim());

			Date checkIn = null;
			try {
				checkIn = (Date) formatter.parse(star.nextToken().trim());
			} catch (ParseException e) {

			}

			Date checkOut = null;
			try {
				checkOut = (Date) formatter.parse(star.nextToken().trim());
			} catch (ParseException e) {

			}

			int numAdult = Integer.parseInt(star.nextToken().trim());
			int numChild = Integer.parseInt(star.nextToken().trim());
			String status = star.nextToken().trim();

			// Create reservation object from file data
			Reservation reserv = new Reservation(reservationID, guest, room, billType, checkIn, checkOut, numAdult,
					numChild, status);

			// Add to reservation list
			alr.add(reserv);
		}
		return alr;
	}

	/**
	 * Saving of reservation data to the text file.
	 * 
	 * @param filename
	 *            To specify the name of text file to read.
	 * @param al
	 *            The list of reservation data to store into the text file.
	 */
	public void saveReservation(String filename, List al) throws IOException {

		DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");

		// To store Reservation data
		List alw = new ArrayList();

		for (int i = 0; i < al.size(); i++) {
			Reservation reserv = (Reservation) al.get(i);
			StringBuilder st = new StringBuilder();

			st.append(reserv.getReservationID().trim());
			st.append(SEPARATOR);

			// Guest ID
			st.append((!reserv.getGuest().getIdentity().getLic().trim().equals("null"))
					? reserv.getGuest().getIdentity().getLic().trim() : reserv.getGuest().getIdentity().getPp().trim());
			st.append(SEPARATOR);

			// Room Number
			st.append(reserv.getRoom().getRoomNo().trim());
			st.append(SEPARATOR);

			st.append(reserv.getBillType());
			st.append(SEPARATOR);
			st.append(formatter.format(reserv.getCheckIn()));
			st.append(SEPARATOR);
			st.append(formatter.format(reserv.getCheckOut()));
			st.append(SEPARATOR);
			st.append(reserv.getNumAdult());
			st.append(SEPARATOR);
			st.append(reserv.getNumChild());
			st.append(SEPARATOR);
			st.append(reserv.getStatus());

			alw.add(st.toString());
		}
		UtilityDB.write(filename, alw);
	}
}
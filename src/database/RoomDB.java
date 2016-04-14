package database;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import entity.Room;

/**
 * DB Class for data access for guest controller.
 * 
 * @author Toh Ling Li Geraldine
 * @version 1.0
 * @since 2016-04-01
 */

public class RoomDB {

	/**
	 * Delimiter for data in text file.
	 */
	public static final String SEPARATOR = "|";

	/**
	 * Reading of room data from text file.
	 * 
	 * @param filename
	 *            To specify the name of text file to read.
	 * @return the list of guest data taken from text file.
	 */
	public static ArrayList readRoom(String filename) throws IOException {

		// Read String from text file
		ArrayList stringArray = (ArrayList) UtilityDB.read(filename);

		// To store Room data
		ArrayList alr = new ArrayList();

		for (int i = 0; i < stringArray.size(); i++) {
			String st = (String) stringArray.get(i);

			// Get individual 'fields' of the string separated by SEPARATOR
			StringTokenizer star = new StringTokenizer(st, SEPARATOR);  // Pass in the string to the string tokenizer using delimiter ","

			String roomNo = star.nextToken().trim();
			String type = star.nextToken().trim();
			double price = (Double.parseDouble(star.nextToken().trim()));
			String status = star.nextToken().trim();
			String[] details = (star.nextToken().trim()).split(", ");

			// Create room object from file data
			Room room = new Room(roomNo, type, price, status, details);

			// Add to room list
			alr.add(room);
		}
		return alr;
	}

	/**
	 * Saving of room data to the text file.
	 * 
	 * @param filename
	 *            To specify the name of the text file to read.
	 * @param al
	 *            The list of room data to store into the text file.
	 */
	public void saveRoom(String filename, List al) throws IOException {

		// To store Reservation data
		List alw = new ArrayList();

		for (int i = 0; i < al.size(); i++) {
			Room room = (Room) al.get(i);
			StringBuilder st = new StringBuilder();

			st.append(room.getRoomNo().trim());
			st.append(SEPARATOR);
			st.append(room.getType().trim());
			st.append(SEPARATOR);
			st.append(room.getPrice());
			st.append(SEPARATOR);
			st.append(room.getStatus());
			st.append(SEPARATOR);
			st.append((Arrays.toString(room.getDetails())).replace('[', ' ').replace(']', ' ').trim());

			alw.add(st.toString());
		}
		UtilityDB.write(filename, alw);
	}
}
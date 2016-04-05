package database;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import entity.Room;

public class RoomDB {

	public static final String SEPARATOR = "|";

	public static ArrayList readRoom(String filename) throws IOException {

		// Read String from text file
		ArrayList stringArray = (ArrayList) UtilityDB.read(filename);
		ArrayList alr = new ArrayList(); // To store Room data

		for (int i = 0; i < stringArray.size(); i++) {
			String st = (String) stringArray.get(i);
			
			// Get individual 'fields' of the string separated by SEPARATOR
			StringTokenizer star = new StringTokenizer(st, SEPARATOR); // Pass in the string to the string tokenizer using delimiter ","

			int roomNo = Integer.parseInt(star.nextToken());
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

	public void saveRoom(String filename, List al) throws IOException {

		List alw = new ArrayList(); // To store Reservation data

		for (int i = 0; i < al.size(); i++) {
			Room room = (Room) al.get(i);
			StringBuilder st = new StringBuilder();
			
			st.append(room.getRoomNo());
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
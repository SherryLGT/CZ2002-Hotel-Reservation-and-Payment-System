package database;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.io.FileInputStream;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.Scanner;

import controller.GuestController;
import controller.RoomController;
import entity.Guest;
import entity.Guest.Identity;
import entity.Menu;
import entity.Reservation;
import entity.Room;
import entity.RoomService;

/**
 * DB Class for data access for the room service controller.
 * 
 * @author Tan Wanyi Cherry
 * @version 1.0
 * @since 2016-04-05
 */

public class RoomServiceDB {
	
	/**
	 * Delimiter for data in text file.
	 */
	public static final String SEPARATOR = "|";
	static DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	Scanner sc = new Scanner(System.in);
	
	/**
	 * Reading of room service data from text file.
	 * 
	 * @param filename
	 *            To specify the name of text file to read.
	 * @return arraylist the list of room service data taken from the text file.
	 */
	public ArrayList readRoomService(String filename) throws IOException {
		
		ArrayList stringArray = (ArrayList) UtilityDB.read(filename);
		ArrayList alr = new ArrayList();

		for (int i = 0; i < stringArray.size(); i++) {
			String st = (String) stringArray.get(i);
			StringTokenizer star = new StringTokenizer(st, SEPARATOR); 

			int roomserviceID = Integer.parseInt(star.nextToken().trim());

			Menu items = new Menu();
			items.setID(Integer.parseInt(star.nextToken().trim()));

			Date date = null;
			try {
				date = formatter.parse(star.nextToken().trim());
			} catch (ParseException e) {
				e.printStackTrace();
			}

			String remarks = star.nextToken().trim();
			String status = star.nextToken().trim();
			
			
			// GUEST PART
			Guest guest = new Guest();
			String id = star.nextToken().trim();
			Identity ident = guest. new Identity();
			ident.setLic(id);
			ident.setPp(id);
			guest.setIdentity(ident);
			
			
			// ROOM PART
			Room room = new Room();
			room.setRoomNo(star.nextToken().trim());
			
			
			// create roomservice object from file data
			RoomService roomservice = new RoomService(roomserviceID, items, date, remarks, status, guest, room);

			// add to room service list
			alr.add(roomservice);
		}
		return alr;
	}

	
	/**
	 * Saving of room service order data to the text file.
	 * 
	 * @param filename
	 *            To specify the name of text file to read.
	 * @param al
	 *            The list of room service order data to store into the text file.
	 */
	public void saveRoomService(String filename, List al) throws IOException {
		List alw = new ArrayList();

		for (int i = 0; i < al.size(); i++) {
			RoomService roomservice = (RoomService) al.get(i);
			StringBuilder st = new StringBuilder();
			st.append(roomservice.getRoomServiceID());
			st.append(SEPARATOR);
			st.append(roomservice.getItems().getID());
			st.append(SEPARATOR);
			st.append(formatter.format(roomservice.getDate()));
			st.append(SEPARATOR);
			st.append(roomservice.getRemarks().trim());
			st.append(SEPARATOR);
			st.append(roomservice.getStatus());
			st.append(SEPARATOR);
			
			if (roomservice.getGuest().getIdentity().getPp().equals("null")) {
				st.append(roomservice.getGuest().getIdentity().getLic());
			} else {
				st.append(roomservice.getGuest().getIdentity().getPp());
			}

			st.append(SEPARATOR);
			st.append(roomservice.getRoom().getRoomNo());
			alw.add(st.toString());
		}
		UtilityDB.write(filename, alw);
	}
}

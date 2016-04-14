package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;

import database.RoomDB;
import entity.Room;
import ui.HRPSApp;

/**
 * Controller class for room.
 * 
 * @author Toh Ling Li Geraldine
 * @version 1.0
 * @since 2016-04-01
 */

public class RoomController {

	/**
	 * For DB access to room text file.
	 */
	private RoomDB roomDB = new RoomDB();

	/**
	 * Name of room text file.
	 */
	private String filename = "room.txt";

	/**
	 * To create a new room with prompts and validation checking.
	 */
	public void createRoom() {

		// Initialize attributes
		Room room = new Room();
		Room checkRoom = new Room();
		int option = 0;
		boolean check = false;
		ArrayList<String> details = new ArrayList<String>();

		Scanner sc = new Scanner(System.in);

		// Room Status
		room.setStatus("Vacant");

		// Room Number
		System.out.print("Room Number: ");
		room.setRoomNo(sc.nextLine());

		// Check for existing room
		checkRoom = searchRoom(room);

		while (checkRoom != null) {
			room = new Room();

			System.out.println("Sorry! Room already exist. Please try again.");

			System.out.print("Room Number: ");
			room.setRoomNo(sc.nextLine());

			checkRoom = searchRoom(room);

		}

		// Room Type
		System.out.print("Room Type: ");
		room.setType(sc.nextLine());

		// Room Price
		do {
			System.out.print("Room Price ($): ");
			try {
				room.setPrice(sc.nextDouble());
				check = true;
			} catch (InputMismatchException e) {
				System.out.println("You have entered an invalid input. Please try again.");
				sc.next();
			}
		} while (!check);

		// Room Details
		System.out.println("Room Details: ");

		do {
			System.out.print("Room Size (sq m): ");
			try {
				details.add(sc.nextInt() + " sq m");
				check = true;
			} catch (InputMismatchException e) {
				System.out.println("You have entered an invalid input. Please try again.");
				sc.next();
			}
		} while (!check);

		do {
			System.out.println("Bed type? (1) Single (2) Double (3) Queen (4) King ");
			try {
				option = sc.nextInt();
				if (option < 1 || option > 4)
					System.out.println("You have not selected option between 1-4. Please try again.");

			} catch (InputMismatchException e) {
				System.out.println("You have entered an invalid input. Please try again.");
				sc.next();
			}
		} while (option < 1 || option > 4);

		switch (option) {
		case 1:
			details.add("single bed");
			break;
		case 2:
			details.add("double bed");
			break;
		case 3:
			details.add("queen bed");
			break;
		case 4:
			details.add("king bed");
			break;
		}

		do {
			System.out.println("City view from the how many floor or higher: ");
			try {
				option = sc.nextInt();
				if (option > 0)
					check = true;
				else
					System.out.println("You have to enter more than 0. Please try again.");
			} catch (InputMismatchException e) {
				System.out.println("You have entered an invalid input. Please try again.");
				sc.next();
			}
		} while (!check && option < 0);

		if (option == 1) {
			details.add("city view from the " + option + "st floor or higher");
		} else if (option == 2) {
			details.add("city view from the " + option + "nd floor or higher");
		} else if (option == 3) {
			details.add("city view from the " + option + "rd floor or higher");
		}
		if (option > 3) {
			details.add("city view from the " + option + "th floor or higher");
		}

		do {
			System.out.println("Wifi enabled? (1) Yes (2) No");
			try {
				option = sc.nextInt();
				if (option < 1 || option > 2)
					System.out.println("You have not selected option between 1-2. Please try again.");

			} catch (InputMismatchException e) {
				System.out.println("You have entered an invalid input. Please try again.");
				sc.next();
			}
		} while (option < 1 || option > 2);

		switch (option) {
		case 1:
			details.add("wifi enabled");
			break;
		case 2:
			details.add("wifi not enabled");
			break;
		}

		do {
			System.out.println("Non-smoking room? (1) Yes (2) No");
			try {
				option = sc.nextInt();
				if (option < 1 || option > 2)
					System.out.println("You have not selected option between 1-2. Please try again.");

			} catch (InputMismatchException e) {
				System.out.println("You have entered an invalid input. Please try again.");
				sc.next();
			}
		} while (option < 1 || option > 2);

		switch (option) {
		case 1:
			details.add("non-smoking room");
			break;
		case 2:
			details.add("smoking room");
			break;
		}

		room.setDetails(details.toArray(new String[5]));

		ArrayList al = getRoom();
		al.add(room);

		try {
			// write Reservation record/s to file.
			roomDB.saveRoom(filename, al);

			System.out.println("\nRoom created successfully.");

		} catch (IOException e) {
			System.out.println("IOException > " + e.getMessage());
		}
	}

	/**
	 * To update changes in room's details.
	 */
	public void updateRoomDetails() {

		// Initialize attributes
		Room room = new Room();
		int option = 0;
		boolean check = false;
		ArrayList<String> details = new ArrayList<String>();

		Scanner sc = new Scanner(System.in);

		// Room Status
		room.setStatus("Vacant");

		// Room Number
		System.out.print("Room Number: ");
		room.setRoomNo(sc.nextLine());

		// Check for existing room
		room = searchRoom(room);

		while (room == null) {
			room = new Room();

			System.out.println("Sorry! Room not found. Please try again.");

			System.out.print("Room Number: ");
			room.setRoomNo(sc.nextLine());

			room = searchRoom(room);

		}

		// Room Type
		System.out.print("Room Type: ");
		room.setType(sc.nextLine());

		// Room Price
		do {
			System.out.print("Room Price ($): ");
			try {
				room.setPrice(sc.nextDouble());
				check = true;
			} catch (InputMismatchException e) {
				System.out.println("You have entered an invalid input. Please try again.");
				sc.next();
			}
		} while (!check);

		// Room Details
		System.out.println("Room Details: ");

		do {
			System.out.print("Room Size (sq m): ");
			try {
				details.add(sc.nextInt() + " sq m");
				check = true;
			} catch (InputMismatchException e) {
				System.out.println("You have entered an invalid input. Please try again.");
				sc.next();
			}
		} while (!check);

		do {
			System.out.println("Bed type? (1) Single (2) Double (3) Queen (4) King ");
			try {
				option = sc.nextInt();
				if (option < 1 || option > 4)
					System.out.println("You have not selected option between 1-4. Please try again.");

			} catch (InputMismatchException e) {
				System.out.println("You have entered an invalid input. Please try again.");
				sc.next();
			}
		} while (option < 1 || option > 4);

		switch (option) {
		case 1:
			details.add("single bed");
			break;
		case 2:
			details.add("double bed");
			break;
		case 3:
			details.add("queen bed");
			break;
		case 4:
			details.add("king bed");
			break;
		}

		do {
			System.out.print("City view from the how many floor or higher: ");
			try {
				option = sc.nextInt();
				if (option > 0)
					check = true;
				else
					System.out.println("You have to enter more than 0. Please try again.");
			} catch (InputMismatchException e) {
				System.out.println("You have entered an invalid input. Please try again.");
				sc.next();
			}
		} while (!check && option < 0);

		if (option == 1) {
			details.add("city view from the " + option + "st floor or higher");
		} else if (option == 2) {
			details.add("city view from the " + option + "nd floor or higher");
		} else if (option == 3) {
			details.add("city view from the " + option + "rd floor or higher");
		}
		if (option > 3) {
			details.add("city view from the " + option + "th floor or higher");
		}

		do {
			System.out.print("Wifi enabled? (1) Yes (2) No");
			try {
				option = sc.nextInt();
				if (option < 1 || option > 2)
					System.out.println("You have not selected option between 1-2. Please try again.");

			} catch (InputMismatchException e) {
				System.out.println("You have entered an invalid input. Please try again.");
				sc.next();
			}
		} while (option < 1 || option > 2);

		switch (option) {
		case 1:
			details.add("wifi enabled");
			break;
		case 2:
			details.add("wifi not enabled");
			break;
		}

		do {
			System.out.print("Non-smoking room? (1) Yes (2) No");
			try {
				option = sc.nextInt();
				if (option < 1 || option > 2)
					System.out.println("You have not selected option between 1-2. Please try again.");

			} catch (InputMismatchException e) {
				System.out.println("You have entered an invalid input. Please try again.");
				sc.next();
			}
		} while (option < 1 || option > 2);

		switch (option) {
		case 1:
			details.add("non-smoking room");
			break;
		case 2:
			details.add("smoking room");
			break;
		}

		room.setDetails(details.toArray(new String[5]));

		ArrayList al = getRoom();

		// Update room arraylist
		for (int i = 0; i < al.size(); i++) {
			Room roomCur = (Room) al.get(i);

			if (room.getRoomNo().equals(roomCur.getRoomNo())) {

				al.set(i, room);
				break;
			}
		}

		try {
			// Write reservation record/s to file.
			roomDB.saveRoom(filename, al);
		} catch (IOException e) {
			System.out.println("IOException > " + e.getMessage());
		}

	}

	/**
	 * To update changes in room's status to maintenance or vacant.
	 */
	public void updateRoomMaintenance() {
		
		// Initialize attributes
		Room room = new Room();
		int option = 0;

		Scanner sc = new Scanner(System.in);

		// Room Number
		System.out.print("Room Number: ");
		room.setRoomNo(sc.nextLine());

		// Check for existing room
		room = searchRoom(room);

		while (room == null) {
			room = new Room();

			System.out.println("Sorry! Room not found. Please try again.");

			System.out.print("Room Number: ");
			room.setRoomNo(sc.nextLine());

			room = searchRoom(room);
		}

		// Room Status
		do {
			System.out.println("(1) Under Maintenance (2) Maintenance Completed: ");
			try {
				option = sc.nextInt();
				if (option < 1 || option > 2)
					System.out.println("You have not selected option between 1-2. Please try again.");
			} catch (InputMismatchException e) {
				System.out.println("You have entered an invalid input. Please try again.");
				sc.next();
			}
		} while (option < 1 || option > 2);

		// Update Room status
		switch (option) {
		case 1:
			updateRoom(room, 2);
			break;
		case 2:
			updateRoom(room, 3);
			break;
		}

		System.out.println("\nRoom updated successfully.");

	}

	/**
	 * To update the room's status.
	 * 
	 * @param room
	 *            Room to update.
	 * @param type
	 *            Room status to set.
	 */
	public void updateRoom(Room room, int type) {
		
		// Initialize attributes
		ArrayList al = getRoom();

		// Set Room status
		for (int i = 0; i < al.size(); i++) {
			Room roomCur = (Room) al.get(i);

			if (room.getRoomNo().equals(roomCur.getRoomNo())) {
				switch (type) {
				case 1:
					roomCur.setStatus("Occupied");
					break;
				case 2:
					roomCur.setStatus("Under Maintenance");
					break;
				case 3:
					roomCur.setStatus("Vacant");
					break;
				case 4:
					roomCur.setStatus("Reserved");
					break;
				}

				al.set(i, roomCur);
				break;
			}
		}

		try {
			// Write reservation record/s to file.
			roomDB.saveRoom(filename, al);
		} catch (IOException e) {
			System.out.println("IOException > " + e.getMessage());
		}
	}

	/**
	 * Retrieval of existing room's details by room number.
	 * 
	 * @param room
	 *            Room to check.
	 * @return room's details.
	 */
	public Room searchRoom(Room room) {
		
		// Initialize attributes
		ArrayList al = getRoom();

		// Check for existing room
		for (int i = 0; i < al.size(); i++) {
			Room roomCur = (Room) al.get(i);

			if (roomCur.getRoomNo().equals(room.getRoomNo())) {
				return roomCur;
			}
		}

		return null;
	}

	/**
	 * Display all rooms with status vacant.
	 */
	public void printRooms() {
		
		// Initialize attributes
		ArrayList al = getRoom();

		System.out.println("\nRooms Avaliable: ");
		HRPSApp.line("-", 139);
		System.out.format("%1s %4s %18s %16s %52s %43s %n", "|", "NO.", "TYPE", "PRICE($)", "DETAILS", "|");
		HRPSApp.line("-", 139);

		// Display vacant room
		for (int i = 0; i < al.size(); i++) {
			Room room = (Room) al.get(i);

			if (room.getStatus().equals("Vacant")) {

				System.out.format("%1s %2s %22s %9s %95s %2s %n", "|", room.getRoomNo(), room.getType(),
						room.getPrice(),
						(Arrays.toString(room.getDetails())).replace('[', ' ').replace(']', ' ').trim(), "|");

			}
		}

		HRPSApp.line("-", 139);

	}

	/**
	 * Display all rooms by different room status.
	 */
	public void printRoomStatus() {
		
		// Initialize attributes
		ArrayList al = getRoom();
		String[] status = { "Vacant", "Reserved", "Occupied", "Under Maintenance" };
		int count = 0;

		// Display different room status
		for (int i = 0; i < status.length; i++) {
			System.out.println("\n" + status[i] + " :");
			System.out.print("\tRooms :");

			count = 0;

			// Display room numbers
			for (int j = 0; j < al.size(); j++) {
				Room room = (Room) al.get(j);
				if (room.getStatus().equals(status[i])) {
					count++;

					if (count > 1)
						System.out.print(", " + room.getRoomNo());
					else
						System.out.print(room.getRoomNo());
				}
			}

			System.out.println("");
		}

		System.out.println("");
	}

	/**
	 * Display all rooms by different room type.
	 */
	public void printRoomOccupancy() {
		// Initialize attributes
		ArrayList al = getRoom();
		ArrayList<String> type = new ArrayList<String>();
		ArrayList<Integer> slotLeft = new ArrayList<Integer>();
		ArrayList<Integer> slot = new ArrayList<Integer>();
		boolean check;
		int count;

		// Get different room type and , total and vacant room count
		for (int i = 0; i < al.size(); i++) {
			Room room = (Room) al.get(i);
			check = false;

			for (int j = 0; j < type.size(); j++) {
				if (type.get(j).equals(room.getType())) {
					slot.set(j, slot.get(j) + 1);

					if (room.getStatus().equals("Vacant"))
						slotLeft.set(j, slotLeft.get(j) + 1);

					check = true;
				}
			}

			if (!check) {
				type.add(room.getType());
				slot.add(1);

				if (room.getStatus().equals("Vacant"))
					slotLeft.add(1);
				else
					slotLeft.add(0);
			}
		}
		
		// Display different room type and it's room count information
		for (int i = 0; i < type.size(); i++) {
			System.out.println("\n" + type.get(i) + ":");
			System.out.println("\tNumbers: " + slotLeft.get(i) + " out of " + slot.get(i));

			count = 0;

			if (slotLeft.get(i) == 0) {
				System.out.print("\tNo rooms avaliable.");
			} else {
				// Display room numbers
				for (int j = 0; j < al.size(); j++) {
					Room room = (Room) al.get(j);
					if (room.getType().equals(type.get(i)) && room.getStatus().equals("Vacant")) {
						if (count > 0) {
							System.out.print(", " + room.getRoomNo());
							count++;
						} else {
							System.out.print("\tRooms: " + room.getRoomNo());
							count = 1;
						}
					}
				}
			}

			System.out.println();
		}

		System.out.println();
	}

	/**
	 * Retrieval of rooms details.
	 * 
	 * @return arraylist of all room sorted in ascending order by room number.
	 */
	public ArrayList getRoom() {
		
		// Initialize attributes
		ArrayList al = null;
		
		try {
			// Read file containing reservation records.
			al = roomDB.readRoom(filename);

			al.sort(new Comparator<Room>() {

				public int compare(Room room1, Room room2) {
					return Integer.parseInt(room1.getRoomNo()) > Integer.parseInt(room2.getRoomNo()) ? +1
							: Integer.parseInt(room1.getRoomNo()) < Integer.parseInt(room2.getRoomNo()) ? -1 : 0;
				}
			});

		} catch (IOException e) {
			System.out.println("IOException > " + e.getMessage());
		}

		return al;
	}
}
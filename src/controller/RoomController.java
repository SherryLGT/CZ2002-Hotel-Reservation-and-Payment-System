package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import database.RoomDB;
import entity.Room;
import ui.HRPSApp;

public class RoomController {

	private RoomDB roomDB = new RoomDB();
	private String filename = "room.txt";

	public void updateRoomMaintenance() {
		Room room = new Room();
		int option = 0;

		Scanner sc = new Scanner(System.in);

		System.out.print("Room Number: ");
		room.setRoomNo(sc.nextInt());
		sc.nextLine();

		room = searchRoom(room);

		while (room == null) {
			room = new Room();

			System.out.println("Sorry! Room not found. Please try again.");

			System.out.print("Room Number: ");
			room.setRoomNo(sc.nextInt());
			sc.nextLine();

			room = searchRoom(room);
		}

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

	public void updateRoom(Room room, int type) {
		ArrayList al = getRoom();

		for (int i = 0; i < al.size(); i++) {
			Room roomCur = (Room) al.get(i);

			if (room.getRoomNo() == roomCur.getRoomNo()) {
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

	public Room searchRoom(Room room) {
		ArrayList al = getRoom();

		for (int i = 0; i < al.size(); i++) {
			Room roomCur = (Room) al.get(i);

			if (roomCur.getRoomNo() == room.getRoomNo()) {
				return roomCur;
			}
		}

		return null;
	}

	public void printRooms() {
		ArrayList al = getRoom();

		String roomFloor = "0";
		String roomNo = "0";

		System.out.println("\nRooms Avaliable: ");
		HRPSApp.line("-", 139);
		System.out.format("%1s %4s %18s %16s %52s %43s %n", "|", "NO.", "TYPE", "PRICE($)", "DETAILS", "|");
		HRPSApp.line("-", 139);

		for (int i = 0; i < al.size(); i++) {
			Room room = (Room) al.get(i);

			if (room.getStatus().equals("Vacant")) {

				roomFloor = "0";
				roomNo = "0";

				if (room.getRoomNo() / 100 > 9)
					roomFloor = Integer.toString(room.getRoomNo() / 100);
				else
					roomFloor += Integer.toString(room.getRoomNo() / 100);

				if (room.getRoomNo() / 100 > 9)
					roomNo = Integer.toString(room.getRoomNo() % 100);
				else
					roomNo += Integer.toString(room.getRoomNo() % 100);

				System.out.format("%1s %2s %22s %9s %95s %2s %n", "|", roomFloor + "-" + roomNo, room.getType(),
						room.getPrice(),
						(Arrays.toString(room.getDetails())).replace('[', ' ').replace(']', ' ').trim(), "|");

			}
		}

		HRPSApp.line("-", 139);

	}

	public void printRoomStatus() {
		ArrayList al = getRoom();
		String[] status = { "Vacant", "Reserved", "Occupied", "Under Maintenance" };
		String roomFloor = "0";
		String roomNo = "0";
		int count = 0;

		for (int i = 0; i < status.length; i++) {
			System.out.println("\n" + status[i] + " :");
			System.out.print("\tRooms :");

			count = 0;

			for (int j = 0; j < al.size(); j++) {
				Room room = (Room) al.get(j);
				if (room.getStatus().equals(status[i])) {
					count++;

					roomFloor = "0";
					roomNo = "0";

					if (room.getRoomNo() / 100 > 9)
						roomFloor = Integer.toString(room.getRoomNo() / 100);
					else
						roomFloor += Integer.toString(room.getRoomNo() / 100);

					if (room.getRoomNo() / 100 > 9)
						roomNo = Integer.toString(room.getRoomNo() % 100);
					else
						roomNo += Integer.toString(room.getRoomNo() % 100);

					if (count > 1)
						System.out.print(", " + roomFloor + "-" + roomNo);
					else
						System.out.print(roomFloor + "-" + roomNo);
				}
			}

			System.out.println("");
		}

		System.out.println("");
	}

	public void printRoomOccupancy() {
		ArrayList al = getRoom();
		ArrayList<String> type = new ArrayList<String>();
		ArrayList<Integer> slotLeft = new ArrayList<Integer>();
		ArrayList<Integer> slot = new ArrayList<Integer>();
		boolean check;
		int count;

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
		for (int i = 0; i < type.size(); i++) {
			System.out.println("\n" + type.get(i) + ":");
			System.out.println("\tNumbers: " + slotLeft.get(i) + " out of " + slot.get(i));

			count = 0;

			if (slotLeft.get(i) == 0) {
				System.out.print("\tNo rooms avaliable.");
			} else {
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

	public ArrayList getRoom() {
		ArrayList al = null;
		try {
			// Read file containing reservation records.
			al = roomDB.readRoom(filename);

			al.sort(new Comparator<Room>() {

				public int compare(Room room1, Room room2) {
					return room1.getRoomNo() > room2.getRoomNo() ? +1 : room1.getRoomNo() < room2.getRoomNo() ? -1 : 0;
				}
			});

		} catch (IOException e) {
			System.out.println("IOException > " + e.getMessage());
		}

		return al;
	}
}
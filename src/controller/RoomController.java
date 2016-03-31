package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import database.RoomDB;
import entity.Reservation;
import entity.Room;

public class RoomController {

	private RoomDB roomDB = new RoomDB();
	private String filename = "room.txt";

	public void updateRoomMaintenance() {
		Room room = new Room();
		int option = 0;

		Scanner sc = new Scanner(System.in);

		System.out.print("Room Number: ");
		room.setRoomNo(sc.nextLine());

		room = searchRoom(room.getRoomNo());

		while (room == null) {
			room = new Room();

			System.out.println("Sorry! Room not found. Please try again.");

			System.out.print("Room Number: ");
			room.setRoomNo(sc.nextLine());

			room = searchRoom(room.getRoomNo());
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
			// write Reservation record/s to file.
			roomDB.saveRoom(filename, al);
		} catch (IOException e) {
			System.out.println("IOException > " + e.getMessage());
		}
	}

	public ArrayList getRoom() {
		ArrayList al = null;
		try {
			// read file containing Reservation records.
			al = roomDB.readRoom(filename);

		} catch (IOException e) {
			System.out.println("IOException > " + e.getMessage());
		}
		return al;
	}

	public Room searchRoom(String roomNo) {
		ArrayList al = getRoom();

		for (int i = 0; i < al.size(); i++) {
			Room room = (Room) al.get(i);

			if (room.getRoomNo().equals(roomNo)) {
				return room;
			}
		}

		return null;
	}

	public void printRooms() {
		ArrayList al = getRoom();

		System.out.println("\nRooms Avaliable: ");
		System.out.println("Room No. \tRoom Type  \t\tRoom Price \tRoom Details");

		for (int i = 0; i < al.size(); i++) {
			Room room = (Room) al.get(i);

			if (room.getStatus().equals("Vacant")) {
				System.out.print(room.getRoomNo() + "\t\t");

				if (room.getType().length() < 14)
					System.out.print(room.getType() + "  \t\t");
				else
					System.out.print(room.getType() + "  \t");

				System.out.print("$" + room.getPrice() + "\t\t");
				System.out.print((Arrays.toString(room.getDetails())).replace('[', ' ').replace(']', ' ').trim());
				System.out.println("\n");
			}
		}

	}

	public void printRoomStatus() {
		ArrayList al = getRoom();
		int count = 0;

		System.out.println("\nVacant :");
		System.out.print("\tRooms :");
		for (int i = 0; i < al.size(); i++) {
			Room room = (Room) al.get(i);

			if (room.getStatus().equals("Vacant")) {
				count++;

				if (count > 1)
					System.out.print(", " + room.getRoomNo());
				else
					System.out.print(room.getRoomNo());
			}
		}

		count = 0;

		System.out.println("\n\nOccupied :");
		System.out.print("\tRooms :");
		for (int i = 0; i < al.size(); i++) {
			Room room = (Room) al.get(i);

			if (room.getStatus().equals("Occupied")) {
				count++;

				if (count > 1)
					System.out.print(", " + room.getRoomNo());
				else
					System.out.print(room.getRoomNo());
			}
		}

		System.out.println("\n");
	}

	public void printRoomOccupancy() {
		ArrayList al = getRoom();

		String current = "", tempType = "";
		int count = 0, tempCount = 0;

		for (int i = 0; i < al.size(); i++) {
			Room room = (Room) al.get(i);

			if (tempType.equals(room.getType())) {
				tempCount++;
			} else {

				if (tempCount > 0 && count == 0)
					System.out.print("\tNo rooms avaliable.");

				if (tempCount > 0)
					System.out.print("\n\tNumbers: " + count + " out of " + tempCount);

				System.out.println("\n" + room.getType() + ":");

				tempType = room.getType();
				tempCount = 1;
				count = 0;
			}

			if (room.getStatus().equals("Vacant")) {

				if (current.equals(room.getType())) {
					System.out.print(", " + room.getRoomNo());
					count++;
				} else {
					current = room.getType();
					System.out.print("\tRooms: " + room.getRoomNo());
					count = 1;
				}
			}
		}

		System.out.println("\n\tNumbers: " + count + " out of " + tempCount + "\n");
	}
}

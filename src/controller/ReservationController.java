package controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

import database.ReservationDB;
import entity.Guest;
import entity.Reservation;
import entity.Room;
import ui.HRPSApp;

/**
 * Controller class for reservation.
 * 
 * @author Toh Ling Li Geraldine
 * @version 1.0
 * @since 2016-03-22
 */

public class ReservationController {

	/**
	 * For DB access to room text file.
	 */
	private ReservationDB reservationDB = new ReservationDB();

	/**
	 * Name of room text file.
	 */
	private String filename = "reservation.txt";

	/**
	 * Standard date format.
	 */
	DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");

	/**
	 * To create a new reservation with prompts and validation checking.
	 */
	public void createReservation() {

		// Initialize attributes
		String reservationID;
		Guest guest = new Guest();
		Room room = new Room();
		int billType = 0;
		Date checkIn = null;
		Date checkOut = null;
		int numAdult = 0;
		int numChild = 0;
		boolean check = false;
		RoomController roomControl = new RoomController();
		GuestController guestControl = new GuestController();
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

		Scanner sc = new Scanner(System.in);

		System.out.println("\n-------- Make Reservation --------\n");

		// Reservation ID
		Date date = new Date();
		reservationID = dateFormat.format(date).trim();

		// Reservation Guest ID
		guest = guestControl.getGuestDetails();

		// Display rooms available
		roomControl.printRooms();

		// Reservation Room Number
		System.out.print("Room Number: ");
		room.setRoomNo(sc.nextLine());

		// Check for existing room
		room = roomControl.searchRoom(room);

		while (room == null || !room.getStatus().equals("Vacant")) {

			if (room == null)
				System.out.println("Sorry! Room not found. Please try again.");
			else if (!room.getStatus().equals("Vacant"))
				System.out.println("Sorry! Room not not avaliable. Please try other rooms.");

			room = new Room();

			System.out.print("Room Number: ");
			room.setRoomNo(sc.nextLine());

			room = roomControl.searchRoom(room);
		}

		// Reservation Bill Type
		do {
			System.out.println("Billing type? (1) Cash (2) Credit Card ");
			try {
				billType = sc.nextInt();
				if (billType < 1 || billType > 2)
					System.out.println("You have not selected option between 1-2. Please try again.");
			} catch (InputMismatchException e) {
				System.out.println("You have entered an invalid input. Please try again.");
				sc.next();
			}
		} while (billType < 1 || billType > 2);

		sc.nextLine();

		// Reservation Check In Date
		do {
			System.out.print("Check In Date (MM/DD/YYYY):");

			try {
				checkIn = (Date) formatter.parse(sc.nextLine());
				Date date1 = new Date();
				date1 = (Date) formatter.parse(formatter.format(date1));

				if (checkIn.equals(date1) || checkIn.after(date1))
					check = true;
				else {
					check = false;
					System.out.println("Sorry! Date must be today or later. Please try again.");
				}

			} catch (ParseException e) {
				System.out.println("Sorry! Wrong format. Please try again.");
			}
		} while (!check);

		check = false;

		// Reservation Check Out Date
		do {
			System.out.print("Check Out Date (MM/DD/YYYY):");

			try {
				checkOut = (Date) formatter.parse(sc.nextLine());

				if (checkIn.before(checkOut))
					check = true;
				else {
					check = false;
					System.out.println("Sorry! Check out date must be after check in date. Please try again.");
				}

			} catch (ParseException e) {
				System.out.println("Sorry! Wrong format. Please try again.");
			}
		} while (!check);

		check = false;

		// Reservation Number of Adults
		do {
			System.out.print("Number of Adults: ");
			try {
				numAdult = sc.nextInt();
				check = true;
			} catch (InputMismatchException e) {
				System.out.println("You have entered an invalid input. Please try again.");
				sc.next();
			}
		} while (!check);

		// Reservation Number of Children
		do {
			System.out.print("Number of Children: ");
			try {
				numChild = sc.nextInt();
				check = true;
			} catch (InputMismatchException e) {
				System.out.println("You have entered an invalid input. Please try again.");
				sc.next();
			}
		} while (!check);

		Reservation reservation = new Reservation(reservationID, guest, room, billType, checkIn, checkOut, numAdult,
				numChild, "Waitlist");

		ArrayList al = getReservation();
		al.add(reservation);

		try {
			// write Reservation record/s to file.
			reservationDB.saveReservation(filename, al);

			roomControl.updateRoom(room, 4);

			// Display acknowledge receipt
			HRPSApp.header("RECEIPT", "*", 50);
			System.out.format("%1s %55s %n", "*", "*");
			System.out.format("%1s %28s %20s %5s %n", "*", "Name:", guest.getName(), "*");
			System.out.format("%1s %28s %20s %5s %n", "*", "Identity Number:",
					(!guest.getIdentity().getLic().trim().equals("null")) ? guest.getIdentity().getLic()
							: guest.getIdentity().getPp(),
					"*");
			System.out.format("%1s %28s %20s %5s %n", "*", "Room No:", room.getRoomNo(), "*");
			System.out.format("%1s %28s %20s %5s %n", "*", "Room Type:", room.getType(), "*");
			System.out.format("%1s %28s %20s %5s %n", "*", "Check in date (MM/DD/YYYY):",
					formatter.format(reservation.getCheckIn()), "*");
			System.out.format("%1s %28s %20s %5s %n", "*", "Check out date (MM/DD/YYYY):",
					formatter.format(reservation.getCheckOut()), "*");
			HRPSApp.line("*", 57);

		} catch (IOException e) {
			System.out.println("IOException > " + e.getMessage());
		}

	}

	/**
	 * To update reservation status to check in
	 */
	public void checkIn() {

		// Initialize attributes
		RoomController roomControl = new RoomController();
		GuestController guestControl = new GuestController();
		Room room = new Room();
		Guest guest = new Guest();
		boolean check = false;

		Scanner sc = new Scanner(System.in);

		System.out.println("\n-------- Check In --------\n");

		// Reservation Guest ID
		guest = guestControl.getGuestDetails();

		ArrayList al = getReservation();

		// Today's date
		Date date = new Date();

		// Reservation Room Number
		System.out.print("Room Number: ");
		room.setRoomNo(sc.nextLine());

		// Check for existing room
		room = roomControl.searchRoom(room);

		while (room == null || !room.getStatus().equals("Reserved")) {

			if (room == null)
				System.out.println("Sorry! Room not found. Please try again.");
			else if (!room.getStatus().equals("Vacant"))
				System.out.println("Sorry! Room not not avaliable. Please try other rooms.");

			room = new Room();

			System.out.print("Room Number: ");
			room.setRoomNo(sc.nextLine());

			room = roomControl.searchRoom(room);
		}

		// Check for existing reservation
		for (int i = 0; i < al.size(); i++) {
			Reservation reserv = (Reservation) al.get(i);

			if ((guest.getIdentity().getLic().equals(reserv.getGuest().getIdentity().getLic())
					|| guest.getIdentity().getPp().equals(reserv.getGuest().getIdentity().getPp()))
					&& (formatter.format(date)).equals(formatter.format(reserv.getCheckIn()))
					&& reserv.getStatus().equals("Waitlist") && room.getRoomNo().equals(reserv.getRoom().getRoomNo())) {
				updateReservation(reserv, 1);
				check = true;
			}
		}

		if (check) {

			// Update room status to reserved
			roomControl.updateRoom(room, 1);

			System.out.println("\nGuest successfully checked in.");

		} else {
			System.out.println("\nSorry! No reserevation found. Guest cannot be checked in.");
		}
	}

	/**
	 * Update reservation status to expired when time pass 2pm
	 */
	public void kickOut() {
		// Initialize attributes
		RoomController roomControl = new RoomController();
		Room room = null;

		ArrayList al = getReservation();

		// Today's date
		Date date = new Date();

		// Check for existing reservation that needs to be expired
		for (int i = 0; i < al.size(); i++) {
			Reservation reserv = (Reservation) al.get(i);
			if ((formatter.format(date)).equals(formatter.format(reserv.getCheckIn()))
					&& reserv.getStatus().equals("Waitlist")) {

				if (Integer.parseInt(reserv.getReservationID().substring(8)) < 140000) {
					updateReservation(reserv, 2);
					room = reserv.getRoom();

					// Update room status to vacant
					if (room != null)
						roomControl.updateRoom(room, 3);

				}
			} else
				try {
					if (formatter.parse((formatter.format(date)))
							.after(formatter.parse(formatter.format(reserv.getCheckIn())))
							&& reserv.getStatus().equals("Waitlist")) {
						updateReservation(reserv, 2);
						room = reserv.getRoom();

						// Update room status to vacant
						if (room != null)
							roomControl.updateRoom(room, 3);

					}
				} catch (ParseException e) {

				}
		}
	}

	/**
	 * Delete reservation
	 */
	public void deleteReservation() {

		// Initialize attributes
		RoomController roomControl = new RoomController();
		GuestController guestControl = new GuestController();
		Room room = new Room();
		Guest guest = new Guest();
		Boolean check = false;
		Date date = null;
		int option = 0;

		Scanner sc = new Scanner(System.in);

		System.out.println("\n-------- Delete Reservation ---------\n");

		// Reservation Guest ID
		guest = guestControl.getGuestDetails();

		ArrayList al = getReservation();

		// Reservation Check In Date
		do {
			System.out.print("Check In Date (MM/DD/YYYY): ");

			try {
				date = (Date) formatter.parse(sc.nextLine());
				check = true;

			} catch (ParseException e) {
				System.out.println("Sorry! Wrong format. Please try again.");
			}
		} while (!check);

		check = false;

		// Reservation Room Number
		System.out.print("Room Number: ");
		room.setRoomNo(sc.nextLine());

		// Check for existing room
		room = roomControl.searchRoom(room);

		while (room == null || !room.getStatus().equals("Reserved")) {

			if (room == null)
				System.out.println("Sorry! Room not found. Please try again.");
			else if (!room.getStatus().equals("Vacant"))
				System.out.println("Sorry! Room not not avaliable. Please try other rooms.");

			room = new Room();

			System.out.print("Room Number: ");
			room.setRoomNo(sc.nextLine());

			room = roomControl.searchRoom(room);
		}

		// Check for existing reservation
		for (int i = 0; i < al.size(); i++) {
			Reservation reserv = (Reservation) al.get(i);

			if ((guestControl.searchGuest(guest) != null) && date.equals(reserv.getCheckIn())
					&& reserv.getStatus().equals("Waitlist") && room.getRoomNo().equals(reserv.getRoom().getRoomNo())) {
				room = reserv.getRoom();
				al.remove(i);
				check = true;
			}
		}

		// Confirmation to delete
		do {
			System.out.println();
			System.out.print("Remove Item Confirmation - (1) Yes   (2) No: ");

			try {
				option = sc.nextInt();

			} catch (InputMismatchException e) {
				System.out.println("You have entered an invalid input. Please try again.");
			}
		} while (option < 1 || option > 2);

		if (check) {
			if (option == 1) {
				try {
					// write Reservation record/s to file.
					reservationDB.saveReservation(filename, al);

					// Update room status to vacant
					roomControl.updateRoom(room, 3);

					System.out.println("\nReservation deleted successfully.");

				} catch (IOException e) {
					System.out.println("IOException > " + e.getMessage());
				}
			}
		} else {
			System.out.println("\nSorry! No reservation found.");
		}
	}

	/**
	 * To update the reservation's status.
	 * 
	 * @param reservation
	 *            Reservation to update.
	 * @param type
	 *            Reservation status to set.
	 */
	public void updateReservation(Reservation reservation, int type) {
		ArrayList al = getReservation();

		for (int i = 0; i < al.size(); i++) {
			Reservation rs = (Reservation) al.get(i);

			if (reservation.getReservationID().equals(rs.getReservationID())) {
				switch (type) {
				case 1:
					rs.setStatus("Checked-In");
					break;
				case 2:
					rs.setStatus("Expired");
					break;
				case 3:
					rs.setStatus("Checked-Out");
					break;
				}
			}
			al.set(i, rs);

		}

		try {
			// Write reservation record/s to file.
			reservationDB.saveReservation(filename, al);
		} catch (IOException e) {
			System.out.println("IOException > " + e.getMessage());
		}
	}

	/**
	 * To search for a reservation's detail by room.
	 * 
	 * @param room
	 *            Room to search
	 * @return reservation's detail
	 */
	public Reservation searchReservationByRoom(Room room) {
		ArrayList reservations = getReservation();
		Date date = new Date();

		for (int i = 0; i < reservations.size(); i++) {
			Reservation reserv = (Reservation) reservations.get(i);

			if (reserv.getRoom().getRoomNo().equals(room.getRoomNo()) && reserv.getStatus().equals("Checked-In")) {
				return reserv;
			}
		}

		return null;
	}

	/**
	 * Display all reservations by different reservation status.
	 */
	public void printReservation() {
		ArrayList al = getReservation();
		String[] status = { "Checked-In", "Checked-Out", "Waitlist" };
		boolean check = false;

		System.out.println("Reservation Details: \n");
		for (int i = 0; i < status.length; i++) {
			HRPSApp.line("=", 114);
			System.out.format("%1s %57s %54s%n", "|", status[i].toUpperCase().replace("-", " "), "|");
			HRPSApp.line("=", 114);
			System.out.format("%1s %4s %20s %16s %20s %20s %20s %2s %n", "|", "ROOM NO.", "ROOM TYPE", "GUEST ID",
					"GUEST NAME", "DATE CHECKED IN", "DATE CHECKED OUT", "|");
			System.out.format("%1s %4s %20s %16s %20s %22s %22s %2s %n", "|", "", "", "", "", "(MM/DD/YYYY)",
					"(MM/DD/YYYY)", "|");
			HRPSApp.line("-", 114);

			check = false;

			for (int j = 0; j < al.size(); j++) {
				Reservation reser = (Reservation) al.get(j);

				if (reser.getStatus().equals(status[i])) {
					RoomController roomControl = new RoomController();
					GuestController guestControl = new GuestController();

					Room room = roomControl.searchRoom(reser.getRoom());
					Guest guest = null;

					ArrayList alGuest = guestControl.getGuest();

					for (int k = 0; k < alGuest.size(); k++) {
						Guest searchGuest = (Guest) alGuest.get(k);

						if (reser.getGuest().getIdentity().getLic().equals(searchGuest.getIdentity().getLic())
								|| reser.getGuest().getIdentity().getPp().equals(searchGuest.getIdentity().getPp())) {
							guest = searchGuest;
						}
					}

					System.out.format("%1s %6s %25s %10s %25s %15s %22s %3s %n", "|", room.getRoomNo(), room.getType(),
							(!guest.getIdentity().getLic().trim().equals("null")) ? guest.getIdentity().getLic()
									: guest.getIdentity().getPp(),
							guest.getName(), formatter.format(reser.getCheckIn()),
							formatter.format(reser.getCheckOut()), "|");

					check = true;

				}
			}

			if (!check) {
				System.out.format("%1s %60s %51s %n", "|", "No record found.", "|");
			}

			HRPSApp.line("-", 114);
			System.out.println();
		}
	}

	/**
	 * Retrieval of reservations details.
	 * 
	 * @return arraylist of all reservation sorted in ascending order by check in date.
	 */
	public ArrayList getReservation() {
		ArrayList al = null;
		try {
			// read file containing Reservation records.
			al = reservationDB.readReservation(filename);

			al.sort(new Comparator<Reservation>() {

				public int compare(Reservation reserv1, Reservation reserv2) {
					return reserv1.getCheckIn().after(reserv2.getCheckIn()) ? +1
							: reserv1.getCheckIn().before(reserv2.getCheckIn()) ? -1 : 0;
				}
			});

		} catch (IOException e) {
			System.out.println("IOException > " + e.getMessage());
		}
		return al;
	}
}
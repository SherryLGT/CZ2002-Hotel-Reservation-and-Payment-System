package controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import database.ReservationDB;
import entity.Guest;
import entity.Reservation;
import entity.Room;

public class ReservationController {
	private ReservationDB reservationDB = new ReservationDB();
	private String filename = "reservation.txt";
	private int position = 0;
	DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");

	public void createReservation() {
		String reservationID;
		Guest guest = new Guest();
		Room room = new Room();
		int billType;
		Date checkIn = null;
		Date checkOut = null;
		int numAdult;
		int numChild;

		RoomController roomControl = new RoomController();
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

		Scanner sc = new Scanner(System.in);

		System.out.println("\n~~~ Make Reservation ~~~\n");

		Date date = new Date();
		reservationID = dateFormat.format(date).trim();
		System.out.println();

		System.out.println("GuestID/Name");
		guest.setName(sc.nextLine());

		roomControl.printRooms();
		System.out.println("RoomID/Type");
		room.setRoomNo(sc.nextInt());
		System.out.println("Billing type? (1) Cash (2) Credit Card");
		billType = sc.nextInt();
		sc.nextLine();
		System.out.println("Check In Date (MM/dd/yyyy):");
		try {
			checkIn = (Date) formatter.parse(sc.nextLine());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println("Check Out Date (MM/dd/yyyy):");
		try {
			checkOut = (Date) formatter.parse(sc.nextLine());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println("Number of Adults:");
		numAdult = sc.nextInt();
		System.out.println("Number of Children: ");
		numChild = sc.nextInt();

		Reservation reservation = new Reservation(reservationID, guest, room, billType, checkIn, checkOut, numAdult,
				numChild, "Waitlist");

		ArrayList al = getReservation();
		al.add(reservation);

		try {
			// write Reservation record/s to file.
			reservationDB.saveReservation(filename, al);

			System.out.println("Resevation Details");

		} catch (IOException e) {
			System.out.println("IOException > " + e.getMessage());
		}

	}

	public void checkIn() {
		String guest;
		Boolean check = false;

		Scanner sc = new Scanner(System.in);

		System.out.println("\n~~~ Check In ~~~\n");

		System.out.println("GuestID/Name");
		guest = sc.nextLine();

		ArrayList al = getReservation();

		Date date = new Date();

		for (int i = 0; i < al.size(); i++) {
			Reservation reserv = (Reservation) al.get(i);

			if (guest.equals(reserv.getGuest().getName())
					&& (formatter.format(date)).equals(formatter.format(reserv.getCheckIn()))
					&& reserv.getStatus().equals("Waitlist")) {
				reserv = updateReservation(reserv, 1);
				al.set(i, reserv);
				check = true;
			}
		}

		if (check) {
			try {
				// write Reservation record/s to file.
				reservationDB.saveReservation(filename, al);

				System.out.println("Checked In!");

			} catch (IOException e) {
				System.out.println("IOException > " + e.getMessage());
			}
		} else {
			System.out.println("Error In Checked In!");
		}
	}

	public void kickOut() {
		ArrayList al = getReservation();
		Date date = new Date();

		for (int i = 0; i < al.size(); i++) {
			Reservation reserv = (Reservation) al.get(i);
			if ((formatter.format(date)).equals(formatter.format(reserv.getCheckIn())) && reserv.getStatus().equals("Waitlist")) {
				reserv = updateReservation(reserv, 2);
				al.set(i, reserv);
			}
		}

		try {
			// write Reservation record/s to file.
			reservationDB.saveReservation(filename, al);
		} catch (IOException e) {
			System.out.println("IOException > " + e.getMessage());
		}
	}

	public void deleteReservation() {
		String guest;
		Boolean check = false;
		Date date = null;

		Scanner sc = new Scanner(System.in);

		System.out.println("\n~~~ Delete Reservation ~~~\n");

		System.out.println("GuestID/Name");
		guest = sc.nextLine();

		ArrayList al = getReservation();

		System.out.println("Check In Date (MM/dd/yyyy):");
		try {
			date = (Date) formatter.parse(sc.nextLine());
		} catch (ParseException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < al.size(); i++) {
			Reservation reserv = (Reservation) al.get(i);

			if (guest.equals(reserv.getGuest().getName()) && date.equals(reserv.getCheckIn())
					&& reserv.getStatus().equals("Waitlist")) {
				reserv = updateReservation(reserv, 1);
				al.remove(i);
				check = true;
			}
		}

		if (check) {
			try {
				// write Reservation record/s to file.
				reservationDB.saveReservation(filename, al);

				System.out.println("Delete!");

			} catch (IOException e) {
				System.out.println("IOException > " + e.getMessage());
			}
		} else {
			System.out.println("Error In Delete!");
		}
	}

	public Reservation updateReservation(Reservation reservation, int type) {
		switch (type) {
		case 1:
			reservation.setStatus("Checked-In");
			break;
		case 2:
			reservation.setStatus("Expired");
			break;
		case 3:
			reservation.setStatus("Checked-Out");
			break;
		}

		return reservation;
	}

	public ArrayList getReservation() {
		ArrayList al = null;
		try {
			// read file containing Reservation records.
			al = reservationDB.readReservation(filename);

		} catch (IOException e) {
			System.out.println("IOException > " + e.getMessage());
		}
		return al;
	}
	
	public Reservation searchReservation(String reservationID) {
		ArrayList al = getReservation();
		
		for (int i = 0; i < al.size(); i++) {
			Reservation reserv = (Reservation) al.get(i);

			if (reserv.getReservationID().equals(reservationID)) {
				return reserv;
			}
		}
		
		return null;
	}

}

package controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import database.ReservationDB;
import model.Guest;
import model.Reservation;
import model.Room;

public class ReservationController {
	private ReservationDB reservationDB = new ReservationDB();
	private String filename = "reservation.txt";

	public void createReservation() {
		String reservationID;
		Guest guest = new Guest();
		Room room = new Room();
		int billType;
		Date checkIn = null;
		Date checkOut = null;
		int numAdult;
		int numChild;
		DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

		Scanner sc = new Scanner(System.in);

		System.out.println("Make Reservation");

		Date date = new Date();
		reservationID = dateFormat.format(date).trim();
		System.out.println();

		System.out.println("GuestID/Name");
		guest.setName(sc.nextLine());
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

		try {
			// read file containing Reservation records.
			ArrayList al = reservationDB.readReservation(filename);
			for (int i = 0; i < al.size(); i++) {
				Reservation reserv = (Reservation) al.get(i);
			}
			al.add(reservation);
			// write Reservation record/s to file.
			reservationDB.saveReservation(filename, al);
			System.out.println("Resevation Details");
		} catch (IOException e) {
			System.out.println("IOException > " + e.getMessage());
		}
		
		
	}

	public void checkIn() {
	}

	public void checkOut() {
	}

}

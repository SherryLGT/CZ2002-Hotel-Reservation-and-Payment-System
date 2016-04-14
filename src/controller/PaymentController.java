package controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

import database.PaymentDB;
import entity.Guest;
import entity.Guest.Identity;
import entity.Payment;
import entity.Reservation;
import entity.Room;
import entity.RoomService;
import ui.HRPSApp;

/**
 * Controller class for payment.
 * 
 * @author Lau Geok Teng
 * @version 1.0
 * @since 2016-03-22
 */

public class PaymentController {

	/**
	 * Declaration of controllers.
	 */
	private GuestController guestControl = new GuestController();
	private ReservationController reservControl = new ReservationController();
	private RoomController roomControl = new RoomController();
	private RoomServiceController rsControl = new RoomServiceController();

	/**
	 * For DB access to payment text file.
	 */
	private PaymentDB paymentDB = new PaymentDB();
	
	/**
	 * Name of payment text file.
	 */
	private String filename = "payment.txt";

	/**
	 * For formatting decimal to 2 decimal places.
	 */
	DecimalFormat df = new DecimalFormat(".##");
	
	/**
	 * For formatting date to appropriate format.
	 */
	DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

	/**
	 * Method for processing payment.
	 */
	public void createPayment() {
		Guest guest = new Guest();
		Reservation reservation = new Reservation();
		Room room = new Room();
		ArrayList<Room> occupiedRooms = new ArrayList<Room>();
		ArrayList<RoomService> roomServices = new ArrayList<RoomService>();

		double charges = 0;
		int daysStayed = 0;
		final double GST = 0.07;
		double tax = 0;
		double discount = 0;
		double totalService = 0;
		double total = 0;

		ArrayList rooms = roomControl.getRoom();
		int count = 0;
		String roomNo;
		boolean valid = false;
		Identity ident = guest.new Identity();
		ArrayList guests = guestControl.getGuest();

		Scanner sc = new Scanner(System.in);

		/**
		 * Checking for room to check out. Retrieve room details.
		 */
		System.out.print("Checked-In rooms: ");
		for (int i = 0; i < rooms.size(); i++) {
			Room rm = (Room) rooms.get(i);
			if (rm.getStatus().equals("Occupied")) {
				occupiedRooms.add(rm);
				count++;

				if (count > 1)
					System.out.print(", " + rm.getRoomNo());
				else
					System.out.print(rm.getRoomNo());
			}
		}

		do {
			System.out.print("\nEnter room number: ");
			roomNo = sc.nextLine();
			for (int index = 0; index < occupiedRooms.size(); index++) {
				if (occupiedRooms.get(index).getRoomNo().equals(roomNo)) {
					room = occupiedRooms.get(index);
					valid = true;
				}
			}
			if (!valid)
				System.out.println("Invalid room no. Please try again.");
		} while (!valid);

		/**
		 * Retrieval of reservation details.
		 */
		reservation = reservControl.searchReservationByRoom(room);

		/**
		 * Retrieval of guest details.
		 */
		ident.setLic(reservation.getGuest().getIdentity().getLic());
		ident.setPp(reservation.getGuest().getIdentity().getPp());
		guest.setIdentity(ident);
		for (int i = 0; i < guests.size(); i++) {
			Guest g = (Guest) guests.get(i);
			if ((g.getIdentity().getLic().equals(reservation.getGuest().getIdentity().getLic())
					|| g.getIdentity().getPp().equals(reservation.getGuest().getIdentity().getPp())))
				guest = g;
		}

		/**
		 * Calculation of number of days stayed.
		 */
		daysStayed = (int) ((reservation.getCheckOut().getTime() - reservation.getCheckIn().getTime())
				/ (24 * 60 * 60 * 1000));

		/**
		 * Calculation of charges charged of room with the number of days stayed.
		 */
		charges = calculateCharges(reservation, room, daysStayed);

		/**
		 * Calculation of tax for the charges.
		 */
		tax = charges * GST;
		tax = Double.valueOf(df.format(tax));

		/**
		 * Retrieve guest details.
		 */
		ident = guest.new Identity();
		ident.setLic(reservation.getGuest().getIdentity().getLic());
		ident.setPp(reservation.getGuest().getIdentity().getPp());
		guest.setIdentity(ident);
		guests = guestControl.getGuest();
		for (int i = 0; i < guests.size(); i++) {
			Guest g = (Guest) guests.get(i);
			if ((g.getIdentity().getLic().equals(reservation.getGuest().getIdentity().getLic())
					|| g.getIdentity().getPp().equals(reservation.getGuest().getIdentity().getPp())))
				guest = g;
		}

		/**
		 * Retrieval of room service details and calculation of total service charged.
		 */
		roomServices = rsControl.searchRoomService(roomServices, guest, room);
		for (int i = 0; i < roomServices.size(); i++) {
			totalService += roomServices.get(i).getItems().getPrice();
		}

		/**
		 * For user to input any discount he has.
		 */
		do {
			valid = false;
			System.out.print("Enter any discount (%): ");
			try {
				discount = sc.nextInt();
				if (discount < 0 || discount > 50)
					System.out.println("Discounts are between " + 0 + "-" + 50 + "%. Please try again.");
				else
					valid = true;
			} catch (InputMismatchException e) {
				System.out.println("You have entered an invalid input. Please try again.");
				sc.next();
			}
		} while (!valid);

		/**
		 * Calculation of overall total amount.
		 */
		total = Double
				.valueOf(df.format(charges + totalService + tax - ((charges + totalService + tax) * (discount / 100))));

		/**
		 * Create payment object to store all relevant data.
		 */
		Payment payment = new Payment(reservation, charges, tax, discount, total, new Date());

		try {
			/**
			 * Saving payment details into DB.
			 */
			ArrayList al = paymentDB.readPayment(filename);
			for (int i = 0; i < al.size(); i++) {
				Payment payments = (Payment) al.get(i);
			}
			al.add(payment);
			paymentDB.savePayment(filename, al);

			/**
			 * Display of bill invoice.
			 */
			System.out.print("\nPayment made by " + guest.getName() + " by ");
			if (reservation.getBillType() == 1)
				System.out.print("cash. ");
			else
				System.out.println("card, " + guest.getCard().getType() + " " + guest.getCard().getNum() + " "
						+ guest.getCard().getExp() + "\nAddress: " + guest.getAddress().getAdd1() + " "
						+ guest.getAddress().getAdd2() + " " + guest.getAddress().getCity() + " "
						+ guest.getAddress().getState() + " " + guest.getAddress().getZip());
			System.out.println("Successful!");
			HRPSApp.header("BILL INVOICE", "*", 36);
			System.out.format("%1s %16s %23s %5s %n", "*", "Days of stay:", daysStayed, "*");
			System.out.format("%1s %28s %11s %5s %n", "*", "Room service order items:", roomServices.size(), "*");
			if (roomServices.size() > 0) {
				for (int i = 0; i < roomServices.size(); i++) {
					count = roomServices.get(i).getItems().getName().length();
					System.out.format("%1s %6s %1s %" + (32 - count) + "s %5s %n", "*", "+",
							roomServices.get(i).getItems().getName(), roomServices.get(i).getItems().getPrice(), "*");
				}
			}
			System.out.format("%1s %31s %8s %5s %n", "*", "Total price of room service:", totalService, "*");
			System.out.format("%1s %7s %32s %5s %n", "*", "Tax:", tax, "*");
			System.out.format("%1s %16s %23s %5s %n", "*", "Total amount:", total, "*");
			HRPSApp.line("*", 48);

			/**
			 * Update reservation details to checked out.
			 */
			reservControl.updateReservation(reservation, 3);

			/**
			 * Update room to vacant.
			 */
			roomControl.updateRoom(room, 3);
		} catch (IOException e) {
			System.out.println("IOException > " + e.getMessage());
		}
	}

	/**
	 * Calculation for charges with respect to price of room and number of days stayed.
	 * 
	 * @param res
	 *            To get the day checked in.
	 * @param r
	 *            To get price of room.
	 * @param days
	 *            Number of days stayed.
	 * @return total amount of charges.
	 */
	private double calculateCharges(Reservation res, Room r, int days) {
		double rate = 0;
		double charges = 0;
		double total = 0;
		Date date = res.getCheckIn();
		Calendar c = Calendar.getInstance();
		DateFormat df = new SimpleDateFormat("EEE dd MMM yyyy");

		System.out.print("\nRoom: " + r.getRoomNo() + ", " + r.getType() + " (S$" + r.getPrice() + ")\n");
		HRPSApp.line("-", 41);
		System.out.format("%1s %4s %14s %17s %1s %n", "|", "DAY", "DATE", "CHARGE(S$)", "|");
		HRPSApp.line("-", 41);

		for (int i = 1; i <= days; i++) {
			c.setTime(date);
			int day = c.get(Calendar.DAY_OF_WEEK);
			// Days of week indexed starting at 1
			if (day > 1 && day < 7)
				rate = 1; // Weekdays
			else
				rate = 1.2; // Weekends

			charges = r.getPrice() * rate;
			System.out.format("%1s %3s %20s %9s %4s %n", "|", i, df.format(date), charges, "|");

			total += charges;

			c.add(Calendar.DATE, 1);
			date = c.getTime();
		}
		HRPSApp.line("=", 41);
		System.out.format("%1s %6s %18s %4s %n", "|", "TOTAL CHARGES: ", total, "|");
		HRPSApp.line("=", 41);

		return total;
	}
}
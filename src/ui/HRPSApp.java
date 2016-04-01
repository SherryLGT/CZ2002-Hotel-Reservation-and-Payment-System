package ui;

import java.util.InputMismatchException;
import java.util.Scanner;

import controller.GuestController;
import controller.MenuController;
import controller.PaymentController;
import controller.ReservationController;
import controller.RoomController;
import controller.RoomServiceController;

public class HRPSApp {
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	/**
	 * \n new line character
	 * \t is a tab
	 * \b backspace
	 * \f form feed in text at that point
	 * \r carriage return
	 * \' single quote character
	 * \" double quote character
	 * \\ backslash character
	 */

	public static void main(String[] aArgs) {
		Scanner sc = new Scanner(System.in);
		int option = -1;
		
		GuestController guestControl = new GuestController();
		PaymentController paymentControl = new PaymentController();
		ReservationController reservationControl = new ReservationController();
		RoomController roomControl = new RoomController();
		RoomServiceController roomServiceControl = new RoomServiceController();
		MenuController menuControl = new MenuController();
		
		System.out.println("=================================\n|\t WELCOME TO HRPS \t|\n=================================");
		System.out.println("| Options: \t\t\t|");
		System.out.println("|\t1. Guest\t\t|");
		System.out.println("|\t2. Reservation\t\t|");
		System.out.println("|\t3. Room Service\t\t|");
		System.out.println("|\t4. Room Maintenance\t|");
		System.out.println("|\t5. Payment\t\t|");
		System.out.println("|\t6. Occupancy Report\t|");
		System.out.println("|\t0. Exit\t\t\t|");
		System.out.println("=================================");

		do {
			System.out.print("Select an option: ");option=5;
			try {
//				option = sc.nextInt();
				if (option < 0 || option > 6)
					System.out.println("You have not selected option between 0-6. Please try again.");
			} catch (InputMismatchException e) {
				System.out.println("You have entered an invalid input. Please try again.");
//				sc.next();
				continue;
			}
		} while (option < 0 || option > 6);
		
		switch (option) {
		case 1: // Guest
			System.out.print("\n~~~~~~~~~~~~~ GUEST ~~~~~~~~~~~~~\n|");
			System.out.format("%7s%8s", "1. ", "Check In (New Guest)\t|\n|");
			System.out.format("%7s%8s", "2. ", "Update Guest Details\t|\n|");
			System.out.format("%7s%8s", "3. ", "Search Guest\t\t|\n");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			do {
				System.out.print("Select an option: ");
				try {
					option = sc.nextInt();
					if (option < 1 || option > 3)
						System.out.println("You have not selected option between 1-3. Please try again.");
				} catch (InputMismatchException e) {
					System.out.println("You have entered an invalid input. Please try again.");
					sc.next();
				}
			} while (option < 1 || option > 3);

			switch (option) {
			case 1:
				reservationControl.checkIn();
				break;
			case 2:
				guestControl.createGuest();
				break;
			case 3:
				System.out.println("Search Guest");
				break;
			}
			break;
		case 2: // Reservation
			reservationControl.kickOut();
			System.out.print("\n~~~~~~~~~~ RESERVATION ~~~~~~~~~~\n|");
			System.out.format("%7s%8s", "1. ", "Create Reservation\t|\n|");
			System.out.format("%7s%8s", "2. ", "Delete Reservation\t|\n");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			do {
				System.out.print("Select an option: ");
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
				reservationControl.createReservation();
				break;
			case 2:
				reservationControl.deleteReservation();
				break;
			}
			break;
		case 3: // Room Service
			System.out.print("\n~~~~~~~~~ ROOM SERVICE ~~~~~~~~~~\n|");
			System.out.format("%10s%8s", "1. ", "Room Service\t\t|\n|");
			System.out.format("%10s%8s", "2. ", "Menu Item\t\t|\n");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			do {
				System.out.print("Select an option: ");
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
				System.out.println("Order Room Service");
				break;
			case 2:
				menuControl.updateItem();
				break;
			}
			break;
		case 4: // Room Maintenance
			System.out.println("\n------- ROOM MAINTENANCE --------");
			roomControl.updateRoomMaintenance();
			break;
		case 5: // Payment
			System.out.println("\n------------ PAYMENT ------------");
			paymentControl.createPayment();
			break;
		case 6: // Occupancy Report
			System.out.print("\n~~~~~~~~~~~ OCCUPANCY REPORT ~~~~~~~~~~~~\n|");
			System.out.format("%7s%8s", "1. ", "Print Room Occupancy Status\t|\n|");
			System.out.format("%7s%8s", "2. ", "Print Room Status\t\t|\n");
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			do {
				System.out.print("Select an option: ");
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
				roomControl.printRoomOccupancy();
				break;
			case 2:
				roomControl.printRoomStatus();
				break;
			}
			break;
		case 0: // Exit
			System.out.print("\nThank you for using HRPS!");
			System.exit(0);
			break;
		}
	}
}

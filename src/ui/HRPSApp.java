package ui;

import java.util.InputMismatchException;
import java.util.Scanner;

import controller.GuestController;
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
	 * \n new line character \t is a tab \b backspace \f form feed in text at
	 * that point \r carriage return \' single quote character \" double quote
	 * character \\ backslash character
	 */

	public static void main(String[] aArgs) {
		Scanner sc = new Scanner(System.in);
		int option = -1;

		GuestController guestControl = new GuestController();
		PaymentController paymentControl = new PaymentController();
		ReservationController reservationControl = new ReservationController();
		RoomController roomControl = new RoomController();
		RoomServiceController roomServiceControl = new RoomServiceController();

		System.out.println(
				"=================================\n|\t WELCOME TO HRPS \t|\n=================================");
		System.out.println("| Options: \t\t\t|");
		System.out.println("|\t1. Guest\t\t|");
		System.out.println("|\t2. Reservation\t\t|");
		System.out.println("|\t3. Room Service\t\t|");
		System.out.println("|\t4. Room Maintenance\t|");
		System.out.println("|\t5. Payment\t\t|");
		System.out.println("|\t6. Occupancy Report\t|");
		System.out.println("|\t7. Exit\t\t\t|");
		System.out.println("=================================");

		do {
			System.out.print("Select an option: ");
			try {
				option = sc.nextInt();
				if (option < 1 || option > 7)
					System.out.println("You have not selected option between 1-7. Please try again.");
			} catch (InputMismatchException e) {
				System.out.println("You have entered an invalid input. Please try again.");
				sc.next();
				continue;
			}
		} while (option < 1 || option > 7);

		switch (option) {
		case 1: // Guest
			System.out.println("\n~~~~~~~~~ Guest ~~~~~~~~~");
			System.out.println("1. Check In (New Guest)");
			System.out.println("2. Update Guest Details");
			System.out.println("3. Search Guest\n");
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
			System.out.println("\n~~~~~~~~~ Reservation ~~~~~~~~~");
			System.out.println("1. Create Reservation");
			System.out.println("2. Delete Reservation\n");
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
			System.out.println("\n~~~~~~~~~ Room Service ~~~~~~~~~");
			System.out.println("1. Room Service");
			System.out.println("2. Menu Item\n");
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
				System.out.println("Menu Items");
				break;
			}
			break;
		case 4: // Room Maintenance
			System.out.println("\n-------- Room Maintenance ---------\n");
			roomControl.updateRoomMaintenance();
			break;
		case 5: // Payment
			System.out.println("\n--------- Payment ---------\n");
			paymentControl.createPayment();
			break;
		case 6: // Occupancy Report
			System.out.println("\n~~~~~~~~~ Occupancy Report ~~~~~~~~~");
			System.out.println("1. Print Room Occupancy Status");
			System.out.println("2. Print Room Status\n");
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
		case 7: // Exit
			System.out.print("Thank you for using HRPS!");
			System.exit(0);
			break;
		}
	}
}

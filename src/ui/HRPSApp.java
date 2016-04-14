package ui;

import java.util.InputMismatchException;
import java.util.Scanner;

import controller.GuestController;
import controller.MenuController;
import controller.PaymentController;
import controller.ReservationController;
import controller.RoomController;
import controller.RoomServiceController;

/**
 * Main class for user interaction, consists of main menu and sub-menus.
 * 
 * @author Lau Geok Teng
 * @version 1.0
 * @since 2016-03-21
 */
public class HRPSApp {
	
	/**
	 * Main method, on start access to main menu.
	 */
	public static void main(String[] aArgs) {
		
		/**
		 * Scanner for user input.
		 */
		Scanner sc = new Scanner(System.in);
		
		/**
		 * Object for checking of user's selection.
		 */
		int option = -1;
		
		/**
		 * Declaration of controllers.
		 */
		GuestController guestControl = new GuestController();
		PaymentController paymentControl = new PaymentController();
		ReservationController reservationControl = new ReservationController();
		RoomController roomControl = new RoomController();
		RoomServiceController roomServiceControl = new RoomServiceController();
		MenuController menuControl = new MenuController();

		do {
			System.out.print("\033[H\033[2J");
			
			/**
			 * Main menu of HRPS.
			 * Require valid user input to access sub-menus.
			 */
			HRPSApp.line("=", 68);
			System.out.format("%1s %59s %6s %n", "|", "WELCOME TO HOTEL RESERVATION AND PAYMENT SYSTEM (HRPS)", "|");
			HRPSApp.line("=", 68);
			System.out.format("%1s %13s %52s %n", "|", "OPTIONS:", "|");
			System.out.format("%1s %16s %6s %42s %n", "|", "1.", "GUEST", "|");
			System.out.format("%1s %16s %5s %43s %n", "|", "2.", "ROOM", "|");
			System.out.format("%1s %16s %12s %36s %n", "|", "3.", "RESERVATION", "|");
			System.out.format("%1s %16s %20s %28s %n", "|", "4.", "PAYMENT (CHECK OUT)", "|");
			System.out.format("%1s %16s %14s %34s %n", "|", "5.", "STATUS REPORT", "|");
			System.out.format("%1s %16s %5s %43s %n", "|", "0.", "EXIT", "|");
			HRPSApp.line("=", 68);

			option = optionChecking(0, 5);

			switch (option) {
			
			/**
			 * Guest menu. Includes add, update and search guest.
			 */
			case 1: // GUEST
				HRPSApp.header("GUEST", "~", 63);
				System.out.format("%1s %17s %8s %34s %n", "|", "1. ", "ADD NEW GUEST", "|");
				System.out.format("%1s %17s %8s %27s %n", "|", "2. ", "UPDATE GUEST DETAILS", "|");
				System.out.format("%1s %17s %8s %35s %n", "|", "3. ", "SEARCH GUEST", "|");
				System.out.format("%1s %17s %3s %43s %n", "|", "0. ", "BACK", "|");
				HRPSApp.line("~", 68);

				option = optionChecking(0, 3);

				switch (option) {
				case 1:
					guestControl.createGuest();
					break;
				case 2:
					guestControl.updateGuest();
					break;
				case 3:
					guestControl.printGuest(guestControl.getGuestDetails());
					break;
				}
				break;
				
			/**
			 * Room menu. Includes add and update of room, room service related methods
			 * and update room maintenance status.
			 */
			case 2: // ROOM
				reservationControl.kickOut();
				HRPSApp.header("ROOM", "~", 64);
				System.out.format("%1s %17s %8s %39s %n", "|", "1. ", "ADD ROOM", "|");
				System.out.format("%1s %17s %8s %28s %n", "|", "2. ", "UPDATE ROOM DETAILS", "|");
				System.out.format("%1s %17s %8s %35s %n", "|", "3. ", "ROOM SERVICE", "|");
				System.out.format("%1s %17s %8s %31s %n", "|", "4. ", "ROOM MAINTENANCE", "|");
				System.out.format("%1s %17s %3s %43s %n", "|", "0. ", "BACK", "|");
				HRPSApp.line("~", 68);

				option = optionChecking(0, 4);

				switch (option) {
				case 1:
					roomControl.createRoom();
					break;
				case 2:
					roomControl.updateRoomDetails();
					break;

				/**
				 * Room sub-menu. Includes room service and menu related methods.
				 */
				case 3: // ROOM SERVICE
					HRPSApp.header("ROOM SERVICE", "~", 56);
					System.out.format("%1s %17s %8s %35s %n", "|", "1. ", "ROOM SERVICE", "|");
					System.out.format("%1s %17s %8s %38s %n", "|", "2. ", "MENU ITEM", "|");
					System.out.format("%1s %17s %3s %43s %n", "|", "0. ", "BACK", "|");
					HRPSApp.line("~", 68);

					option = optionChecking(0, 2);
					
					switch (option) {
					
					/**
					 * Room service sub-menu. Includes ordering of room service and updates of order status.
					 */
					case 1:
						HRPSApp.header("ORDER SERVICE", "~", 55);
						System.out.format("%1s %17s %8s %29s %n", "|", "1. ", "ORDER ROOM SERVICE", "|");
						System.out.format("%1s %17s %8s %28s %n", "|", "2. ", "UPDATE ORDER STATUS", "|");
						System.out.format("%1s %17s %3s %43s %n", "|", "0. ", "BACK", "|");
						HRPSApp.line("~", 68);

						option = optionChecking(0, 2);

						switch (option) {
						case 1:
							roomServiceControl.guestOrder();
							break;
						case 2:
							roomServiceControl.updateStatus();
							break;
						}
						break;
						
					/**
					 * Menu item menu. Includes add, update and removal of menu item.
					 */
					case 2:
						HRPSApp.header("MENU OPTIONS", "~", 56);
						System.out.format("%1s %17s %8s %34s %n", "|", "1. ", "ADD MENU ITEM", "|");
						System.out.format("%1s %17s %8s %31s %n", "|", "2. ", "UPDATE MENU ITEM", "|");
						System.out.format("%1s %17s %8s %31s %n", "|", "3. ", "REMOVE MENU ITEM", "|");
						System.out.format("%1s %17s %3s %43s %n", "|", "0. ", "BACK", "|");
						HRPSApp.line("~", 68);

						option = optionChecking(0, 3);

						switch (option) {
						case 1:
							menuControl.createItem();
							break;
						case 2:
							menuControl.updateItem();
							break;
						case 3:
							menuControl.removeItem();
							break;
						}
					}
					break;

				/**
				 * Menu access to room maintenance status update method.
				 */
				case 4: // ROOM MAINTENANCE
					HRPSApp.header("ROOM MAINTENANCE", "-", 52);
					roomControl.updateRoomMaintenance();
					break;
				}

				break;
			
			/**
			 * Reservation menu. Includes check in, creation, deletion and printing of reservations.
			 */
			case 3: // RESERVATION
				reservationControl.kickOut();
				HRPSApp.header("RESERVATION", "~", 57);
				System.out.format("%1s %17s %8s %39s %n", "|", "1. ", "CHECK IN", "|");
				System.out.format("%1s %17s %8s %29s %n", "|", "2. ", "CREATE RESERVATION", "|");
				System.out.format("%1s %17s %8s %29s %n", "|", "3. ", "DELETE RESERVATION", "|");
				System.out.format("%1s %17s %8s %30s %n", "|", "4. ", "PRINT RESERVATION", "|");
				System.out.format("%1s %17s %3s %43s %n", "|", "0. ", "BACK", "|");
				HRPSApp.line("~", 68);

				option = optionChecking(0, 4);

				switch (option) {
				case 1:
					reservationControl.checkIn();
					break;
				case 2:
					reservationControl.createReservation();
					break;
				case 3:
					reservationControl.deleteReservation();
					break;
				case 4:
					reservationControl.printReservation();
					break;
				}
				break;

			/**
			 * Menu access to payment (check out).
			 */
			case 4: // PAYMENT
				HRPSApp.header("PAYMENT", "-", 61);
				paymentControl.createPayment();
				break;
				
			/**
			 * Status report menu. Includes printing of room occupancy status and room status.
			 */
			case 5: // STATUS REPORT
				HRPSApp.header("STATUS REPORT", "~", 55);
				System.out.format("%1s %17s %8s %20s %n", "|", "1. ", "PRINT ROOM OCCUPANCY STATUS", "|");
				System.out.format("%1s %17s %8s %30s %n", "|", "2. ", "PRINT ROOM STATUS", "|");
				System.out.format("%1s %17s %3s %43s %n", "|", "0. ", "BACK", "|");
				HRPSApp.line("~", 68);

				option = optionChecking(0, 2);

				switch (option) {
				case 1:
					roomControl.printRoomOccupancy();
					break;
				case 2:
					roomControl.printRoomStatus();
					break;
				}
				break;
			case 0: // EXIT
				System.out.print("THANK YOU FOR USING HRPS!");
				sc.nextLine();
				System.exit(0);
				break;
			}
			
			/**
			 *  In cases where user wants to go back to main menu.
			 */
			System.out.print("Press enter key to continue...");
			sc.nextLine();
			option = 0;
		} while (option == 0);
	}

	/**
	 * Checking user input to ensure proper value is used.
	 * 
	 * @param min
	 * 		Ensure that user's input is within minimum range of option provided.
	 * @param max
	 * 		Ensure that user's input is within maximum range of option provided.
	 * @return valid option selection.
	 */
	private static int optionChecking(int min, int max) {
		Scanner sc = new Scanner(System.in);
		int option = -1;

		do {
			System.out.print("Select an option: ");
			try {
				option = sc.nextInt();
				if (option < min || option > max)
					System.out
							.println("You have not selected option between " + min + "-" + max + ". Please try again.");
			} catch (InputMismatchException e) {
				System.out.println("You have entered an invalid input. Please try again.");
				sc.next();
			}
		} while (option < min || option > max);
		System.out.println();
		return option;
	}

	/**
	 * To display a header with appropriate text, pattern and length.
	 * 
	 * @param text
	 * 		Text to display on the header.
	 * @param pattern
	 * 		Pattern to display as the line.
	 * @param size
	 * 		Size to determine how long the header would be.
	 */
	public static void header(String text, String pattern, int size) {
		System.out.print(new String(new char[(size / 2) - 1]).replace("\0", pattern));
		System.out.print(" " + text + " ");
		if (size % 2 != 0)
			System.out.print(pattern);
		System.out.println(new String(new char[(size / 2) - 1]).replace("\0", pattern));
	}

	/**
	 * To display a straight line with appropriate pattern and length.
	 * 
	 * @param pattern
	 * 		Pattern to display as the line.
	 * @param size
	 * 		Size to determine how long the line would be.
	 */
	public static void line(String pattern, int size) {
		System.out.println(new String(new char[size]).replace("\0", pattern));
	}
}

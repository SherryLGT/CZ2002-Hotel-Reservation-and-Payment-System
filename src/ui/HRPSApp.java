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
	public static void main(String[] aArgs) {
		Scanner sc = new Scanner(System.in);
		int option = -1;
		
		GuestController guestControl = new GuestController();
		PaymentController paymentControl = new PaymentController();
		ReservationController reservationControl = new ReservationController();
		RoomController roomControl = new RoomController();
		RoomServiceController roomServiceControl = new RoomServiceController();
		MenuController menuControl = new MenuController();
		
		roomControl.printRooms();
		
		do {
			System.out.print("\033[H\033[2J");
			
			HRPSApp.line("=", 41);
			System.out.format("%1s %26s %12s %n", "|", "WELCOME TO HRPS", "|");
			HRPSApp.line("=", 41);
			System.out.format("%1s %8s %30s %n", "|", "Options:", "|");
			System.out.format("%1s %11s %1s %21s %n", "|", "1.", "Guest", "|");
			System.out.format("%1s %11s %1s %15s %n", "|", "2.", "Reservation", "|");
			System.out.format("%1s %11s %1s %14s %n", "|", "3.", "Room Service", "|");
			System.out.format("%1s %11s %1s %10s %n", "|", "4.", "Room Maintenance", "|");
			System.out.format("%1s %11s %1s %19s %n", "|", "5.", "Payment", "|");
			System.out.format("%1s %11s %1s %10s %n", "|", "6.", "Occupancy Report", "|");
			System.out.format("%1s %11s %1s %22s %n", "|", "0.", "Exit", "|");
			HRPSApp.line("=", 41);
			
			option = optionChecking(0, 6);
			System.out.println();
			
			switch (option) {
			case 1: // Guest
				HRPSApp.header("GUEST", "~", 36);
				System.out.format("%1s %9s %8s %8s %n", "|", "1. ", "Check In (New Guest)", "|");
				System.out.format("%1s %9s %8s %8s %n", "|", "2. ", "Update Guest Details", "|");
				System.out.format("%1s %9s %8s %16s %n", "|", "3. ", "Search Guest", "|");
				System.out.format("%1s %9s %3s %24s %n", "|", "0. ", "Back", "|");
				HRPSApp.line("~", 41);
				
				option = optionChecking(0, 3);
	
				switch (option) {
				case 1:
					reservationControl.checkIn();
					break;
				case 2:
					guestControl.createGuest();
					break;
				case 3:
					//guestControl.updateGuest();
					
					System.out.println("\n------------ Search Guest ------------");
					guestControl.printGuest(guestControl.getGuestDetails());
					break;
				}
				break;
			case 2: // Reservation
				reservationControl.kickOut();
				HRPSApp.header("RESERVATION", "~", 30);
				System.out.format("%1s %10s %8s %9s %n", "|", "1. ", "Create Reservation", "|");
				System.out.format("%1s %10s %8s %9s %n", "|", "2. ", "Delete Reservation", "|");
				System.out.format("%1s %10s %3s %23s %n", "|", "0. ", "Back", "|");
				HRPSApp.line("~", 41);
				
				option = optionChecking(0, 2);
	
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
				HRPSApp.header("ROOM SERVICE", "~", 29);
				System.out.format("%1s %13s %8s %12s %n", "|", "1. ", "Room Service", "|");
				System.out.format("%1s %13s %8s %15s %n", "|", "2. ", "Menu Item", "|");
				System.out.format("%1s %13s %3s %20s %n", "|", "0. ", "Back", "|");
				HRPSApp.line("~", 41);
				
				option = optionChecking(0, 2);
	
				switch (option) {
				case 1:
					System.out.println("Order Room Service");
					break;
				case 2:
					System.out.println("1. Add Menu Item");
					System.out.println("2. Update Menu Item");
					System.out.println("3. Remove Menu Item");
					
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
			case 4: // Room Maintenance
				HRPSApp.header("ROOM MAINTENANCE", "-", 25);
				roomControl.updateRoomMaintenance();
				break;
			case 5: // Payment
				HRPSApp.header("PAYMENT", "-", 34);
				paymentControl.createPayment();
				break;
			case 6: // Occupancy Report
				HRPSApp.header("OCCUPANCY REPORT", "~", 25);
				System.out.format("%1s %6s %8s %4s %n", "|", "1. ", "Print Room Occupancy Status", "|");
				System.out.format("%1s %6s %8s %14s %n", "|", "2. ", "Print Room Status", "|");
				System.out.format("%1s %6s %3s %27s %n", "|", "0. ", "Back", "|");
				HRPSApp.line("~", 41);
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
			case 0: // Exit
				System.out.print("Thank you for using HRPS!");
				System.exit(0);
				break;
			}
			
			System.out.print("Press any key to continue...");
			sc.nextLine();
			option = 0;
		} while(option == 0);
	}
	
	private static int optionChecking(int min, int max) {
		Scanner sc = new Scanner(System.in);
		int option = -1;
		
		do {
			System.out.print("Select an option: ");
			try {
				option = sc.nextInt();
				if (option < min || option > max)
					System.out.println("You have not selected option between " + min + "-" + max + ". Please try again.");
			} catch (InputMismatchException e) {
				System.out.println("You have entered an invalid input. Please try again.");
				sc.next();
			}
		} while (option < min || option > max);
		return option;
	}
    
    public static void line(String pattern, int size) {
    	System.out.println(new String(new char[size]).replace("\0", pattern));
    }
    public static void header(String text, String pattern, int size) {
    	System.out.print(new String(new char[(size/2)-1]).replace("\0", pattern));
    	System.out.print(" " + text + " ");
    	if(size%2!=0)
    		System.out.print(pattern);
    	System.out.println(new String(new char[(size/2)-1]).replace("\0", pattern));
    }
}

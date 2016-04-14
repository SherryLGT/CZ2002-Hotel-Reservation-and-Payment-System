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
		
		do {
			
			HRPSApp.line("=", 68);
			System.out.format("%1s %59s %6s %n", "|", "WELCOME TO HOTEL RESERVATION AND PAYMENT SYSTEM (HRPS)", "|");
			HRPSApp.line("=", 68);
			System.out.format("%1s %13s %52s %n", "|", "OPTIONS:", "|");
			System.out.format("%1s %16s %6s %42s %n", "|", "1.", "GUEST", "|");
			System.out.format("%1s %16s %5s %43s %n", "|", "2.", "ROOM", "|");
			System.out.format("%1s %16s %12s %36s %n", "|", "3.", "RESERVATION", "|");
			System.out.format("%1s %16s %19s %29s %n", "|", "4.", "PAYMENT (CHECKOUT)", "|");
			System.out.format("%1s %16s %17s %31s %n", "|", "5.", "STATUS REPORT", "|");
			System.out.format("%1s %16s %5s %43s %n", "|", "0.", "EXIT", "|");
			HRPSApp.line("=", 68);
			
			option = optionChecking(0, 5);
			
			switch (option) {
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
			case 2: // ROOM;
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
				case 3: // ROOM SERVICE
					HRPSApp.header("ROOM SERVICE", "~", 56);
					System.out.format("%1s %17s %8s %35s %n", "|", "1. ", "ROOM SERVICE", "|");
					System.out.format("%1s %17s %8s %38s %n", "|", "2. ", "MENU ITEM", "|");
					System.out.format("%1s %17s %3s %43s %n", "|", "0. ", "BACK", "|");
					HRPSApp.line("~", 68);
					
					option = optionChecking(0, 2);
					
					switch (option) {
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
				case 4: // ROOM MAINTENANCE
					HRPSApp.header("ROOM MAINTENANCE", "-", 52);
					roomControl.updateRoomMaintenance();
					break;
				}
				
				break;
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
			case 4: // Payment
				HRPSApp.header("PAYMENT", "-", 61);
				paymentControl.createPayment();
				break;
			case 5: // OCCUPANCY STATUS
				HRPSApp.header("OCCUPANCY STATUS", "~", 52);
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
			
			System.out.print("Press enter key to continue...");
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
		System.out.println();
		return option;
	}
    
    public static void header(String text, String pattern, int size) {
    	System.out.print(new String(new char[(size/2)-1]).replace("\0", pattern));
    	System.out.print(" " + text + " ");
    	if(size%2!=0)
    		System.out.print(pattern);
    	System.out.println(new String(new char[(size/2)-1]).replace("\0", pattern));
    }
    
    public static void line(String pattern, int size) {
    	System.out.println(new String(new char[size]).replace("\0", pattern));
    }
}

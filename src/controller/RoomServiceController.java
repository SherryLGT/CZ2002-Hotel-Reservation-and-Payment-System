package controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

import database.MenuDB;
import database.RoomServiceDB;
import entity.Guest;
import entity.Menu;
import entity.Reservation;
import entity.Room;
import entity.RoomService;
import entity.Guest.Identity;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class RoomServiceController {
	private MenuController menuControl = new MenuController();
	private MenuDB menuDb = new MenuDB();
	private String filenamemenu = "menu.txt";
	private GuestController guestControl = new GuestController();
	private RoomController roomControl = new RoomController();
	
	private RoomServiceDB roomserviceDb = new RoomServiceDB();
	private String filename = "roomservice.txt";
	DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
	Scanner sc = new Scanner(System.in);
	int option = -1;
	
	public void guestOrder() {
		RoomService roomservice = new RoomService();
		Menu item = new Menu();
		Guest guest = new Guest();
		Room room = new Room();
		
		String remarks = "";
		String status = "Confirmed";
		int rsID = 0;
		Date date;
		//int identityType = 0;
		//int room = 0;
		//int item = 0;
		//String guest = "";

		
		System.out.println();
		System.out.println("~~~~~ORDER FOR GUEST~~~~~");
		
		System.out.println();
		System.out.println("Please enter guest orders: ");
		System.out.println();
		
		ArrayList al = null;
		try {
			System.out.print("Enter Guest Identity Number: ");
			Identity ident = guest.new Identity();
			guest.setIdentity(ident);
			//IdentityType = sc.nextInt();
			sc.nextLine();
					
		//	guest = sc.nextLine();
			//Identity ident = guest.new Identity();
			//guest.setIdentity(ident);
			//identityType = sc.nextInt();
			//sc.nextLine();
				
			
			System.out.println();
			System.out.print("Enter Room Number: ");
			room.setRoomNo(sc.nextLine());
			sc.nextLine();
			//Room r = room.new Room();
			//room = sc.nextInt();
			//room.setRoomNo(RoomNo);
			//room.setRoomNo(sc.nextInt());
			
			
			//if () {
				// Do if-else. Check if the guest and room is same/correct. 
				// If wrong, prompt them. If correct, let them choose menu	
			//}
			
			/*
			al = menuDb.readMenu(filenamemenu);
			String repeated = new String(new char[92]).replace("\0", "-");
			System.out.println(repeated);
			System.out.printf("%3s %23s %53s %10s", "ID", "NAME", "DESCRIPTION", "S$ PRICE");
			System.out.println();
			System.out.println(repeated);
			for (int i = 0; i < al.size(); i++) {
				Menu menu = (Menu) al.get(i);
				System.out.format("%3s %23s %53s %10s", menu.getID(), menu.getName(), menu.getDescription(),
						menu.getPrice());
				System.out.println();
			}
			System.out.println(repeated);
			*/
			
			
			System.out.println();
			System.out.print("Enter Menu Item ID: ");
			item.setID(sc.nextInt());
			sc.nextLine();
			//item = sc.nextInt();
			
			
            Date date1 = new Date();

			
			System.out.println();
			System.out.print("Enter Remark for Item: ");
			remarks = sc.nextLine();
			
			// Do a check whether they want to put remark or not.
			
			
			do {
				System.out.println();
				System.out.print("Order Confirmation - (1) Yes   (2) No: ");
				System.out.println();
				try {
					option = sc.nextInt();
					roomservice.setItems(item);
					roomservice.setDate(date1);
					roomservice.setRemarks(remarks);
					roomservice.setStatus(status);
					roomservice.setGuest(guest);
					roomservice.setRoom(room);

				} catch (InputMismatchException e) {
					System.out.println("You have entered an invalid input. Please try again.");
					sc.next();
				}
			} while (option < 1 || option > 2);
			
			
			
			
			try {
				RoomService rs = (RoomService) al.get(al.size() - 1);
				roomservice.setRoomServiceID(al.size() + 1);

				if (option == 1) {
					item.setID(al.size() + 1); /*
					System.out.println(repeated);
					System.out.printf("%5s %5s %13s %55s %12s %5s %5s", "ID", "ITEM ID", "DATE", "REMARKS", "STATUS", "GUEST ID", "ROOM ID");
					System.out.println();
					System.out.println(repeated);

					System.out.format("%3s %23s %53s %10s", al.size() + 1, item, date1, remarks, status, guest, room);
					System.out.println();

					System.out.println(repeated);
*/
					al.add(item);

					// write item record/s to file.
					menuDb.saveMenu(filename, al);
					System.out.println();
					System.out.println("Item stored successfully!");

				} else {
					System.out.println("Item not saved.");
				}

			} catch (IOException e) {
				System.out.println("IOException > " + e.getMessage());
			}

			
		/*	
		} catch (IOException e1) {
			System.out.println("IOException > " + e1.getMessage());
		}
		*/
		} catch (InputMismatchException e) {
			System.out.println("You have entered an invalid input. Please try again.");
			sc.next();
		}
		
	}
	
	
}

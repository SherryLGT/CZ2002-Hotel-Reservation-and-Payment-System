package controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

import database.MenuDB;
import database.RoomServiceDB;
import entity.Guest;
import entity.Menu;
import entity.Room;
import entity.RoomService;

public class RoomServiceController {
	private RoomServiceDB RoomServiceDb = new RoomServiceDB();
	private String filename = "roomservice.txt";
	
	private MenuDB menuDb = new MenuDB();
	private String filenamemenu = "menu.txt";
	
	DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
	Scanner sc = new Scanner(System.in);
	int option = -1;
	
    private MenuController menuControl = new MenuController();
	
	public void guestOrder() {
		GuestController guestControl = new GuestController();
		
		RoomService roomservice = new RoomService();
		Menu item = new Menu();
		Guest guest = new Guest();
		Room room = new Room();
		Date date = new Date();
		
		String remarks = "";
		String status = "Confirmed";
		
		System.out.println();
		System.out.println("~~~~~ORDER FOR GUEST~~~~~");
		
		System.out.println();
		System.out.println("Please enter guest orders: ");
		System.out.println();
		
		
		ArrayList al, almenu;
		
		try {
			
		al = RoomServiceDb.readRoomService(filename);

		for (int x = 0; x < al.size(); x++) {
			RoomService rs = (RoomService) al.get(x);

			guest = guestControl.getGuestDetails();
			roomservice.setGuest(guest);
			
			if ((guest.getIdentity().getLic().equals(roomservice.getGuest().getIdentity().getLic()))
					|| (guest.getIdentity().getPp().equals(roomservice.getGuest().getIdentity().getPp()))) {
				roomservice.setGuest(guest);
				
				
				System.out.print("Enter Room Number: ");
				room.setRoomNo(sc.nextLine());
				roomservice.setRoom(room);
				
				
				if ((room.getRoomNo().equals(roomservice.getRoom().getRoomNo()))) {

					
					almenu = menuDb.readMenu(filenamemenu);
					System.out.println();
					String repeated = new String(new char[92]).replace("\0", "-");
					System.out.println(repeated);
					System.out.printf("%3s %23s %53s %10s", "ID", "NAME", "DESCRIPTION", "S$ PRICE");
					System.out.println();
					System.out.println(repeated);
					for (int i = 0; i < almenu.size(); i++) {
						Menu m = (Menu) almenu.get(i);
						System.out.format("%3s %23s %53s %10s", m.getID(), m.getName(), m.getDescription(),
								m.getPrice());
						System.out.println();
					}
					System.out.println(repeated);
					
					System.out.println("Enter Menu Item ID: ");
					item.setID(sc.nextInt());
					roomservice.setItems(item);
					sc.nextLine();
					
					System.out.println();
					System.out.print("Enter Remark for Item: ");
					remarks = sc.nextLine();
					
						
					} else { // room if
				System.out.println("Room don't exist. Please try again.");	
			} 
			} else {// guest if
				System.out.println("Guest identity don't match. Please try again.");
			}
			
		do {
			System.out.println();
			System.out.print("Order Confirmation - (1) Yes   (2) No: ");
			System.out.println();
			try {
				option = sc.nextInt();

				if (option == 1) {
					
					roomservice.setItems(item);
					roomservice.setDate(date);
					roomservice.setRemarks(remarks);
					roomservice.setStatus(status);

				roomservice.setRoomServiceID(al.size() +1);
				
				System.out.println();
				String repeated = new String(new char[100]).replace("\0", "-");
				System.out.println(repeated);
				System.out.printf("%3s %9s %35s %16s %13s %10s %8s", "ID", "ITEM ID", "DATE", "REMARKS", 
						"STATUS", "GUEST ID", "ROOM ID");
				System.out.println();
				System.out.println(repeated);
				
				if (roomservice.getGuest().getIdentity().getPp().equals("null")) {
					System.out.format("%3s %9s %35s %16s %13s %10s %8s", al.size() + 1, rs.getItems().getID(), date, remarks, 
							status, roomservice.getGuest().getIdentity().getLic(), roomservice.getRoom().getRoomNo());
				} else {
					System.out.format("%3s %9s %35s %16s %13s %10s %8s", al.size() + 1, rs.getItems().getID(), date, remarks, 
							status, roomservice.getGuest().getIdentity().getPp(), roomservice.getRoom().getRoomNo());
				}
				System.out.println();

				System.out.println(repeated);

				al.add(roomservice);
				
							
				RoomServiceDb.saveRoomService(filename, al);
				System.out.println();
				System.out.println("Guest Order stored successfully!");

			} else {
				System.out.println("Guest Order not saved.");
			}
			} catch (InputMismatchException e) {
				System.out.println("You have entered an invalid input. Please try again.");
				sc.next();
			}
		} while (option < 1 || option > 2);
		
		break;
		
		}
		
		} catch (IOException e) {
			System.out.println("IOException > " + e.getMessage());	
		}	
	}


	
	public void updateStatus() {
		
		GuestController guestControl = new GuestController();
		
		RoomService roomservice = new RoomService();
		Guest guest = new Guest();
		
		Integer roomserviceID = 0;
		
		System.out.println("Enter Room Service ID: ");
		roomserviceID = sc.nextInt();
		
		getRoomService();
		
		ArrayList al;
		
		try {
			
		al = RoomServiceDb.readRoomService(filename);
		
		for (int x = 0; x < al.size(); x++) {
			RoomService rs = (RoomService) al.get(x);

			guest = guestControl.getGuestDetails();
			roomservice.setGuest(guest);
			
			if ((roomserviceID.equals(rs.getRoomServiceID())) && 
					((guest.getIdentity().getLic().equals(roomservice.getGuest().getIdentity().getLic()))
					|| (guest.getIdentity().getPp().equals(roomservice.getGuest().getIdentity().getPp())))) {
				roomservice.setGuest(guest);
				
				System.out.print("\n~~~~~~~~~ ORDER STATUS ~~~~~~~~~~\n|");
				System.out.format("%10s%8s", "1. ", "Preparing\t\t|\n|");
				System.out.format("%10s%8s", "2. ", "Delivered\t\t|\n|");
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
					String status = "Preparing";
					roomservice.setRoomServiceID(al.size() +1);
					roomservice.setStatus(status);
					
					System.out.println();
					String repeated = new String(new char[100]).replace("\0", "-");
					System.out.println(repeated);
					System.out.printf("%3s %9s %35s %16s %13s %10s %8s", "ID", "ITEM ID", "DATE", "REMARKS", 
							"STATUS", "GUEST ID", "ROOM ID");
					System.out.println();
					System.out.println(repeated);
					
					if (roomservice.getGuest().getIdentity().getPp().equals("null")) {
						System.out.format("%3s %9s %35s %16s %13s %10s %8s", rs.getRoomServiceID(), rs.getItems().getID(), 
								rs.getDate(), rs.getRemarks(), roomservice.getStatus(), 
								rs.getGuest().getIdentity().getLic(), rs.getRoom().getRoomNo());
			
						rs.getGuest().getIdentity().getLic();

					} else {
						System.out.format("%3s %9s %35s %16s %13s %10s %8s", rs.getRoomServiceID(), rs.getItems().getID(), 
								rs.getDate(), rs.getRemarks(), roomservice.getStatus(), 
								rs.getGuest().getIdentity().getPp(), rs.getRoom().getRoomNo());

						rs.getGuest().getIdentity().getPp();

					}
					
					rs.setStatus(status);
					rs.getRoomServiceID();
					rs.getItems();
					rs.getDate();
					rs.getRemarks();
					rs.getRoom();
					
					System.out.println();

					System.out.println(repeated);	
								
					RoomServiceDb.saveRoomService(filename, al);
					System.out.println();
					System.out.println("Guest Order updated successfully!");
					break;
				case 2:
					break;
				}
				
				
			} else {
				System.out.println("Guest identity don't match. Please try again.");
			}
			break;
		}
				
		} catch (IOException e) {
			System.out.println("IOException > " + e.getMessage());
		}
	}
	
	

	
	public ArrayList getRoomService() {
		ArrayList al = null;
		try {
			al = RoomServiceDb.readRoomService(filename);
		} catch (IOException e) {
			System.out.println("IOException > " + e.getMessage());
		}
		return al;
	}
	
	

	
	public ArrayList<RoomService> searchRoomService(ArrayList<RoomService> rms, Guest guest, Room room) {
		ArrayList roomServices = getRoomService();
		
		for (int i = 0; i < roomServices.size(); i++) {
			RoomService roomService = (RoomService) roomServices.get(i);
			
			if ((guest.getIdentity().getLic().equals(roomService.getGuest().getIdentity().getLic())
					|| guest.getIdentity().getPp().equals(roomService.getGuest().getIdentity().getPp()))
					&& roomService.getRoom().getRoomNo().equals(room.getRoomNo())) {
				Menu item = new Menu();
				item = menuControl.getItemById(roomService.getItems());
				roomService.setItems(item);
				rms.add(roomService);
			}
		}
		return rms;
	}
	
	
}

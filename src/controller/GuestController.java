package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import database.GuestDB;
import entity.Guest;
import entity.Reservation;
import entity.Guest.CreditCard;

public class GuestController {

	private GuestDB guestDb = new GuestDB();
	private String filename = "guest.txt";
	Scanner sc = new Scanner (System.in);
	
	public void createGuest(){
		println("GUEST REGISTRATION");
		
		//initialize attributes
		String name = "", cardNum = "", cvv = "", exp = "", add1 = "", add2 = "", city = "", state = "", zip = "";
		int cardType = 0;
		
		//prompt user for guest details and set it into a guest object
		Guest guest = new Guest();
		println("Please enter the following guest information");
		
		do{
			print("Name: ");
			name = sc.nextLine();
			if(name.equals("")){
				println("Please enter a valid name.");
			}
			else{
				guest.setName(name);
			}
		}while(name.equals(""));
		
		println("\nCredit Card Details");
		
		print("Card Type - (1) Visa (2) Master (3) Amex: ");
		cardType = sc.nextInt();
		switch(cardType){
			case 1: guest.getCard().setType("Visa");break;
			case 2: guest.getCard().setType("Master");break;
			case 3: guest.getCard().setType("Amex");break;
			default: println("Opps, error...");
		}
		
		print("Card Number: ");
		cardNum = sc.nextLine();
		guest.getCard().setNum(cardNum);
		
		print("CVV: ");
		cvv = sc.nextLine();
		guest.getCard().setCvv(cvv);
		
		println("EXP (MM/YY): ");
		exp = sc.nextLine();
		guest.getCard().setExp(exp);
		
		println("\nAddress");
		
		print("Address Line 1: ");
		add1 = sc.nextLine();
		guest.getAddress().setAdd1(add1);
		
		print("Address Line 2: ");
		add2 = sc.nextLine();
		guest.getAddress().setAdd2(add2);
		
		print("City: ");
		city = sc.nextLine();
		guest.getAddress().setCity(city);
		
		print("State: ");
		state = sc.nextLine();
		guest.getAddress().setState(state);
		
		print("Zip: ");
		zip = sc.nextLine();
		guest.getAddress().setZip(zip);
		
		//CONTINUE TO PROMPT FOR THE REMAINING INFO
		
		try {
			// read file containing Guest records.
			ArrayList al = guestDb.readGuest(filename);
			for (int i = 0; i < al.size(); i++) {
				Guest g = (Guest) al.get(i);
			}
			al.add(guest);
			// write Guest record/s to file.
			guestDb.saveGuest(filename, al);
		} catch (IOException e) {
			println("IOException > " + e.getMessage());
		}
	}
	
	private void println(String output){
		System.out.println(output);
	}
	
	private void print(String output){
		System.out.print(output);
	}
}

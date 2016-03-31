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
		println("\n----- Guest Registration -----");

		// Initialize attributes
		String name = "", cardNum = "", cvv = "", exp = "", add1 = "", add2 = "", city = "", state = "", zip = "", country = "", lic = "", pp = "", nationality = "";
		int contact = 0, genderType = 0, cardType = 0, identityType = 0;

		// Prompt user for guest details and set it into a guest object
		Guest guest = new Guest();
		
		// Initialize the other classes within the class here
				
		println("Please enter the following guest information");

		do{
			print("\nName: ");
			name = sc.nextLine();
			if(name.equals("")){
				println("Please enter a valid name.");
			}
			else{
				guest.setName(name);
			}
		}while(name.equals(""));

		println("\nGender: ");

		print("(1) Male (2) Female ");
		genderType = sc.nextInt();
		switch(genderType){
		case 1: guest.setGender("Male");break;
		case 2: guest.setGender("Female");break;
		default: println("Oops, error...");
		}

		println("\nCredit Card Details: ");

		print("Card Type - (1) Visa (2) Master (3) Amex ");
		cardType = sc.nextInt();
		
		switch(cardType){
		
		case 1: 
			guest.getCard().setType("Visa");
			break;
			
		case 2: 
			guest.getCard().setType("Master");
			break;
			
		case 3: 
			guest.getCard().setType("Amex");
			break;
			
		default: println("Oops, error...");
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

		println("\nCountry");

		print("Country: ");
		country = sc.nextLine();
		guest.setCountry(country);

		println("\nIdentity");

		print("Identity Type - (1) Driving License (2) Passport: ");
		identityType = sc.nextInt();
		switch(identityType){

		case 1: 
			lic = sc.nextLine();
			guest.getIdentity().setLic(lic);
			break;

		case 2: 
			pp = sc.nextLine();
			guest.getIdentity().setPp(pp);
			break;

		default: println("Oops, error...");
		}

		print("Nationality: ");
		nationality = sc.nextLine();
		guest.setNationality(nationality);

		print("Contact: ");
		contact = sc.nextInt();
		guest.setContact(contact);

		ArrayList al = getGuest();
		al.add(guest);

		try {
			// Write Guest records to file
			guestDb.saveGuest(filename, al);
		} catch (IOException e) {
			println("IOException > " + e.getMessage());
		}

	}


	public void updateGuest() {
		println("UPDATE GUEST");

		// Initialize attributes
		String name = "", cardNum = "", cvv = "", exp = "", add1 = "", add2 = "", city = "", state = "", zip = "", country = "", lic = "", pp = "", nationality = "";
		int contact = 0, genderType = 0, cardType = 0, identityType = 0; 
		int updateType = 0;

		// Prompt user for guest details 
		Guest guest = null;
		print("Please select identity type - (1) Driving License (2) Passport");
		identityType = sc.nextInt();
		
		if (identityType == 1) {
			print("Please enter driving license: ");
			lic = sc.nextLine();
			
			ArrayList al = getGuest();
			
			for (int i = 0; i < al.size(); i++) {
				Guest updateguest = (Guest) al.get(i);
				
				if (lic.equals(updateguest.getIdentity().getLic())) {
					
					// UPDATE OPERATIONS HERE
					
					print("Please select guest details to update - (1) Name (2) Gender (3) Credit Card (4) Address (5) Country (6) Identity (7) Nationality (8) Contact No.: ");
					updateType = sc.nextInt();
					switch(updateType){

					case 1: 
						
						do{
							print("Please enter name to be updated: ");
							name = sc.nextLine();
							if(name.equals("")){
								println("Please enter a valid name.");
							}
							else{
								guest.setName(name);
							}
						}while(name.equals(""));
						
						println("Name has been successfully updated!");
						break;
						
					case 2: 
						
						pp = sc.nextLine();
						guest.getIdentity().setPp(pp);
						break;

					default: println("Oops, error...");
					}
					
					break;
				}
			}
			
		}
		else
		{
			print("Please enter passport number: ");
			pp = sc.nextLine();
		}
		
		
	}


	
	public ArrayList getGuest() {
		ArrayList al = null;
		try {
			// read file containing Reservation records.
			al = guestDb.readGuest(filename);

		} catch (IOException e) {
			System.out.println("IOException > " + e.getMessage());
		}
		return al;
	}

	
		public Guest searchGuest(String pp) {
		println("SEARCH GUEST");
		
		ArrayList al = getGuest();

		for (int i = 0; i < al.size(); i++) {
			Guest searchguestpp = (Guest) al.get(i);

			if (searchguestpp.getIdentity().getPp().equals(pp))
			{
				return searchguestpp;
			}
		}

		return null;



	}

	private void println(String output){
		System.out.println(output);
	}

	private void print(String output){
		System.out.print(output);
	}
}

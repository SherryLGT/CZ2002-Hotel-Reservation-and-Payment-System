package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import database.GuestDB;
import entity.Guest;
import entity.Reservation;
import entity.Guest.Address;
import entity.Guest.CreditCard;
import entity.Guest.Identity;

public class GuestController {

	private GuestDB guestDB = new GuestDB();
	private String filename = "guest.txt";
	Scanner sc = new Scanner(System.in);

	public void createGuest() {
		println("\n------------ Guest Registration ------------");

		// Initialize attributes
		String name = "";
		String cardNum = "";
		String cvv = "";
		String exp = "";
		String add1 = ""; 
		String add2 = "";
		String city = "";
		String state = ""; 
		String zip = "";
		String country = "";
		String lic = "";
		String pp = "";
		String nationality = "";
		String contact = "";
		int genderType = 0;
		int cardType = 0;
		int identityType = 0;

		// To be used for data validation
		String digit = "\\d+";
		String alpha = "[a-zA-Z.*\\s+.]+";

		// Prompt user for guest details and set it into a guest object
		Guest guest = new Guest();

		println("Please enter the following guest information");

		// Guest Name
		do {
			print("\nName: ");
			name = sc.nextLine();

			if (name.equals("") || !name.matches(alpha)) {

				println("Please enter a valid name");

			} else {

				guest.setName(name);

			}

		} while (name.equals("") || !name.matches(alpha));


		// Guest Gender
		do {
			println("\nGender: ");

			print("(1) Male (2) Female ");
			genderType = sc.nextInt();
			sc.nextLine();

			if (genderType != 1 && genderType != 2) {

				println("Please select a valid gender");

			} else {

				switch (genderType) {

				case 1:
					guest.setGender("Male");
					break;

				case 2:
					guest.setGender("Female");
					break;

				}

			}

		} while (genderType != 1 && genderType != 2);


		// Guest Credit Card Details
		println("\nCredit Card Details ");
		println("...................... \n ");


		// Guest Credit Card - Type
		do {
			print("Card Type - (1) Visa (2) Master (3) Amex: ");
			CreditCard cc = guest.new CreditCard();
			guest.setCard(cc);
			cardType = sc.nextInt();
			sc.nextLine();

			if (cardType != 1 && cardType != 2 && cardType != 3) {

				println("Please select a valid credit card type\n");

			} else {

				switch (cardType) {

				case 1:
					guest.getCard().setType("Visa");
					break;

				case 2:
					guest.getCard().setType("Master");
					break;

				case 3:
					guest.getCard().setType("Amex");
					break;

				}

			} 

		} while (cardType != 1 && cardType != 2 && cardType != 3);


		// Guest Credit Card - No.
		do {
			print("Card Number: ");
			cardNum = sc.nextLine();

			if (cardNum.equals("") || !cardNum.matches(digit)) {

				println("Please enter a valid credit card no.\n");

			} else {

				guest.getCard().setNum(cardNum);

			}

		} while (cardNum.equals("") || !cardNum.matches(digit));


		// Guest Credit Card - CVV
		do {
			print("CVV: ");
			cvv = sc.nextLine();

			if (cvv.equals("") || !cvv.matches(digit)) {

				println("Please enter a valid credit card CVV.\n");

			} else {

				guest.getCard().setCvv(cvv);

			}

		} while (cvv.equals("") || !cvv.matches(digit));


		// Guest Credit Card - Exp Date
		print("EXP (MM/YY): ");
		exp = sc.nextLine();
		guest.getCard().setExp(exp);


		// Guest Address
		println("\nAddress");
		println(".......... \n ");


		// Guest Address - Line 1
		do {
			print("Address Line 1: ");
			Address add = guest.new Address();
			add1 = sc.nextLine();

			if (add1.equals("")) {

				println("Please enter a valid address line 1\n");

			} else {

				guest.setAddress(add);
				guest.getAddress().setAdd1(add1);

			}

		} while (add1.equals(""));


		// Guest Address - Line 2
		do {
			print("Address Line 2: ");
			add2 = sc.nextLine();

			if (add2.equals("")) {

				println("Please enter a valid address line 2\n");

			} else {

				guest.getAddress().setAdd2(add2);

			}

		} while (add2.equals(""));


		// Guest Address - City
		do {
			print("City: ");
			city = sc.nextLine();

			if (city.equals("")) {

				println("Please enter a valid city\n");

			} else {

				guest.getAddress().setCity(city);

			}

		} while (city.equals(""));


		// Guess Address - State
		do {
			print("State: ");
			state = sc.nextLine();

			if (state.equals("")) {

				println("Please enter a valid state\n");

			} else {

				guest.getAddress().setState(state);

			}

		} while (state.equals(""));


		// Guest Address - Zip
		do {
			print("Zip Code: ");
			zip = sc.nextLine();

			if (zip.equals("")) {

				println("Please enter a valid zip code\n");

			} else {

				guest.getAddress().setZip(zip);

			}

		} while (state.equals(""));
		

		// Guest Country
		do {
			print("\nCountry: ");
			country = sc.nextLine();

			if (country.equals("") || !country.matches(alpha)) {

				println("Please enter a valid country");

			} else {

				guest.setCountry(country);

			}

		} while (country.equals("") || !country.matches(alpha));


		// Guest Identity
		println("\nIdentity");
		println("........... \n ");
		
		do {

			print("Identity Type - (1) Driving License (2) Passport: ");
			Identity ident = guest.new Identity();
			guest.setIdentity(ident);
			identityType = sc.nextInt();
			sc.nextLine();

			if (identityType != 1 && identityType !=2) {

				println("Please select a valid identity type\n");

			} else {

				switch (identityType) {

				case 1:
					print("Driving License: ");
					lic = sc.nextLine();
					guest.getIdentity().setLic(lic);
					break;

				case 2:
					print("Passport No.: ");
					pp = sc.nextLine();
					guest.getIdentity().setPp(pp);
					break;

				}
			}

		} while (identityType != 1 && identityType !=2);


		// Guest Nationality
		do {
			print("\nNationality: ");
			nationality = sc.nextLine();

			if (nationality.equals("") || !nationality.matches(alpha)) {

				println("Please enter a valid nationality");

			} else {

				guest.setNationality(nationality);

			}

		} while (nationality.equals("") || !nationality.matches(alpha));


		// Guest Contact
		do {
			print("\nContact: ");
			contact = sc.nextLine();

			if (contact.equals("") || !contact.matches(digit)) {

				println("Please enter a valid contact no.");

			} else {

				guest.setContact(contact);

			}

		} while (contact.equals("") || !contact.matches(digit));


		sc.close();

		ArrayList<Guest> alr = getGuest();
		alr.add(guest);

		try {
			// Write Guest records to file
			guestDB.saveGuest(filename, alr);

			System.out.println("New guest details has been successfully saved!");

		} catch (IOException e) {
			println("IOException > " + e.getMessage());
		}

	}


	public void updateGuest() {
		println("UPDATE GUEST");

		// Initialize attributes
		String name = "", cardNum = "", cvv = "", exp = "", add1 = "", add2 = "", city = "", state = "", zip = "",
				country = "", lic = "", pp = "", nationality = "", contact = "";
		int genderType = 0, cardType = 0, identityType = 0;
		int updateType = 0;

		// Prompt user for guest details
		Guest guest = null;
		print("Please select identity type - (1) Driving License (2) Passport");
		identityType = sc.nextInt();

		if (identityType == 1) {
			print("Please enter driving license: ");
			lic = sc.nextLine();

			ArrayList<Guest> alr = getGuest();

			for (int i = 0; i <= alr.size(); i++) {
				Guest updateguest = (Guest) alr.get(i);

				if (lic.equals(updateguest.getIdentity().getLic())) {

					// UPDATE OPERATIONS HERE

					print("Please select guest details to update - (1) Name (2) Gender (3) Credit Card (4) Address (5) Country (6) Identity (7) Nationality (8) Contact No.: ");
					updateType = sc.nextInt();
					switch (updateType) {

					case 1:

						do {
							print("Please enter name to be updated: ");
							name = sc.nextLine();
							if (name.equals("")) {
								println("Please enter a valid name.");
							} else {
								guest.setName(name);
							}
						} while (name.equals(""));

						println("Name has been successfully updated!");
						break;

					case 2:

						pp = sc.nextLine();
						guest.getIdentity().setPp(pp);
						break;

					default:
						println("Oops, error...");
					}

					break;
				}
			}

		} else {
			print("Please enter passport number: ");
			pp = sc.nextLine();
		}

	}

	public ArrayList<Guest> getGuest() {
		ArrayList<Guest> alr = null;
		try {
			// read file containing Guest records
			alr = guestDB.readGuest(filename);

		} catch (IOException e) {
			System.out.println("IOException > " + e.getMessage());
		}
		return alr;
	}

	public Guest searchGuest(String pp) {
		println("SEARCH GUEST");

		ArrayList<Guest> alr = getGuest();

		for (int i = 0; i <= alr.size(); i++) {
			Guest searchguestpp = (Guest) alr.get(i);

			if (searchguestpp.getIdentity().getPp().equals(pp)) {
				return searchguestpp;
			}
		}

		return null;

	}

	private void println(String output) {
		System.out.println(output);
	}

	private void print(String output) {
		System.out.print(output);
	}
}

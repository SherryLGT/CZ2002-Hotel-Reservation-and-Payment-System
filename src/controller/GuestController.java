package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

			if (cvv.equals("")) {

				println("Please enter a valid credit card CVV\n");

			} else {

				guest.getCard().setCvv(cvv);

			}

		} while (cvv.equals(""));

		// !!! Need help for validation. For the while loop, unable to input
		// "inputdate.before(todaysdate)" cos of the variables' placing? !!!
		// Guest Credit Card - Exp Date
		do {
			print("EXP (MM/YY): ");
			exp = sc.nextLine();

			SimpleDateFormat sdf = new SimpleDateFormat("MM/yy");
			sdf.setLenient(false);
			Date todaysdate = new Date();
			Date inputdate = null;

			try {
				inputdate = sdf.parse(exp);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			if (exp == "" || inputdate.before(todaysdate)) {
				println("Please enter a valid credit card expiration date\n");

			} else {

				guest.getCard().setExp(exp);

			}
		} while (exp == "");

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

			if (identityType != 1 && identityType != 2) {

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

		} while (identityType != 1 && identityType != 2);

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

			if (contact.equals("")) {

				println("Please enter a valid contact no.");

			} else {

				guest.setContact(contact);

			}

		} while (contact.equals(""));

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
		println("\n------------ Update Guest ------------");

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
		String lic = "null";
		String pp = "null";
		String nationality = "";
		String contact = "";
		int genderType = 0;
		int cardType = 0;
		int identityType = 0;
		int updateType = 0;
		int updateCardType = 0;
		int updateAddType = 0;

		// To be used for data validation
		String digit = "\\d+";
		String alpha = "[a-zA-Z.*\\s+.]+";

		// Prompt user for guest details
		Guest guest = null;

		ArrayList<Guest> alr = getGuest();

		do {

			print("Please select identity type - (1) Driving License (2) Passport: ");
			identityType = sc.nextInt();
			sc.nextLine();

			if (identityType == 1) {
				for (int i = 0; i < alr.size(); i++) {
					Guest updateguest = (Guest) alr.get(i);	

					do {

						print("Please enter driving license: ");
						lic = sc.nextLine();

						if (!lic.equals(updateguest.getIdentity().getLic())) {
							println("\nError - Please enter a valid driving license");
							break;
						}
						
						else {
							break;
						}

					} while (!lic.equals(updateguest.getIdentity().getLic()));
					break;
				}

			}

			else if (identityType == 2) {
				for (int i = 0; i < alr.size(); i++) {
					Guest updateguest = (Guest) alr.get(i);	

					do {

						print("Please enter passport no.: ");
						pp = sc.nextLine();

						if (!pp.equals(updateguest.getIdentity().getPp())) {
							println("\nError - Please enter a valid passport no.");
							break;
						}
						
						else {
							break;
						}

					} while (!pp.equals(updateguest.getIdentity().getPp()));
					break;
				}

			}

			else
			{
				println("Error - Please select a valid identity type\n");
			}

		} while (identityType != 1 && identityType != 2);



			for (int i = 0; i < alr.size(); i++) {
				Guest updateguest = (Guest) alr.get(i);			
				
				if (lic.equals(updateguest.getIdentity().getLic()) && pp.equals(updateguest.getIdentity().getPp())) {

					print("\nPlease select guest details to update - \n(1) Name (2) Gender (3) Credit Card (4) Address (5) Country (6) Identity (7) Nationality (8) Contact No.: ");
					updateType = sc.nextInt();
					sc.nextLine();

					switch (updateType) {


					case 1:
						// Guest Name
						do {
							print("\nNew Guest Name: ");
							name = sc.nextLine();

							if (name.equals("") || !name.matches(alpha)) {

								println("Please enter a valid name to be updated");

							} 

							else if ((name.equals(updateguest.getName()))) {

								println("Error - Current name and new name is the same");

							} 

							else {

								updateguest.setName(name);
								break;

							}

						} while (name.equals("") || (name.equals(updateguest.getName())) || !name.matches(alpha));	
						break;


					case 2:
						// Guest Gender
						do {
							println("\nNew Guest Gender: ");
							print("(1) Male (2) Female ");

							genderType = sc.nextInt();
							sc.nextLine();

							if ((genderType != 1 && genderType != 2)) {

								println("Please enter a valid gender to be updated");

							} 

							else if ((genderType == 1 && (updateguest.getGender().equals("Male"))) || (genderType == 2 && (updateguest.getGender().equals("Female")))) {

								println("Error - Current gender and new gender is the same");

							} 

							else {

								switch (genderType) {

								case 1:
									updateguest.setGender("Male");
									break;

								case 2:
									updateguest.setGender("Female");
									break;

								}
								break;
							}

						} while ((genderType != 1 && genderType != 2) || (genderType == 1 && (updateguest.getGender().equals("Male"))) || (genderType == 2 && (updateguest.getGender().equals("Female"))));	
						break;


					case 3:
						// Guest Credit Card
						do {
							println("\nUpdate Credit Card Details - \n(1) Card Type (2) Card No. (3) Card CVV (4) Card Exp:  ");

							CreditCard cc = updateguest.getCard();
							updateguest.setCard(cc);
							updateCardType = sc.nextInt();
							sc.nextLine();

							if (updateCardType != 1 && updateCardType != 2 && updateCardType != 3 && updateCardType != 4) {

								println("Please select a valid credit card detail to be updated");

							} else {

								switch (updateCardType) {


								case 1:
									// Guest Credit Card - Type
									do {
										print("\nNew Credit Card Type - (1) Visa (2) Master (3) Amex: ");
										cardType = sc.nextInt();
										sc.nextLine();

										if (cardType != 1 && cardType != 2 && cardType != 3) {

											println("Please enter a valid credit card type to be updated");

										} 

										else if ((cardType == 1 && (updateguest.getCard().getType().equals("Visa")) || (cardType == 2 && (updateguest.getCard().getType().equals("Master")) || (cardType == 3 && (updateguest.getCard().getType().equals("Amex")))))) {

											println("Error - Current credit card type and new credit card type is the same");

										} 

										else {

											switch (cardType) {

											case 1:
												updateguest.getCard().setType("Visa");
												break;

											case 2:
												updateguest.getCard().setType("Master");
												break;

											case 3:
												updateguest.getCard().setType("Amex");
												break;

											}
											break;
										}

									} while ((cardType != 1 && cardType != 2 && cardType != 3) || (cardType == 1 && (updateguest.getCard().getType().equals("Visa")) || (cardType == 2 && (updateguest.getCard().getType().equals("Master")) || (cardType == 3 && (updateguest.getCard().getType().equals("Amex"))))));	
									break;


								case 2:
									// Guest Credit Card - No.
									do {
										print("\nNew Credit Card Number: ");
										cardNum = sc.nextLine();

										if (cardNum.equals("") || !cardNum.matches(digit)) {

											println("Please enter a valid credit card number to be updated");

										} 

										else if ((cardNum.equals(updateguest.getCard().getNum()))) {

											println("Error - Current credit card number and new credit card number is the same");

										} 

										else {

											updateguest.getCard().setNum(cardNum);
											break;

										}

									} while (cardNum.equals("") || !cardNum.matches(digit) || (cardNum.equals(updateguest.getCard().getNum())));	
									break;


								case 3:
									// Guest Credit Card - CVV
									do {
										print("\nNew Credit Card CVV: ");
										cvv = sc.nextLine();

										if (cvv.equals("")) {

											println("Please enter a valid credit card CVV to be updated");

										} 

										else if ((cvv.equals(updateguest.getCard().getCvv()))) {

											println("Error - Current credit card CVV and new credit card CVV is the same");

										} 

										else {

											updateguest.getCard().setCvv(cvv);
											break;

										}

									} while (cvv.equals("")|| (cvv.equals(updateguest.getCard().getCvv())));	
									break;


								case 4:
									// !!! Need help for validation. For the while
									// loop, unable to input
									// "inputdate.before(todaysdate)" cos of the
									// variables' placing? !!!
									// Guest Credit Card - Exp Date
									do {
										print("\nNew Credit Card Exp (MM/YY): ");
										exp = sc.nextLine();

										SimpleDateFormat sdf = new SimpleDateFormat("MM/yy");
										sdf.setLenient(false);
										Date todaysdate = new Date();
										Date inputdate = null;

										try {
											inputdate = sdf.parse(exp);
										} catch (ParseException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}

										if (exp == "" || inputdate.before(todaysdate)) {

											println("Please enter a valid credit card exp date to be updated");

										} 

										else if ((exp.equals(updateguest.getCard().getExp()))) {

											println("Error - Current credit card exp date and new credit card exp date is the same");

										} 

										else {

											updateguest.getCard().setExp(exp);
											break;

										}

									} while (exp == "" || (exp.equals(updateguest.getCard().getExp())) );	
									break;

								}

							}

						} while (updateCardType != 1 && updateCardType != 2 && updateCardType != 3 && updateCardType != 4);
						break;


					case 4:
						// Guest Address
						do {
							print("\nUpdate Address - \n(1) Address Line 1 (2) Address Line 2 (3) City (4) State (5) Zip Code: ");

							Address add = updateguest.getAddress();
							updateguest.setAddress(add);
							updateAddType = sc.nextInt();
							sc.nextLine();

							if (updateAddType != 1 && updateAddType != 2 && updateAddType != 3 && updateAddType != 4
									&& updateAddType != 5) {

								println("Please enter a valid address detail to be updated");

							} else {

								switch (updateAddType) {


								case 1:
									// Guest Address - Line 1
									do {
										print("\nNew Address Line 1: ");
										add1 = sc.nextLine();

										if (add1.equals("")) {

											println("Please enter a valid address line 1 to be updated");

										} 

										else if ((add1.equals(updateguest.getAddress().getAdd1()))) {

											println("Error - Current address line 1 and new address line 1 is the same");

										} 

										else {

											updateguest.getAddress().setAdd1(add1);
											break;

										}

									} while (add1.equals("") || (add1.equals(updateguest.getAddress().getAdd1())));	
									break;


								case 2:
									// Guest Address - Line 2	
									do {
										print("\nNew Address Line 2: ");
										add2 = sc.nextLine();

										if (add2.equals("")) {

											println("Please enter a valid address line 2 to be updated");

										} 

										else if ((add2.equals(updateguest.getAddress().getAdd2()))) {

											println("Error - Current address line 2 and new address line 2 is the same");

										} 

										else {

											updateguest.getAddress().setAdd2(add2);
											break;

										}

									} while (add2.equals("") || (add2.equals(updateguest.getAddress().getAdd2())));	
									break;


								case 3:
									// Guest Address - City	
									do {
										print("\nNew City: ");
										city = sc.nextLine();

										if (city.equals("")) {

											println("Please enter a valid city to be updated");

										} 

										else if ((city.equals(updateguest.getAddress().getCity()))) {

											println("Error - Current city and new city is the same");

										} 

										else {

											updateguest.getAddress().setCity(city);
											break;

										}

									} while (city.equals("") || (city.equals(updateguest.getAddress().getCity())));	
									break;


								case 4:
									// Guess Address - State		
									do {
										print("\nNew State: ");
										state = sc.nextLine();

										if (state.equals("")) {

											println("Please enter a valid state to be updated");

										} 

										else if ((state.equals(updateguest.getAddress().getState()))) {

											println("Error - Current state and new state is the same");

										} 

										else {

											updateguest.getAddress().setState(state);
											break;

										}

									} while (state.equals("") || (state.equals(updateguest.getAddress().getState())));	
									break;


								case 5:
									// Guest Address - Zip
									do {
										print("\nNew Zip Code: ");
										zip = sc.nextLine();

										if (zip.equals("")) {

											println("Please enter a valid zip code to be updated");

										} 

										else if ((zip.equals(updateguest.getAddress().getZip()))) {

											println("Error - Current zip code and new zip code is the same");

										} 

										else {

											updateguest.getAddress().setZip(zip);
											break;

										}

									} while (zip.equals("") || (zip.equals(updateguest.getAddress().getZip())));	
									break;

								}

							}

						} while (updateAddType != 1 && updateAddType != 2 && updateAddType != 3 && updateAddType != 4
								&& updateAddType != 5);
						break;


					case 5:
						// Guest Country
						do {
							print("\nNew Country: ");
							country = sc.nextLine();

							if (country.equals("") || !country.matches(alpha)) {

								println("Please enter a valid country to be updated");

							} 

							else if ((country.equals(updateguest.getCountry()))) {

								println("Error - Current country and new country is the same");

							} 

							else {

								updateguest.setCountry(country);
								break;

							}

						} while (country.equals("") || !country.matches(alpha) || (country.equals(updateguest.getCountry())));	
						break;


					case 6:
						// Guest Identity
						do {

							print("\nUpdate identity type - (1) Driving License (2) Passport: ");
							Identity ident = updateguest.new Identity();
							updateguest.setIdentity(ident);
							identityType = sc.nextInt();
							sc.nextLine();

							if (identityType != 1 && identityType != 2) {

								println("Please select a valid identity type to be updated\n");

							} else {

								switch (identityType) {

								case 1:
									print("Driving License: ");
									lic = sc.nextLine();
									updateguest.getIdentity().setLic(lic);
									break;

								case 2:
									print("Passport No.: ");
									pp = sc.nextLine();
									updateguest.getIdentity().setPp(pp);
									break;

								}
							}

						} while (identityType != 1 && identityType != 2);
						break;


					case 7:
						// Guest Nationality		
						do {
							print("\nNew Nationality: ");
							nationality = sc.nextLine();

							if (nationality.equals("") || !nationality.matches(alpha)) {

								println("Please enter a valid nationality to be updated");

							} 

							else if ((nationality.equals(updateguest.getNationality()))) {

								println("Error - Current nationality and new nationality is the same");

							} 

							else {

								updateguest.setNationality(nationality);
								break;

							}

						} while (nationality.equals("") || !nationality.matches(alpha) || (nationality.equals(updateguest.getNationality())));	
						break;


					case 8:
						// Guest Contact
						do {
							print("\nNew Contact No.: ");
							contact = sc.nextLine();

							if (contact.equals("")) {

								println("Please enter a valid contact no. to be updated");

							} 

							else if ((contact.equals(updateguest.getContact()))) {

								println("Error - Current contact no. and new contact no. is the same");

							} 

							else {

								updateguest.setContact(contact);
								break;

							}

						} while (contact.equals("") || (contact.equals(updateguest.getContact())));	
						break;

					}

					break;
				}


			}

			sc.close();

			try {
				// Write Guest records to file
				guestDB.saveGuest(filename, alr);

				System.out.println("Guest details has been successfully updated!");

			} catch (IOException e) {
				println("IOException > " + e.getMessage());
			}


		}

	
	
	public void searchGuest(String[] args) {
		println("\n------------ Search Guest ------------");
		
		// Initialize attributes
		String lic = "null";
		String pp = "null";
		int identityType = 0;


		// To be used for data validation
		String digit = "\\d+";
		String alpha = "[a-zA-Z.*\\s+.]+";
		
		// Prompt user for guest details
		Guest guest = null;
		ArrayList<Guest> alr = getGuest();		
		
		do {

			print("Please select identity type - (1) Driving License (2) Passport: ");
			identityType = sc.nextInt();
			sc.nextLine();

			do {

				print("Please select identity type - (1) Driving License (2) Passport: ");
				identityType = sc.nextInt();
				sc.nextLine();

				if (identityType == 1) {
					for (int i = 0; i < alr.size(); i++) {
						Guest searchguest = (Guest) alr.get(i);	

						do {

							print("Please enter driving license: ");
							lic = sc.nextLine();

							if (!lic.equals(updateguest.getIdentity().getLic())) {
								println("\nError - Please enter a valid driving license");
								break;
							}
							
							else {
								break;
							}

						} while (!lic.equals(updateguest.getIdentity().getLic()));
						break;
					}

				}

				else if (identityType == 2) {
					for (int i = 0; i < alr.size(); i++) {
						Guest searchguest = (Guest) alr.get(i);	

						do {

							print("Please enter passport no.: ");
							pp = sc.nextLine();

							if (!pp.equals(updateguest.getIdentity().getPp())) {
								println("\nError - Please enter a valid passport no.");
								break;
							}
							
							else {
								break;
							}

						} while (!pp.equals(updateguest.getIdentity().getPp()));
						break;
					}

				}

				else
				{
					println("Error - Please select a valid identity type\n");
				}

			} while (identityType != 1 && identityType != 2);
		
			if (lic.equals(searchguest.getIdentity().getLic()) && pp.equals(searchguest.getIdentity().getPp())) {
							
				for (int i = 0; i < alr.size(); i++) {
					Guest searchguest = (Guest) alr.get(i);
					println("Name is: " + searchguest.getName());
					println("Gender is: " + searchguest.getGender());
					println("Address is: " +searchguest.getAddress().getAdd1());
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



	private void println(String output) {
		System.out.println(output);
	}



	private void print(String output) {
		System.out.print(output);
	}
}

package ui;
import java.util.InputMismatchException;
import java.util.Scanner;

import controller.PaymentController;
import controller.ReservationController;

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
	 * \n new line character
	 * \t is a tab
	 * \b backspace
	 * \f form feed in text at that point
	 * \r carriage return
	 * \' single quote character
	 * \" double quote character
	 * \\ backslash character
	 */
	
	public static void main(String[] aArgs)  {
		Scanner sc = new Scanner(System.in);
		int option = -1;
		ReservationController reservationControl = new ReservationController();
		PaymentController paymentControl = new PaymentController();
		
		System.out.println("=================================\n|\t WELCOME TO HRPS \t|\n=================================");
		System.out.println("| Options: \t\t\t|");
		System.out.println("|\t1. Check In\t\t|");
		System.out.println("|\t2. Make Reservation\t|");
		System.out.println("|\t3. Room Service\t\t|");
		System.out.println("|\t4. Payment\t\t|");
		System.out.println("|\t5. Search Guest\t\t|");
		System.out.println("|\t6. Occupancy Report\t|");
		System.out.println("|\t7. Exit\t\t\t|");
		System.out.println("=================================");
		
		do {
			System.out.print("Select an option: ");
			try {
				option = sc.nextInt();
				if (option < 1 || option > 7) {
					System.out.println("You have not selected option between 1-7. Please try again.");
					continue;
				}
				else
					break;
			} catch(InputMismatchException e) {
				System.out.println("You have entered an invalid input. Please try again.");
				sc.next();
				continue;
			}
		} while(option < 1 || option > 6);
		
		switch(option) {
			case 1:
				reservationControl.checkIn();
				break;
			case 2:
				reservationControl.createReservation();
				break;
			case 3:
				System.out.print("Room Service");
				break;
			case 4:
				paymentControl.createPayment();
				reservationControl.checkOut();
				break;
			case 5:
				System.out.print("Search Guest");
				break;
			case 6:
				System.out.print("Occupancy Report");
				break;
			case 7:
				System.out.print("Thank you for using HRPS!");
				System.exit(0);
				break;
		}
		
//    	TextDB txtDB = new TextDB();
//    	String filename = "professor.txt" ;
//		try {
//			// read file containing Professor records.
//			ArrayList al = TextDB.readProfessors(filename) ;
//			for (int i = 0 ; i < al.size() ; i++) {
//					Professor prof = (Professor)al.get(i);
//					System.out.println("Name " + prof.getName() );
//					System.out.println("Contact " + prof.getContact() );
//			}
//			Professor p1 = new Professor("Joseph","jos@ntu.edu.sg",67909999);
//			// al is an array list containing Professor objs
//			al.add(p1);
//			// write Professor record/s to file.
//			TextDB.saveProfessors(filename, al);
//		}catch (IOException e) {
//			System.out.println("IOException > " + e.getMessage());
//		}
  }
}

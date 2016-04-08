package controller;
 
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
 
import database.PaymentDB;
import entity.Guest;
import entity.Guest.Identity;
import entity.Payment;
import entity.Reservation;
import entity.Room;
import entity.RoomService;
import ui.HRPSApp;
 
public class PaymentController {
    private GuestController guestControl = new GuestController();
    private ReservationController reservControl = new ReservationController();
    private RoomController roomControl = new RoomController();
    private RoomServiceController rsControl = new RoomServiceController();
    
    private PaymentDB paymentDB = new PaymentDB();
    private String filename = "payment.txt";
    
    Calendar c = Calendar.getInstance();
    DecimalFormat df = new DecimalFormat(".##");
    DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    
    public void createPayment() {
    	Guest guest = new Guest();
        Reservation reservation = new Reservation();
        Room room = new Room();
        RoomService roomService = new RoomService();

        double charges = 0;
        int daysStayed = 0;
        final double TAXRATE = 0.18; // 10% - Service Charge, 7% - GST, 1% - Government Tax
        final double GST = 0.07;
        double tax = 0;
        double discount = 0;
        double total = 0;
 
        Scanner sc = new Scanner(System.in);

        ArrayList rooms = roomControl.getRoom();
        ArrayList<Room> occupiedRooms = new ArrayList<Room>();
        int count = 0;
        System.out.print("Checked-In rooms: ");
        for(int i = 0; i < rooms.size(); i++) {
        	Room rm = (Room) rooms.get(i);
        	if(rm.getStatus().equals("Occupied")) {
        		occupiedRooms.add(rm);
        		count++;
        		
        		if(count > 1)
        			System.out.print(", " + rm.getRoomNo());
        		else
        			System.out.print(rm.getRoomNo());
        	}
        }
        
        String roomNo;
        boolean valid = false;
    	do {
    		System.out.print("\nEnter room number: ");
    		roomNo = sc.nextLine();
    		for(int index = 0; index < occupiedRooms.size(); index++) {
    			if(occupiedRooms.get(index).getRoomNo().equals(roomNo)) {
        			room = occupiedRooms.get(index);
        			valid = true;
    			}
    		}
    		if (!valid)
    			System.out.println("Invalid room no. Please try again.");
    	} while(!valid);
		
        reservation = reservControl.searchReservationByRoom(room);
//        room = roomControl.searchRoom(reservation.getRoom());
        
		Identity ident = guest.new Identity();
		ident.setLic(reservation.getGuest().getIdentity().getLic());
		ident.setPp(reservation.getGuest().getIdentity().getPp());
		guest.setIdentity(ident);
        guest = guestControl.searchGuest(guest);
        
        daysStayed = (int) ((reservation.getCheckOut().getTime() - reservation.getCheckIn().getTime()) / (24 * 60 * 60 * 1000));
        charges = calculateCharges(reservation, room, daysStayed);
        
        tax = charges * GST;
        tax = Double.valueOf(df.format(tax));
        
//		TODO roomService
         
        System.out.print("Enter any discount (%): ");
        discount = sc.nextDouble();
        
        total = charges + tax - ((charges + tax) * (discount / 100));
		
        Payment payment = new Payment(reservation, charges, tax, roomService, discount, total, new Date());
         
        try {
            ArrayList al = paymentDB.readPayment(filename);
            for (int i = 0; i < al.size(); i++) {
                Payment payments = (Payment) al.get(i);
            }
            al.add(payment);
//            paymentDB.savePayment(filename, al);
            
            System.out.print("\nPayment made by " + guest.getName() + " by ");
            if(reservation.getBillType() == 1)
            	System.out.print("cash. ");
            else
            	System.out.println("card, " + guest.getCard().getType() + " " + guest.getCard().getNum() + " " + guest.getCard().getExp() +
            			"\nAddress: " + guest.getAddress().getAdd1() + " " + guest.getAddress().getAdd2() + " " + guest.getAddress().getCity() + " " + guest.getAddress().getState() + " " + guest.getAddress().getZip());
            System.out.println("Successful!");
            HRPSApp.header("BILL INVOICE", "*", 36);
            System.out.format("%1s %16s %23s %5s %n", "*", "Days of stay:", daysStayed, "*");
            System.out.format("%1s %28s %11s %5s %n", "*", "Room service order items:", 0, "*");
            System.out.format("%1s %31s %8s %5s %n", "*", "Total price of room service:", 0, "*");
            System.out.format("%1s %7s %32s %5s %n", "*", "Tax:", tax, "*");
            System.out.format("%1s %16s %23s %5s %n", "*", "Total amount:", total, "*");
            HRPSApp.line("*", 48);
            
//            reservControl.updateReservation(reservation, 3);
//            roomControl.updateRoom(room, 3);
        } catch (IOException e) {
            System.out.println("IOException > " + e.getMessage());
        }
    }
    
    private double calculateCharges(Reservation res, Room r, int days) {
    	double rate = 0;
    	double charges = 0;
    	double total = 0;
    	Date date = res.getCheckIn();
    	DateFormat df = new SimpleDateFormat("EEE dd MMM yyyy");
    	
		System.out.print("\nRoom: " + r.getRoomNo() + ", " + r.getType() + " (S$" + r.getPrice() + ")\n");
		HRPSApp.line("-", 41);
		System.out.format("%1s %4s %14s %17s %1s %n", "|", "DAY", "DATE", "CHARGE(S$)", "|");
		HRPSApp.line("-", 41);
		
		for (int i = 1; i <= days; i++) {
    		c.setTime(date);
    		int day = c.get(Calendar.DAY_OF_WEEK);
    		// Days of week indexed starting at 1
    		if(day > 1 && day < 7)
    			rate = 1; // Weekdays
    		else
    			rate = 1.2; // Weekends
    		
    		charges = r.getPrice() * rate;
    		System.out.format("%1s %3s %20s %9s %4s %n", "|", i, df.format(date), charges, "|");
    		
    		total += charges;
    		
    		c.add(Calendar.DATE, 1);
    		date = c.getTime();
    	}
		HRPSApp.line("=", 41);
		System.out.format("%1s %6s %18s %4s %n", "|", "TOTAL CHARGES: ", total, "|");
		HRPSApp.line("=", 41);
		
		return total;
	}
     
//    public ArrayList getPayment() {
//        ArrayList al = null;
//        try {
//            al = paymentDB.readPayment(filename);
//        } catch (IOException e) {
//            System.out.println("IOException > " + e.getMessage());
//        }
//        return al;
//    }
}
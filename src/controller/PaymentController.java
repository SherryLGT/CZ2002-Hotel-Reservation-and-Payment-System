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
import entity.Payment;
import entity.Reservation;
import entity.Room;
import entity.RoomService;
 
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
        double tax = 0;
        double TAXRATE = 0.18; // 10% - Service Charge, 7% - GST, 1% - Government Tax
        double discount = 0;
        double total = 0;
        int daysStayed = 0;
        Date date = new Date();
 
        Scanner sc = new Scanner(System.in);
         
        System.out.print("Enter guest ID: ");
        // guest = guestControl.searchGuest(sc.nextLine()); // pp
        reservation = reservControl.searchReservation("20160324012229");
        room = roomControl.searchRoom(reservation.getRoom().getRoomNo());
         
        c.setTime(reservation.getCheckOut());
        int day = c.get(Calendar.DAY_OF_WEEK);
        // Days of week indexed starting at 1
        if(day > 1 && day < 7)
            charges = 1; // Weekdays
        else
            charges = 1.2; // Weekends
         
        // roomService
         
        System.out.print("Enter any discount: ");
        // discount = sc.nextDouble();
         
        daysStayed = (int) ((reservation.getCheckOut().getTime() - reservation.getCheckIn().getTime()) / (24 * 60 * 60 * 1000));
         
        tax = (room.getPrice() * charges) * TAXRATE;
        tax = Double.valueOf(df.format(tax));
        total = (room.getPrice() * charges) + tax - discount;
         
        Payment payment = new Payment(reservation, charges, tax, roomService, discount, total, date);
         
        try {
            ArrayList al = paymentDB.readPayment(filename);
            for (int i = 0; i < al.size(); i++) {
                Payment payments = (Payment) al.get(i);
            }
            al.add(payment);
            paymentDB.savePayment(filename, al);
             
            System.out.println("\nPayment Successful!\n");
            System.out.println("****************** BILL INVOICE *****************");
            System.out.println("*\tDays of stay: " + daysStayed + "\t\t\t*");
            System.out.println("*\tRoom service order items: " + 0 + "\t\t*");
            System.out.println("*\tTotal price of room service: " + 0 + "\t\t*");
            System.out.println("*\tTax: " + tax + "\t\t\t\t*");
            System.out.println("*\tTotal amount: " + total + "\t\t\t*");
            System.out.println("*************************************************");
        } catch (IOException e) {
            System.out.println("IOException > " + e.getMessage());
        }
    }
     
    public ArrayList getPayment() {
        ArrayList al = null;
        try {
            al = paymentDB.readPayment(filename);
        } catch (IOException e) {
            System.out.println("IOException > " + e.getMessage());
        }
        return al;
    }
}
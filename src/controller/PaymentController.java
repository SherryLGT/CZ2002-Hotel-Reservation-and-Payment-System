package controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import database.PaymentDB;
import entity.Payment;
import entity.Reservation;
import entity.RoomService;

public class PaymentController {
	private PaymentDB paymentDB = new PaymentDB();
	private ReservationController reservationControl = new ReservationController();
	private String filename = "payment.txt";
	Calendar c = Calendar.getInstance();
	DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		
	public void createPayment() {
		Reservation reservation = new Reservation();
		double charges = 0;
		double tax = 0;
		double TAXRATE = 0.18; // 10% - Service Charge, 7% - GST, 1% - Government Tax
		RoomService roomService = new RoomService();
		double discount = 0;
		double total = 0;
		Date date = null;
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\nMake Payment\n-------------");
		System.out.print("Enter reservation ID: ");
		reservation = reservationControl.searchReservation(sc.nextLine());
		
		c.setTime(reservation.getCheckOut());
		int day = c.get(Calendar.DAY_OF_WEEK);
		// Days of week indexed starting at 1
		if(day > 1 && day < 7)
			charges = 1; // Weekdays
		else
			charges = 1.2; // Weekends
		
		//roomService
		
		System.out.println("Enter any discount: ");
		discount = sc.nextDouble();
		
		try {
			date = (Date) formatter.parse(formatter.format(new Date()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		//total
		
		Payment payment = new Payment(reservation, charges, tax, roomService, discount, total, date);
		
		try {
			ArrayList al = paymentDB.readPayment(filename);
			for (int i = 0; i < al.size(); i++) {
				Payment payments = (Payment) al.get(i);
			}
			al.add(payment);
			paymentDB.savePayment(filename, al);
			
			System.out.println("Bill Invoice");
			System.out.println("Days of stay: " + 0);
			System.out.println("Room service order items: " + 0);
			System.out.println("Total price of room service: " + 0);
			System.out.println("Tax: " + tax);
			System.out.println("Total amount: " + total);
		} catch (IOException e) {
			System.out.println("IOException > " + e.getMessage());
		}
	}
	
	public Payment getPayment(String reservationID) {
		Payment payment = new Payment();
		try {
			ArrayList al = paymentDB.readPayment(filename);
		} catch (IOException e) {
			System.out.println("IOException > " + e.getMessage());
		}
		return payment;
	}
}

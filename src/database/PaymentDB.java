package database;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import entity.Payment;
import entity.Reservation;
import entity.RoomService;

public class PaymentDB {
	private static final String SEPARATOR = "|";
	DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	
	public ArrayList readPayment(String filename) throws IOException {
		ArrayList stringArray = (ArrayList) read(filename);
		ArrayList alr = new ArrayList();
		
		for(int i = 0; i < stringArray.size(); i++) {
			String st = (String) stringArray.get(i);
			StringTokenizer star = new StringTokenizer(st, SEPARATOR);

			Date date = null;
			try {
				date = formatter.parse(star.nextToken().trim());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			Reservation reservation = new Reservation();
			reservation.setReservationID(star.nextToken().trim());
			double charges = (Double.parseDouble(star.nextToken().trim()));
			double tax = (Double.parseDouble(star.nextToken().trim()));
			//roomService
			RoomService roomService = new RoomService();
			//roomService.(star.nextToken().trim());
			double discount = (Double.parseDouble(star.nextToken().trim()));
			double total = (Double.parseDouble(star.nextToken().trim()));
			
			Payment payment = new Payment(reservation, charges, tax, roomService, discount, total, date);
			alr.add(payment);
		}
		return alr;
	}
	
	public void savePayment(String filename, List al) throws IOException {
		List alw = new ArrayList();
		
		for (int i = 0; i < al.size(); i++) {
			Payment payment = (Payment) al.get(i);
			StringBuilder st = new StringBuilder();
			st.append(formatter.format(payment.getDate()));
			st.append(SEPARATOR);
			st.append(payment.getReservation().getReservationID().trim());
			st.append(SEPARATOR);
			st.append(payment.getCharges());
			st.append(SEPARATOR);
			st.append(payment.getTax());
			st.append(SEPARATOR);
			//roomService
			st.append(SEPARATOR);
			st.append(payment.getDiscount());
			st.append(SEPARATOR);
			st.append(payment.getTotal());
			alw.add(st.toString());
		}
		write(filename, alw);
	}
	
	public void write(String fileName, List data) throws IOException {
		PrintWriter out = new PrintWriter(new FileWriter(fileName));

		try {
			for (int i = 0; i < data.size(); i++) {
				out.println((String) data.get(i));
			}
		} finally {
			out.close();
		}
	}
	
	public List read(String fileName) throws IOException {
		List data = new ArrayList();
		
	    File file = new File(".", fileName);

	    if (!file.isFile() && !file.createNewFile()) {
	        throw new IOException("Error creating new file: " + file.getAbsolutePath());
	    }
		Scanner scanner = new Scanner(new FileInputStream(fileName));
		try {
			while (scanner.hasNextLine()) {
				data.add(scanner.nextLine());
			}
		} finally {
			scanner.close();
		}
		return data;
	}
}

package database;
 
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import entity.Payment;
import entity.Reservation;
 
public class PaymentDB {
    private static final String SEPARATOR = "|";
     
    public ArrayList readPayment(String filename) throws IOException {
    	
    	DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    	
    	// Read String from text file
        ArrayList stringArray = (ArrayList) UtilityDB.read(filename);
        ArrayList alr = new ArrayList(); // To store Reservation data
         
        for(int i = 0; i < stringArray.size(); i++) {
            String st = (String) stringArray.get(i);

            // Get individual 'fields' of the string separated by SEPARATOR
			StringTokenizer star = new StringTokenizer(st, SEPARATOR); // Pass in the string to the string tokenizer using delimiter ","
 
            Date date = null;
            try {
                date = formatter.parse(star.nextToken().trim());
            } catch (ParseException e) {

            }

            // Reservation ID
            Reservation reservation = new Reservation();
            reservation.setReservationID(star.nextToken().trim());
            
            double charges = (Double.parseDouble(star.nextToken().trim()));
            double tax = (Double.parseDouble(star.nextToken().trim()));
           
            double discount = (Double.parseDouble(star.nextToken().trim()));
            double total = (Double.parseDouble(star.nextToken().trim()));
             
            // Create payment object from file data
            Payment payment = new Payment(reservation, charges, tax, discount, total, date);
            
            // Add to payment list
            alr.add(payment);
        }
        return alr;
    }
     
    public void savePayment(String filename, List al) throws IOException {
    	
    	DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    	
    	List alw = new ArrayList();
         
        for (int i = 0; i < al.size(); i++) {
            Payment payment = (Payment) al.get(i);
            StringBuilder st = new StringBuilder();
           
            st.append(formatter.format(payment.getDate()));
            st.append(SEPARATOR);
            
            // Reservation ID
            st.append(payment.getReservation().getReservationID().trim());
            
            st.append(SEPARATOR);
            st.append(payment.getCharges());
            st.append(SEPARATOR);
            st.append(payment.getTax());
            st.append(SEPARATOR);
            
            // Room Service
            st.append(SEPARATOR);
            st.append(payment.getDiscount());
            st.append(SEPARATOR);
            st.append(payment.getTotal());
            
            alw.add(st.toString());
        }
        UtilityDB.write(filename, alw);
    }
}
package database;
 
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
 
import entity.Guest;
import entity.Payment;
import entity.Reservation;
import entity.Room;
 
public class ReservationDB {
    private static final String SEPARATOR = "|";
    private DateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
 
    public ArrayList readReservation(String filename) throws IOException {
        // read String from text file
        ArrayList stringArray = (ArrayList) UtilityDB.read(filename);
        ArrayList alr = new ArrayList();// to store Reservation data
 
        for (int i = 0; i < stringArray.size(); i++) {
            String st = (String) stringArray.get(i);
            // get individual 'fields' of the string separated by SEPARATOR
            StringTokenizer star = new StringTokenizer(st, SEPARATOR); // pass in the string to the string tokenizer using delimiter ","
             
            String reservationID = star.nextToken().trim();
            Guest guest = new Guest();
            guest.setName(star.nextToken().trim());
            Room room = new Room();
            room.setRoomNo(Integer.parseInt(star.nextToken()));
            int billType = Integer.parseInt(star.nextToken().trim());
            Date checkIn = null;
            try {
                checkIn = (Date) formatter.parse(star.nextToken().trim());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Date checkOut = null;
            try {
                checkOut = (Date) formatter.parse(star.nextToken().trim());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            int numAdult = Integer.parseInt(star.nextToken().trim());
            int numChild = Integer.parseInt(star.nextToken().trim());
            String status = star.nextToken().trim();
 
            // create reserv object from file data
            Reservation reserv = new Reservation(reservationID, guest, room, billType, checkIn,
                    checkOut, numAdult, numChild, status);
             
            // add to Reservation list
            alr.add(reserv);
        }
        return alr;
    }
 
    public void saveReservation(String filename, List al) throws IOException {
        List alw = new ArrayList();// to store Reservation data
 
        for (int i = 0; i < al.size(); i++) {
            Reservation reserv = (Reservation) al.get(i);
            StringBuilder st = new StringBuilder();
            st.append(reserv.getReservationID().trim());
            st.append(SEPARATOR);
            st.append(reserv.getGuest().getName().trim());
            st.append(SEPARATOR);
            st.append(reserv.getRoom().getRoomNo());
            st.append(SEPARATOR);
            st.append(reserv.getBillType());
            st.append(SEPARATOR);
            st.append(formatter.format(reserv.getCheckIn()));
            st.append(SEPARATOR);
            st.append(formatter.format(reserv.getCheckOut()));
            st.append(SEPARATOR);
            st.append(reserv.getNumAdult());
            st.append(SEPARATOR);
            st.append(reserv.getNumChild());
            st.append(SEPARATOR);
            st.append(reserv.getStatus());
            alw.add(st.toString());
        }
        UtilityDB.write(filename, alw);
    }
}
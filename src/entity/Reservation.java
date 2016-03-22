package entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Represents the total billType for payment for the guest
 * 
 * @author Toh Ling Li Geraldine
 * @version 1.0
 * @since 2016-03-22
 */

public class Reservation implements Serializable {
	private String reservationID;
	private Guest guest;
	private Room room;
	private Payment payment;
	private int billType;
	private Date checkIn;
	private Date checkOut;
	private int numAdult;
	private int numChild;
	private String status;

	public Reservation() {

	}

	public Reservation(String reservationID, Guest guest, Room room, int billType, Date checkIn, Date checkOut,
			int numAdult, int numChild, String status) {
		super();
		this.reservationID = reservationID;
		this.guest = guest;
		this.room = room;
		this.payment = new Payment();
		payment.setCharges(0.0);
		this.billType = billType;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.numAdult = numAdult;
		this.numChild = numChild;
		this.status = status;
	}
	
	public Reservation(String reservationID, Guest guest, Room room, Payment payment, int billType, Date checkIn,
			Date checkOut, int numAdult, int numChild, String status) {
		super();
		this.reservationID = reservationID;
		this.guest = guest;
		this.room = room;
		this.payment = payment;
		this.billType = billType;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.numAdult = numAdult;
		this.numChild = numChild;
		this.status = status;
	}

	public String getReservationID() {
		return reservationID;
	}

	public void setReservationID(String reservationID) {
		this.reservationID = reservationID;
	}

	public Guest getGuest() {
		return guest;
	}

	public void setGuest(Guest guest) {
		this.guest = guest;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public int getBillType() {
		return billType;
	}

	public void setBillType(int billType) {
		this.billType = billType;
	}

	public Date getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(Date checkIn) {
		this.checkIn = checkIn;
	}

	public Date getCheckOut() {
		return checkOut;
	}

	public void setCheckOut(Date checkOut) {
		this.checkOut = checkOut;
	}

	public int getNumAdult() {
		return numAdult;
	}

	public void setNumAdult(int numAdult) {
		this.numAdult = numAdult;
	}

	public int getNumChild() {
		return numChild;
	}

	public void setNumChild(int numChild) {
		this.numChild = numChild;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}

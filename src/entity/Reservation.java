package entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Represents the room reservation
 * 
 * @author Toh Ling Li Geraldine
 * @version 1.0
 * @since 2016-03-22
 */

public class Reservation implements Serializable {
	private String reservationID;
	private Guest guest;
	private Room room;
	private int billType;
	private Date checkIn;
	private Date checkOut;
	private int numAdult;
	private int numChild;
	private String status;

	/**
	 * Default constructor
	 */
	public Reservation() {

	}

	/**
	 * Creates a new reservation with the room and guest details. With the
	 * addition of reservation id, bill type, check in date, check out date,
	 * number of adults, number of children taken into consideration.
	 * 
	 * @param reservationID
	 *            This reservation's id.
	 * @param guest
	 *            This reservation's guest details.
	 * @param room
	 *            This reservation's room details.
	 * @param billType
	 *            This reservation's bill type.
	 * @param checkIn
	 *            This reservation's check in date.
	 * @param checkOut
	 *            This reservation's check out date.
	 * @param numAdult
	 *            This reservation's number of adults.
	 * @param numChild
	 *            This reservation's number of children.
	 * @param status
	 *            This reservation's status.
	 */
	public Reservation(String reservationID, Guest guest, Room room, int billType, Date checkIn, Date checkOut,
			int numAdult, int numChild, String status) {
		super();
		this.reservationID = reservationID;
		this.guest = guest;
		this.room = room;
		this.billType = billType;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.numAdult = numAdult;
		this.numChild = numChild;
		this.status = status;
	}

	/**
	 * Gets the id of this reservation.
	 * 
	 * @return this reservation's id.
	 */
	public String getReservationID() {
		return reservationID;
	}

	/**
	 * Changes the id of this room.
	 * 
	 * @param reservationID
	 *            This reservation's id.
	 */
	public void setReservationID(String reservationID) {
		this.reservationID = reservationID;
	}

	/**
	 * Gets the guest details of this reservation.
	 * 
	 * @return this reservation's guest details.
	 */
	public Guest getGuest() {
		return guest;
	}

	/**
	 * Changes the guest details of this room.
	 * 
	 * @param guest
	 *            This reservation's guest details.
	 */
	public void setGuest(Guest guest) {
		this.guest = guest;
	}

	/**
	 * Gets the room details of this reservation.
	 * 
	 * @return this reservation's room details.
	 */
	public Room getRoom() {
		return room;
	}

	/**
	 * Changes the room details of this room.
	 * 
	 * @param room
	 *            This reservation's room details.
	 */
	public void setRoom(Room room) {
		this.room = room;
	}

	/**
	 * Gets the bill type of this reservation.
	 * 
	 * @return this reservation's bill type.
	 */
	public int getBillType() {
		return billType;
	}

	/**
	 * Changes the bill type of this room.
	 * 
	 * @param billType
	 *            This reservation's bill type.
	 */
	public void setBillType(int billType) {
		this.billType = billType;
	}

	/**
	 * Gets the check in date of this reservation.
	 * 
	 * @return this reservation's check in date.
	 */
	public Date getCheckIn() {
		return checkIn;
	}

	/**
	 * Changes the this reservation's bill type of this room.
	 * 
	 * @param checkIn
	 *            This reservation's check in date.
	 */
	public void setCheckIn(Date checkIn) {
		this.checkIn = checkIn;
	}

	/**
	 * Gets the check out date of this reservation.
	 * 
	 * @return this reservation's check out date.
	 */
	public Date getCheckOut() {
		return checkOut;
	}

	/**
	 * Changes the check out date of this room.
	 * 
	 * @param checkOut
	 *            This reservation's check out date.
	 */
	public void setCheckOut(Date checkOut) {
		this.checkOut = checkOut;
	}

	/**
	 * Gets the number of adults of this reservation.
	 * 
	 * @return this reservation's number of adults.
	 */
	public int getNumAdult() {
		return numAdult;
	}

	/**
	 * Changes the number of adults of this room.
	 * 
	 * @param numAdult
	 *            This reservation's number of adults.
	 */
	public void setNumAdult(int numAdult) {
		this.numAdult = numAdult;
	}

	/**
	 * Gets the number of children of this reservation.
	 * 
	 * @return this reservation's number of children.
	 */
	public int getNumChild() {
		return numChild;
	}

	/**
	 * Changes the number of children of this room.
	 * 
	 * @param numChild
	 *            This reservation's number of children.
	 */
	public void setNumChild(int numChild) {
		this.numChild = numChild;
	}

	/**
	 * Gets the status of this reservation.
	 * 
	 * @return this reservation's status.
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Changes the status of this room.
	 * 
	 * @param status
	 *            This reservation's status.
	 */
	public void setStatus(String status) {
		this.status = status;
	}

}
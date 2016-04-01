package entity;

import java.io.Serializable;

/**
 * Represents the room information
 * 
 * @author Toh Ling Li Geraldine
 * @version 1.0
 * @since 2016-04-01
 */

public class Room implements Serializable {
	private String roomNo;
	private String type;
	private double price;
	private String status;
	private String[] details;

	/**
	 * Default constructor
	 */
	public Room() {
	}

	/**
	 * Create a new room with room number, type of room, price of room room
	 * status and room details.
	 * 
	 * @param roomNo
	 *            This room's number
	 * @param type
	 *            This room's type
	 * @param price
	 *            This room's price
	 * @param status
	 *            This room's status
	 * @param details
	 *            This room's details
	 */
	public Room(String roomNo, String type, double price, String status, String[] details) {
		super();
		this.roomNo = roomNo;
		this.type = type;
		this.price = price;
		this.status = status;
		this.details = details;
	}

	/**
	 * Gets the room number of this room
	 * 
	 * @return this room's number
	 */
	public String getRoomNo() {
		return roomNo;
	}

	/**
	 * Changes the room number of this room
	 * 
	 * @param roomNo
	 *            This room's number
	 */
	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}

	/**
	 * Gets the type of this room
	 * 
	 * @return this room's type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Changes the type of this room
	 * 
	 * @param type
	 *            This room's type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Gets the price of this room
	 * 
	 * @return this room's price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Changes the price of this room
	 * 
	 * @param price
	 *            This room's price
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * Gets the status of this room
	 * 
	 * @return this room's status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Changes the status of this room
	 * 
	 * @param status
	 *            This room's status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Gets the details of this room
	 * 
	 * @return this room's details
	 */
	public String[] getDetails() {
		return details;
	}

	/**
	 * Changes the details of this room
	 * 
	 * @param details
	 *            This room's details
	 */
	public void setDetails(String[] details) {
		this.details = details;
	}
}

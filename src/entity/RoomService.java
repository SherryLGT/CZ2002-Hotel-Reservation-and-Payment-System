package entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Represents the room service for the guest.
 * 
 * @author Tan Wanyi Cherry
 * @version 1.0
 * @since 2016-04-05
 */

public class RoomService implements Serializable {
	private int roomserviceID;
	private Menu items;
	private Date date;
	private String remarks;
	private String status;
	private Guest guest;
	private Room room;

	/*
	 * private class Menu{
	 * 
	 * }
	 */

	public RoomService() {
	}

	public RoomService(int roomserviceID, Menu items, Date date, String remarks, String status,
			Guest guest, Room room) {
		this.roomserviceID = roomserviceID;
		this.items = items;
		this.date = date;
		this.remarks = remarks;
		this.status = status;
		this.guest = guest;
		this.room = room;
	}

	public int getRoomServiceID() {
		return roomserviceID;
	}

	public void setRoomServiceID(int roomserviceID) {
		this.roomserviceID = roomserviceID;
	}

	public Menu getItems() {
		return items;
	}

	public void setItems(Menu items) {
		this.items = items;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Guest getGuest() {
		return guest;
	}

	public void setGuest(Guest guest) {
		this.guest = guest;
	}
	
	public Room getRoom(){
		return room;
	}
	
	public void setRoom(Room room) {
		this.room = room;
	}
}

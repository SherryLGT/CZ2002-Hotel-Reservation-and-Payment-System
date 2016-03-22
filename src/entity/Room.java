package entity;

import java.io.Serializable;

public class Room implements Serializable{
	private int roomNo;
	private String[] details;
	private String status;
	
	public Room() {}
	
	public Room(int roomNo, String[] details, String status) {
		this.roomNo = roomNo;
		this.details = details;
		this.status = status;
	}

	public int getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}

	public String[] getDetails() {
		return details;
	}

	public void setDetails(String[] details) {
		this.details = details;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}

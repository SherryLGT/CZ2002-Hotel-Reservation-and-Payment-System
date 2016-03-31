package entity;

import java.io.Serializable;

/**
 * Contains Room details 
 * 
 * @author Nicole Liow Wei Xuan
 * @since 2016-03-29
 */

public class Room implements Serializable{
	private int roomNo;
	private String type;
	private String price;
	private String[] details;
	private String status;
	
	public Room() {}
	
	public Room(int roomNo, String[] details, String status) {
		this.roomNo = roomNo;
		this.type = type;
		this.price = price;
		this.details = details;
		this.status = status;
	}

	public int getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
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

package entity;

import java.io.Serializable;
import java.util.Date;

public class RoomService implements Serializable{
	private MenuItem items;
	private Date date;
	private String remarks;
	private String status;
	
	private class MenuItem {
		
	}
	
	public RoomService() {}
	
	public RoomService(MenuItem items, Date date, String remarks, String status) {
		this.items = items;
		this.date = date;
		this.remarks = remarks;
		this.status = status;
	}

	public MenuItem getItems() {
		return items;
	}

	public void setItems(MenuItem items) {
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
}

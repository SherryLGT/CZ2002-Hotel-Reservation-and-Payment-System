package model;

import java.io.Serializable;

import org.joda.time.DateTime;

public class RoomService implements Serializable{
	private MenuItem items;
	private DateTime date;
	private String remarks;
	private String status;
	
	private class MenuItem {
		
	}
	
	public RoomService() {}
	
	public RoomService(MenuItem items, DateTime date, String remarks, String status) {
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

	public DateTime getDate() {
		return date;
	}

	public void setDate(DateTime date) {
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

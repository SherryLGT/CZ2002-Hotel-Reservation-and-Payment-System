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


	public RoomService() {
	}
	
	
	/**
	 * Room Service orders can be created and the status can be updated. 
	 * 
	 * @param roomserviceID
	 *            This room service order id.
	 * @param item
	 *            This room service order menu item id.
	 * @param date
	 *            This room service order date.
	 * @param remarks
	 *            This room service order remarks.
	 * @param status
	 *            This room service order status.
	 * @param guest
	 *            This room service order guest id.
	 * @param room
	 *            This room service order room id.
	 */
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
	
	/**
	 * Gets the room service order id.
	 * 
	 * @return this room service order id.
	 */
	public int getRoomServiceID() {
		return roomserviceID;
	}
	
	/**
	 * Changes the room service order's id.
	 * 
	 * @param id
	 *            This room service order's id.
	 */
	public void setRoomServiceID(int roomserviceID) {
		this.roomserviceID = roomserviceID;
	}

	/**
	 * Gets the menu item's id for this room service order.
	 * 
	 * @return this room service order's menu item's id.
	 */
	public Menu getItems() {
		return items;
	}
	
	/**
     * Changes the menu item's id for this room service order.
     * 
     * @param Menu
     *            This room service order's menu item's id
     */
	public void setItems(Menu items) {
		this.items = items;
	}
	
	/**
     * Gets the date of this room service order.
     * 
     * @return this room service order's date
     */
	public Date getDate() {
		return date;
	}
	
	/**
     * Changes the date of this room service order.
     * 
     * @param date
     *            This room service order's date
     */
	public void setDate(Date date) {
		this.date = date;
	}
	
	/**
	 * Gets the room service order's remarks.
	 * 
	 * @return this room service order's remarks.
	 */
	public String getRemarks() {
		return remarks;
	}
	
	/**
	 * Changes the room service order's remarks.
	 * 
	 * @param remarks
	 *            This room service order's remarks.
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	/**
	 * Gets the room service order's status.
	 * 
	 * @return this room service order's status.
	 */
	public String getStatus() {
		return status;
	}
	
	/**
	 * Changes the room service order's status.
	 * 
	 * @param status
	 *            This room service order's status.
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	/**
     * Gets the guest's details for this room service order.
     * 
     * @return this room service order's guest details.
     */
	public Guest getGuest() {
		return guest;
	}
	
	/**
     * Changes the guest's details for this room service order.
     * 
     * @param Guest
     *            this room service order's guest details.
     */
	public void setGuest(Guest guest) {
		this.guest = guest;
	}
	
	/**
     * Gets the room's details for this room service order.
     * 
     * @return this room service order's room details.
     */
	public Room getRoom(){
		return room;
	}
	
	/**
     * Changes the room's details for this room service order.
     * 
     * @param Room
     *            this room service order's room details.
     */
	public void setRoom(Room room) {
		this.room = room;
	}
}

package entity;

import java.io.Serializable;
import java.util.Date;

/** 
 * Represents the total bill for payment for the guest
 * @author Lau Geok Teng
 * @version 1.0
 * @since 2016-03-22
 */

public class Payment implements Serializable{
	private Reservation reservation;
	private double charges;
	private double tax;
	private RoomService roomService;
	private double discount;
	private double total;
	private Date date;
	
	/**
	 *  Default constructor
	 */
	public Payment() {}
	
	/**
	 *  Creates a new Payment with the room and room service details.
	 *  With the addition of charges, tax and discount(s) taken into consideration.
	 *  @param reservation This Payment for which reservation
	 *  @param charges This Payment's charges.
	 *  @param tax This Payment's tax.
	 *  @param roomService This Payment's room service.
	 *  @param discount This Payment's discount.
	 *  @param total This Payment's total bill.
	 *  @param date This Payment's date.
	 */	
	public Payment(Reservation reservation, double charges, double tax, RoomService roomService, double discount, double total, Date date) {
		this.reservation = reservation;
		this.charges = charges;
		this.tax = tax;
		this.roomService = roomService;
		this.discount = discount;
		this.total = total;
		this.date = date;
	}

	/**
	 * Gets the reservation details for this Payments.
	 * @return this Payment's reservation.
	 */
	public Reservation getReservation() {
		return reservation;
	}

	/**
	 * Changes the reservation details of this Payment.
	 * @param reservation the reservation to set
	 */
	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	/**
	 * Gets the charges of this Payments.
	 * @return this Payment's charges.
	 */
	public double getCharges() {
		return charges;
	}

	/**
	 * Changes the charges of this Payment.
	 * @param charges the charges to set
	 */
	public void setCharges(double charges) {
		this.charges = charges;
	}

	/**
	 * Gets the tax of this Payments.
	 * @return the tax
	 */
	public double getTax() {
		return tax;
	}

	/**
	 * Changes the tax of this Payment.
	 * @param tax the tax to set
	 */
	public void setTax(double tax) {
		this.tax = tax;
	}

	/**
	 * Gets the roomService details for this Payments.
	 * @return this Payment's roomService.
	 */
	public RoomService getRoomService() {
		return roomService;
	}

	/**
	 * Changes the roomService details of this Payment.
	 * @param roomService the roomService to set
	 */
	public void setRoomService(RoomService roomService) {
		this.roomService = roomService;
	}

	/**
	 * Gets the discount of this Payments.
	 * @return the discount
	 */
	public double getDiscount() {
		return discount;
	}

	/**
	 * Changes the discount of this Payment.
	 * @param discount the discount to set
	 */
	public void setDiscount(double discount) {
		this.discount = discount;
	}

	/**
	 * Gets the total bill of this Payments.
	 * @return the total
	 */
	public double getTotal() {
		return total;
	}

	/**
	 * Changes the total of this Payment.
	 * @param total the total to set
	 */
	public void setTotal(double total) {
		this.total = total;
	}

	/**
	 * Gets the date of this Payments.
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Changes the date of this Payment.
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}
}

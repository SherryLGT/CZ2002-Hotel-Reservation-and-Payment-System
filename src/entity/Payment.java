package entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Represents the total bill for payment for the guest
 * 
 * @author Lau Geok Teng
 * @version 1.0
 * @since 2016-03-22
 */

public class Payment implements Serializable {
	private Reservation reservation;
	private double charges;
	private double tax;
	private RoomService roomService;
	private double discount;
	private double total;
	private Date date;

	/**
	 * Default constructor
	 */
	public Payment() {
	}

	/**
	 * Creates a new payment with the room and room service details. With the
	 * addition of charges, tax and discount(s) taken into consideration.
	 * 
	 * @param reservation
	 *            This payment's reservation details
	 * @param charges
	 *            This payment's charges.
	 * @param tax
	 *            This payment's tax.
	 * @param roomService
	 *            This payment's room service.
	 * @param discount
	 *            This payment's discount.
	 * @param total
	 *            This payment's total bill.
	 * @param date
	 *            This payment's date.
	 */
	public Payment(Reservation reservation, double charges, double tax, RoomService roomService, double discount,
			double total, Date date) {
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
	 * 
	 * @return this Payment's reservation details.
	 */
	public Reservation getReservation() {
		return reservation;
	}

	/**
	 * Changes the reservation details of this payment.
	 * 
	 * @param reservation
	 *            This payment's reservation details
	 */
	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	/**
	 * Gets the charges of this payment.
	 * 
	 * @return this Payment's charges.
	 */
	public double getCharges() {
		return charges;
	}

	/**
	 * Changes the charges of this payment.
	 * 
	 * @param charges
	 *            This payment's charges
	 */
	public void setCharges(double charges) {
		this.charges = charges;
	}

	/**
	 * Gets the tax of this payment.
	 * 
	 * @return this Payment's tax
	 */
	public double getTax() {
		return tax;
	}

	/**
	 * Changes the tax of this payment.
	 * 
	 * @param tax
	 *            This payment's tax
	 */
	public void setTax(double tax) {
		this.tax = tax;
	}

	/**
	 * Gets the roomService details for this payment.
	 * 
	 * @return this payment's roomService.
	 */
	public RoomService getRoomService() {
		return roomService;
	}

	/**
	 * Changes the roomService details of this payment.
	 * 
	 * @param roomService
	 *            This payment's roomService
	 */
	public void setRoomService(RoomService roomService) {
		this.roomService = roomService;
	}

	/**
	 * Gets the discount of this payment.
	 * 
	 * @return this payment's discount
	 */
	public double getDiscount() {
		return discount;
	}

	/**
	 * Changes the discount of this payment.
	 * 
	 * @param discount
	 *            This payment's discount
	 */
	public void setDiscount(double discount) {
		this.discount = discount;
	}

	/**
	 * Gets the total bill of this payment.
	 * 
	 * @return this payment total
	 */
	public double getTotal() {
		return total;
	}

	/**
	 * Changes the total of this payment.
	 * 
	 * @param total
	 *            This payment's total
	 */
	public void setTotal(double total) {
		this.total = total;
	}

	/**
	 * Gets the date of this payment.
	 * 
	 * @return this payment's date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Changes the date of this payment.
	 * 
	 * @param date
	 *            This payment's date
	 */
	public void setDate(Date date) {
		this.date = date;
	}
}

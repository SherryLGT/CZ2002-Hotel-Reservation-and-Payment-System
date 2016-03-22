package entity;

import java.io.Serializable;

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
	 */	
	public Payment(Reservation reservation, double charges, double tax, RoomService roomService, double discount) {
		this.reservation = reservation;
		this.charges = charges;
		this.tax = tax;
		this.roomService = roomService;
		this.discount = discount;
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
}

package entity;

import java.io.Serializable;

/**
 * Contains Guest details which can be updated and read
 * 
 * @author Nicole Liow Wei Xuan
 * @version 1.0
 * @since 2016-03-29
 * 
 */

public class Guest implements Serializable{

	private String name;
	private String gender;
	private CreditCard card;
	private Address address;
	private String country;
	private Identity identity;
	private String nationality;
	private String contact;
	 
    /**
     * Creates a new creditcard with the type, card number,
     * cvv and expiry date.
     * 
     * @param type
     *            This creditcard type.
     * @param num
     *            This creditcard number.
     * @param cvv
     *            This creditcard cvv.
     * @param exp
     *            This creditcard expiry date.
     */
	public class CreditCard {
		
		private String type="null";
		private String num="null";
		private String cvv="null";
		private String exp="null";
		
		/**
		 * Gets the type of card for this creditcard.
		 * 
		 * @return this creditcard's type.
		 *
		 */
		public String getType() {return type;}
		
		/**
		 * Changes the type of card for this creditcard.
		 * 
		 * @param type
		 *		This creditcard's type
		 *
		 */
		public void setType(String type) {this.type = type;}
		
		/**
		 * Gets the number of the card for this creditcard.
		 * 
		 * @return this creditcard's number.
		 *
		 */
		public String getNum() {return num;	}
		
		/**
		 * Changes the number of the card for this creditcard.
		 * 
		 * @param num
		 *		This creditcard's number
		 *
		 */
		public void setNum(String num) {this.num = num;}
		
		/**
		 * Gets the cvv for this creditcard.
		 * 
		 * @return this creditcard's cvv.
		 *
		 */
		public String getCvv() {return cvv;	}
		
		/**
		 * Changes the cvv for this creditcard.
		 * 
		 * @param cvv
		 *		This creditcard's cvv
		 *
		 */
		public void setCvv(String cvv) {this.cvv = cvv;	}
		
		/**
		 * Gets the expiry date for this creditcard.
		 * 
		 * @return this creditcard's expiry date.
		 *
		 */
		public String getExp() {return exp;	}
		
		/**
		 * Changes the expiry date for this creditcard.
		 * 
		 * @param exp
		 *		This creditcard's expiry date.
		 *
		 */
		public void setExp(String exp) {this.exp = exp;	}
		
	    /**
	     * Default constructor
	     */
		public CreditCard(){}
		
	}
	
    /**
     * Creates a new address with address line 1 and 2,
     * city, state and zip.
     * 
     * @param reservation
     *            This payment's reservation details.
     * @param charges
     *            This payment's charges.
     * @param tax
     *            This payment's tax.
     * @param discount
     *            This payment's discount.
     * @param total
     *            This payment's total bill.
     * @param date
     *            This payment's date.
     */
	public class Address {
		private String add1;
		private String add2;
		private String city;
		private String state;
		private String zip;
		
	    /**
	     * Gets the address line 1 for this address.
	     * 
	     * @return this address's address line 1.
	     */
		public String getAdd1() {return add1;}
		
	    /**
	     * Changes the address line 1 of this address.
	     * 
	     * @param add1
	     *            This address's address line 1.
	     */
		public void setAdd1(String add1) {this.add1 = add1;}
		
	    /**
	     * Gets the address line 2 for this address.
	     * 
	     * @return this address's address line 2.
	     */
		public String getAdd2() {return add2;}
		
	    /**
	     * Changes the address line 2 of this address.
	     * 
	     * @param add2
	     *            This address's address line 2.
	     */
		public void setAdd2(String add2) {this.add2 = add2;}
		
	    /**
	     * Gets the city for this address.
	     * 
	     * @return this address's city.
	     */
		public String getCity() {return city;}
		
	    /**
	     * Changes the city of this address.
	     * 
	     * @param city
	     *            This address's city.
	     */
		public void setCity(String city) {this.city = city;}
		
	    /**
	     * Gets the state for this address.
	     * 
	     * @return this address's state.
	     */
		public String getState() {return state;}
		
	    /**
	     * Changes the state of this address.
	     * 
	     * @param state
	     *            This address's state.
	     */
		public void setState(String state) {this.state = state;}
		
	    /**
	     * Gets the zip code for this address.
	     * 
	     * @return this address's zip code.
	     */
		public String getZip() {return zip;}
		
	    /**
	     * Changes the zip code of this address.
	     * 
	     * @param city
	     *            This address's zip code.
	     */
		public void setZip(String zip) {this.zip = zip;}
		
		/**
		 * Default constructor
		 */
		public Address(){}	
	}
	
    /**
     * Creates a new identity with license number and passport number.
     * 
     * @param lic
     *            This identity's license number.
     * @param pp
     *            This identity's passport number.
     */
	public class Identity {
		
		private String lic="null";
		private String pp="null";
		
	    /**
	     * Gets the license number for this identity.
	     * 
	     * @return this identity's license number.
	     */
		public String getLic() {return lic;	}
		
	    /**
	     * Changes the license number of this identity.
	     * 
	     * @param lic
	     *            This identity's license number.
	     */
		public void setLic(String lic) {this.lic = lic;	}
		
	    /**
	     * Gets the passport number for this identity.
	     * 
	     * @return this identity's passport number.
	     */
		public String getPp() {return pp;	}
		
	    /**
	     * Changes the passport number of this identity.
	     * 
	     * @param pp
	     *            This identity's passport number.
	     */
		public void setPp(String pp) {this.pp = pp;	}

		/**
		 * Default constructor
		 */
		public Identity(){}
		
	}

	/**
	 * Default constructor
	 */
	public Guest() {}
	
    /**
     * Creates a new guest with name, gender, creditcard, address,
     * country, identity, nationality and contact
     * 
     * @param name
     *            This guest's name.
     * @param gender
     *            This guest's gender.
     * @param card
     *            This guest's card.
     * @param address
     *            This guest's address.
     * @param country
     *            This guest's total country.
     * @param identity
     *            This guest's identity.
     * @param nationality
     *            This guest's nationality.
     * @param contact
     *            This guest's contact.
     */
	public Guest(String name, String gender, CreditCard card, Address address, String country, Identity identity, String nationality, String contact) {
		this.name = name;
		this.gender = gender;
		this.card = card;
		this.address = address;
		this.country = country;
		this.identity = identity;
		this.nationality = nationality;
		this.contact = contact;
	}

    /**
     * Gets the name for this guest.
     * 
     * @return this guest's name.
     */
	public String getName() {
		return name;
	}
	
    /**
     * Changes the name of this guest.
     * 
     * @param name
     *            This guest's name.
     */
	public void setName(String name) {
		this.name = name;
	}

    /**
     * Gets the gender for this guest.
     * 
     * @return this guest's gender.
     */
	public String getGender() {
		return gender;
	}
	
    /**
     * Changes the gender of this guest.
     * 
     * @param gender
     *            This guest's gender.
     */
	public void setGender(String gender) {
		this.gender = gender;
	}

    /**
     * Gets the creditcard for this guest.
     * 
     * @return this guest's creditcard.
     */
	public CreditCard getCard() {
		return card;
	}
	
    /**
     * Changes the creditcard of this guest.
     * 
     * @param card
     *            This guest's creditcard.
     */
	public void setCard(CreditCard card) {
		this.card = card;
	}

    /**
     * Gets the address for this guest.
     * 
     * @return this guest's address.
     */
	public Address getAddress() {
		return address;
	}
	
    /**
     * Changes the address of this guest.
     * 
     * @param address
     *            This guest's address.
     */
	public void setAddress(Address address) {
		this.address = address;
	}

    /**
     * Gets the country for this guest.
     * 
     * @return this guest's country.
     */
	public String getCountry() {
		return country;
	}
	
    /**
     * Changes the country of this guest.
     * 
     * @param country
     *            This guest's country.
     */
	public void setCountry(String country) {
		this.country = country;
	}

    /**
     * Gets the identity for this guest.
     * 
     * @return this guest's identity.
     */
	public Identity getIdentity() {
		return identity;
	}
	
    /**
     * Changes the identity of this guest.
     * 
     * @param identity
     *            This guest's identity.
     */
	public void setIdentity(Identity identity) {
		this.identity = identity;
	}

    /**
     * Gets the nationality for this guest.
     * 
     * @return this guest's nationality.
     */
	public String getNationality() {
		return nationality;
	}
	
    /**
     * Changes the nationality of this guest.
     * 
     * @param nationality
     *            This guest's nationality.
     */
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

    /**
     * Gets the contact for this guest.
     * 
     * @return this guest's contact.
     */
	public String getContact() {
		return contact;
	}
	
    /**
     * Changes the contact of this guest.
     * 
     * @param contact
     *            This guest's contact.
     */
	public void setContact(String contact) {
		this.contact = contact;
	}
	
    /**
     * Checking for whether the names are the same.
     * 
     * @param o
     *            Object for checking.
     */
	public boolean equals(Object o) {
		if (o instanceof Guest) {
			Guest g = (Guest)o;
			return (getName().equals(g.getName()));
		}
		return false;
	}
	
}

package entity;

import java.io.Serializable;

import entity.Guest.CreditCard;

/**
 * Contains Guest details which can be updated and read
 * 
 * @author Nicole Liow Wei Xuan
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
	private int contact;

	public class CreditCard {
		
		private String type;
		private String num;
		private String cvv;
		private String exp;
		
		public String getType() {return type;}
		public void setType(String type) {this.type = type;}
		
		public String getNum() {return num;	}
		public void setNum(String num) {this.num = num;}
		
		public String getCvv() {return cvv;	}
		public void setCvv(String cvv) {this.cvv = cvv;	}
		
		public String getExp() {return exp;	}
		public void setExp(String exp) {this.exp = exp;	}
		
		public CreditCard(){}
		
	}
	
	public class Address {
		private String add1;
		private String add2;
		private String city;
		private String state;
		private String zip;
	
		public String getAdd1() {return add1;}
		public void setAdd1(String add1) {this.add1 = add1;}
		
		public String getAdd2() {return add2;}
		public void setAdd2(String add2) {this.add2 = add2;}
		
		public String getCity() {return city;}
		public void setCity(String city) {this.city = city;}
		
		public String getState() {return state;}
		public void setState(String state) {this.state = state;}
		
		public String getZip() {return zip;}
		public void setZip(String zip) {this.zip = zip;}
		
		public Address(){}	
	}
	
	public class Identity {
		
		private String lic;
		private String pp;
		
		public String getLic() {return lic;	}
		public void setLic(String lic) {this.lic = lic;	}
		
		public String getPp() {return pp;	}
		public void setPp(String pp) {this.pp = pp;	}
		
		public Identity(){}
		
	}
	
	public Guest() {}
	
	public Guest(String name, String gender, CreditCard card, Address address, String country, Identity identity, String nationality, int contact) {
		this.name = name;
		this.gender = gender;
		this.card = card;
		this.address = address;
		this.country = country;
		this.identity = identity;
		this.nationality = nationality;
		this.contact = contact;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public CreditCard getCard() {
		return card;
	}

	public void setCard(CreditCard card) {
		this.card = card;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Identity getIdentity() {
		return identity;
	}

	public void setIdentity(Identity identity) {
		this.identity = identity;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public int getContact() {
		return contact;
	}

	public void setContact(int contact) {
		this.contact = contact;
	}
}

package model;

import java.io.Serializable;

public class Guest implements Serializable{
	private String name;
	private CreditCard card;
	private Address address;
	private String country;
	private Identity identity;
	private String nationality;
	private int contact;

	private class CreditCard {
		
	}
	
	private class Address {
		
	}
	
	private class Identity {
		
	}
	
	public Guest() {}
	
	public Guest(String name, CreditCard card, Address address, String country, Identity identity, String nationality, int contact) {
		this.name = name;
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

package com.uppoint.core.payload;

public class ProUserPayload {

	private String key;

	private String professionKey;

	private String name;
	private String email;
	private double rating;
	private String phoneNumber;
	private AddressPayload address;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getProfessionKey() {
		return professionKey;
	}

	public void setProfessionKey(String professionKey) {
		this.professionKey = professionKey;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public AddressPayload getAddress() {
		return address;
	}

	public void setAddress(AddressPayload address) {
		this.address = address;
	}

}

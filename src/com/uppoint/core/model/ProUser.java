package com.uppoint.core.model;

import com.google.api.server.spi.config.ApiTransformer;
import com.google.appengine.api.datastore.Key;
import com.uppoint.core.tansformer.ProUserTransformer;

@ApiTransformer(ProUserTransformer.class)
public class ProUser extends Model {

	private String name;
	private String email;
	private double rating;
	private String googleUserId;

	private String phoneNumber;
	private Address address;

	private Key professionKey;

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

	public String getGoogleUserId() {
		return googleUserId;
	}

	public void setGoogleUserId(String googleUserId) {
		this.googleUserId = googleUserId;
	}

	public Key getProfessionKey() {
		return professionKey;
	}

	public void setProfessionKey(Key professionKey) {
		this.professionKey = professionKey;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((googleUserId == null) ? 0 : googleUserId.hashCode());
		result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		result = prime * result + ((professionKey == null) ? 0 : professionKey.hashCode());
		long temp;
		temp = Double.doubleToLongBits(rating);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProUser other = (ProUser) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (googleUserId == null) {
			if (other.googleUserId != null)
				return false;
		} else if (!googleUserId.equals(other.googleUserId))
			return false;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		if (professionKey == null) {
			if (other.professionKey != null)
				return false;
		} else if (!professionKey.equals(other.professionKey))
			return false;
		if (Double.doubleToLongBits(rating) != Double.doubleToLongBits(other.rating))
			return false;
		return true;
	}
	
	public static interface SearchIndex {
		
		public static final String KIND = "ProUserSearchIndex";
		
		public static final String PROPERTY_COUNTRY_KEY = "countryKey";
		public static final String PROPERTY_CITY_KEY = "cityKey";
		public static final String PROPERTY_PROFESSION_KEY = "professionKey";
		public static final String PROPERTY_SERVICE_TYPES = "serviceTypes";
	}
	
}

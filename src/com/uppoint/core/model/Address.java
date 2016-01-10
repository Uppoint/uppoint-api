package com.uppoint.core.model;

import com.google.appengine.api.datastore.GeoPt;
import com.google.appengine.api.datastore.Key;

public class Address {

	private Key countryKey;
	private Key cityKey;
	private String street;
	private String number;
	private GeoPt location;

	public Key getCountryKey() {
		return countryKey;
	}

	public void setCountryKey(Key countryKey) {
		this.countryKey = countryKey;
	}

	public Key getCityKey() {
		return cityKey;
	}

	public void setCityKey(Key cityKey) {
		this.cityKey = cityKey;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public GeoPt getLocation() {
		return location;
	}

	public void setLocation(GeoPt location) {
		this.location = location;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cityKey == null) ? 0 : cityKey.hashCode());
		result = prime * result + ((countryKey == null) ? 0 : countryKey.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		result = prime * result + ((street == null) ? 0 : street.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		if (cityKey == null) {
			if (other.cityKey != null)
				return false;
		} else if (!cityKey.equals(other.cityKey))
			return false;
		if (countryKey == null) {
			if (other.countryKey != null)
				return false;
		} else if (!countryKey.equals(other.countryKey))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		if (street == null) {
			if (other.street != null)
				return false;
		} else if (!street.equals(other.street))
			return false;
		return true;
	}

}

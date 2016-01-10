package com.uppoint.core.model;

import com.google.api.server.spi.config.ApiTransformer;
import com.google.appengine.api.datastore.Key;
import com.uppoint.core.tansformer.UserDefinedServiceTransformer;

@ApiTransformer(UserDefinedServiceTransformer.class)
public class UserDefinedService extends Model {

	private String name;
	private String description;
	private int duration;
	private double price;

	private Key seriveTypeKey;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Key getSeriveTypeKey() {
		return seriveTypeKey;
	}

	public void setSeriveTypeKey(Key seriveTypeKey) {
		this.seriveTypeKey = seriveTypeKey;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + duration;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((seriveTypeKey == null) ? 0 : seriveTypeKey.hashCode());
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
		UserDefinedService other = (UserDefinedService) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (duration != other.duration)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		if (seriveTypeKey == null) {
			if (other.seriveTypeKey != null)
				return false;
		} else if (!seriveTypeKey.equals(other.seriveTypeKey))
			return false;
		return true;
	}

}

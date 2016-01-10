package com.uppoint.core.payload;

public class UserDefinedServicePayload {

	private String key;
	private String userKey;
	private String serviceTypeKey;

	private String name;
	private String description;
	private int duration;
	private double price;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getUserKey() {
		return userKey;
	}

	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}

	public String getServiceTypeKey() {
		return serviceTypeKey;
	}

	public void setServiceTypeKey(String serviceTypeKey) {
		this.serviceTypeKey = serviceTypeKey;
	}

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

}

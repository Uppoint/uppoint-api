package com.uppoint.sync;

import java.util.List;

import com.uppoint.core.model.Category;
import com.uppoint.core.model.City;
import com.uppoint.core.model.Country;
import com.uppoint.core.model.Event;
import com.uppoint.core.model.ProUser;
import com.uppoint.core.model.Profession;
import com.uppoint.core.model.ServiceType;
import com.uppoint.core.model.UserDefinedService;

public class SyncPayload {

	private long lastSyncTimestamp;

	private List<Country> updatedCountries;
	private List<City> updatedCities;

	private List<Category> updatedCategories;
	private List<Profession> updatedProfessions;
	private List<ServiceType> updatedServiceTypes;

	private ProUser profile;
	private List<UserDefinedService> updatedServices;
	private List<Event> updatedEvents;

	private List<String> deletedCountries;
	private List<String> deletedCities;

	private List<String> deletedCategories;
	private List<String> deletedProfessions;
	private List<String> deletedServiceTypes;

	private List<String> deletedServices;
	private List<String> deletedEvents;

	public long getLastSyncTimestamp() {
		return lastSyncTimestamp;
	}

	public List<Country> getUpdatedCountries() {
		return updatedCountries;
	}

	public void setUpdatedCountries(List<Country> updatedCountries) {
		this.updatedCountries = updatedCountries;
	}

	public List<City> getUpdatedCities() {
		return updatedCities;
	}

	public void setUpdatedCities(List<City> updatedCities) {
		this.updatedCities = updatedCities;
	}

	public List<Category> getUpdatedCategories() {
		return updatedCategories;
	}

	public void setUpdatedCategories(List<Category> updatedCategories) {
		this.updatedCategories = updatedCategories;
	}

	public List<Profession> getUpdatedProfessions() {
		return updatedProfessions;
	}

	public void setUpdatedProfessions(List<Profession> updatedProfessions) {
		this.updatedProfessions = updatedProfessions;
	}

	public List<ServiceType> getUpdatedServiceTypes() {
		return updatedServiceTypes;
	}

	public void setUpdatedServiceTypes(List<ServiceType> updatedServiceTypes) {
		this.updatedServiceTypes = updatedServiceTypes;
	}

	public void setLastSyncTimestamp(long lastSyncTimestamp) {
		this.lastSyncTimestamp = lastSyncTimestamp;
	}

	public ProUser getProfile() {
		return profile;
	}

	public void setProfile(ProUser profile) {
		this.profile = profile;
	}

	public List<UserDefinedService> getUpdatedServices() {
		return updatedServices;
	}

	public void setUpdatedServices(List<UserDefinedService> updatedServices) {
		this.updatedServices = updatedServices;
	}

	public List<Event> getUpdatedEvents() {
		return updatedEvents;
	}

	public void setUpdatedEvents(List<Event> updatedEvents) {
		this.updatedEvents = updatedEvents;
	}

	public List<String> getDeletedCountries() {
		return deletedCountries;
	}

	public void setDeletedCountries(List<String> deletedCountries) {
		this.deletedCountries = deletedCountries;
	}

	public List<String> getDeletedCities() {
		return deletedCities;
	}

	public void setDeletedCities(List<String> deletedCities) {
		this.deletedCities = deletedCities;
	}

	public List<String> getDeletedCategories() {
		return deletedCategories;
	}

	public void setDeletedCategories(List<String> deletedCategories) {
		this.deletedCategories = deletedCategories;
	}

	public List<String> getDeletedProfessions() {
		return deletedProfessions;
	}

	public void setDeletedProfessions(List<String> deletedProfessions) {
		this.deletedProfessions = deletedProfessions;
	}

	public List<String> getDeletedServiceTypes() {
		return deletedServiceTypes;
	}

	public void setDeletedServiceTypes(List<String> deletedServiceTypes) {
		this.deletedServiceTypes = deletedServiceTypes;
	}

	public List<String> getDeletedServices() {
		return deletedServices;
	}

	public void setDeletedServices(List<String> deletedServices) {
		this.deletedServices = deletedServices;
	}

	public List<String> getDeletedEvents() {
		return deletedEvents;
	}

	public void setDeletedEvents(List<String> deletedEvents) {
		this.deletedEvents = deletedEvents;
	}

}

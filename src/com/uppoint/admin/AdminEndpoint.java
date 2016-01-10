package com.uppoint.admin;

import javax.inject.Named;

import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.response.BadRequestException;
import com.google.appengine.api.oauth.OAuthRequestException;
import com.google.appengine.api.users.User;
import com.uppoint.core.BaseEndpoint;
import com.uppoint.core.model.Category;
import com.uppoint.core.model.City;
import com.uppoint.core.model.Country;
import com.uppoint.core.model.Profession;
import com.uppoint.core.model.ServiceType;

public class AdminEndpoint extends BaseEndpoint {

	private final AdminService adminService;

	public AdminEndpoint() {
		this.adminService = new AdminService();
	}

	@ApiMethod(path = "admin/categories", name = "admin.insertCategory")
	public Category insertCategory(Category category, User user) throws OAuthRequestException, BadRequestException {
		if (user == null) {
			throw new OAuthRequestException("Authentication required");
		}

		if (category == null) {
			throw new BadRequestException("Category is missing");
		}

		return this.adminService.insertCategory(category);
	}

	@ApiMethod(path = "admin/professions", name = "admin.insertProfession")
	public Profession insertProfession(@Named("parentKey") String categoryKeyStirng, Profession profession, User user)
			throws OAuthRequestException, BadRequestException {
		if (user == null) {
			throw new OAuthRequestException("Authentication required");
		}

		if (profession == null) {
			throw new BadRequestException("Profession is missing");
		}

		return this.adminService.insertProfession(categoryKeyStirng, profession);
	}

	@ApiMethod(path = "admin/serviceTypes", name = "admin.insertServiceType")
	public ServiceType insertServiceType(@Named("parentKey") String professionKeyString, ServiceType serviceType,
			User user) throws OAuthRequestException, BadRequestException {
		if (user == null) {
			throw new OAuthRequestException("Authentication required");
		}

		if (serviceType == null) {
			throw new BadRequestException("Service type is missing");
		}

		return this.adminService.insertServiceType(professionKeyString, serviceType);
	}
	
	@ApiMethod(path = "admin/countries", name = "admin.insertCountry")
	public Country insertCountry(Country country, User user) throws OAuthRequestException, BadRequestException {
		if (user == null) {
			throw new OAuthRequestException("Authentication required");
		}
		
		if (country == null) {
			throw new BadRequestException("Country is missing");
		}
		
		return this.adminService.insertCountry(country);
	}
	
	@ApiMethod(path = "admin/countries/{parentKey}/cities", name = "admin.insertCity")
	public City insertCity(@Named("parentKey") String countryKeyString, City city, User user) throws OAuthRequestException, BadRequestException {
		if (user == null) {
			throw new OAuthRequestException("Authentication required");
		}
		
		if (city == null) {
			throw new BadRequestException("City is missing");
			
		}
		
		return this.adminService.insertCity(countryKeyString, city);
	}
}

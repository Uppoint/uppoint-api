package com.uppoint.admin;

import java.util.Calendar;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.KeyRange;
import com.uppoint.core.mapper.CategoryMapper;
import com.uppoint.core.mapper.CityMapper;
import com.uppoint.core.mapper.CountryMapper;
import com.uppoint.core.mapper.Mapper;
import com.uppoint.core.mapper.ProfessionMapper;
import com.uppoint.core.mapper.ServiceTypeMapper;
import com.uppoint.core.model.Category;
import com.uppoint.core.model.City;
import com.uppoint.core.model.Country;
import com.uppoint.core.model.Profession;
import com.uppoint.core.model.ServiceType;

public class AdminService {

	private final DatastoreService datastore;

	private final Mapper<Category> categoryMapper;
	private final Mapper<Profession> professionMapper;
	private final Mapper<ServiceType> serviceTypeMapper;
	private final Mapper<Country> countryMapper;
	private final Mapper<City> cityMapper;

	AdminService() {
		this.datastore = DatastoreServiceFactory.getDatastoreService();

		this.categoryMapper = new CategoryMapper();
		this.professionMapper = new ProfessionMapper();
		this.serviceTypeMapper = new ServiceTypeMapper();
		this.countryMapper = new CountryMapper();
		this.cityMapper = new CityMapper();
	}

	Category insertCategory(Category category) {
		category.setDeleted(false);
		category.setLastUpdateTime(Calendar.getInstance().getTimeInMillis());

		final Entity entity = this.categoryMapper.toEntity(category);
		this.datastore.put(entity);
		category.setKey(entity.getKey());
		
		return category;
	}

	Profession insertProfession(String categoryKeyStirng, Profession profession) {
		final Key categoryKey = KeyFactory.stringToKey(categoryKeyStirng);
		final KeyRange generatedKeys = this.datastore.allocateIds(categoryKey, Profession.class.getSimpleName(), 1);

		profession.setKey(generatedKeys.getStart());
		profession.setDeleted(false);
		profession.setLastUpdateTime(Calendar.getInstance().getTimeInMillis());

		this.datastore.put(this.professionMapper.toEntity(profession));

		return profession;
	}

	ServiceType insertServiceType(String professionKeyString, ServiceType serviceType) {
		final Key professionKey = KeyFactory.stringToKey(professionKeyString);
		final KeyRange generatedKeys = this.datastore.allocateIds(professionKey, ServiceType.class.getSimpleName(), 1);

		serviceType.setKey(generatedKeys.getStart());
		serviceType.setDeleted(false);
		serviceType.setLastUpdateTime(Calendar.getInstance().getTimeInMillis());
		this.datastore.put(this.serviceTypeMapper.toEntity(serviceType));

		return serviceType;
	}

	Country insertCountry(Country country) {
		country.setDeleted(false);
		country.setLastUpdateTime(Calendar.getInstance().getTimeInMillis());

		final Entity entity = this.countryMapper.toEntity(country);
		this.datastore.put(entity);
		country.setKey(entity.getKey());

		return country;
	}

	City insertCity(String countryKeyString, City city) {
		final Key countryKey = KeyFactory.stringToKey(countryKeyString);
		final KeyRange generatedKeys = this.datastore.allocateIds(countryKey, City.class.getSimpleName(), 1);

		city.setKey(generatedKeys.getStart());
		city.setDeleted(false);
		city.setLastUpdateTime(Calendar.getInstance().getTimeInMillis());

		this.datastore.put(this.cityMapper.toEntity(city));

		return city;
	}
}

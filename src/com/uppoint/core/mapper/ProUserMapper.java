package com.uppoint.core.mapper;

import com.google.appengine.api.datastore.EmbeddedEntity;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.GeoPt;
import com.google.appengine.api.datastore.Key;
import com.uppoint.core.model.Address;
import com.uppoint.core.model.ProUser;

public class ProUserMapper extends BaseMapper<ProUser> {

	private static final String PROPERTY_PROFESSION_KEY = "professionKey";
	private static final String PROPERTY_NAME = "name";
	public static final String PROPERTY_EMAIL = "email";
	private static final String PROPERTY_RATING = "rating";
	private static final String PROPERTY_GOOGLE_USER_ID = "googleUserId";
	private static final String PROPERTY_PHONE_NUMBER = "phoneNumber";
	private static final String PROPERTY_ADDRESS = "address";

	private static final String PROPERTY_COUNTRY_KEY = "countryKey";
	private static final String PROPERTY_CITY_KEY = "cityKey";
	private static final String PROPERTY_STREET = "street";
	private static final String PROPERTY_NUMBER = "number";
	private static final String PROPERTY_LOCATION = "location";

	@Override
	protected void copyPropertiesToEntity(ProUser model, Entity outEntity) {
		final Key professionKey = model.getProfessionKey();
		if (professionKey != null) {
			outEntity.setUnindexedProperty(PROPERTY_PROFESSION_KEY, professionKey);
		}
		
		final String name = model.getName();
		if (name != null) {
			outEntity.setUnindexedProperty(PROPERTY_NAME, name);
		}
		
		final double rating = model.getRating();
		if (rating != 0d) {
			outEntity.setUnindexedProperty(PROPERTY_RATING, rating);
		}
		
		final String email = model.getEmail();
		if (email != null) {
			outEntity.setIndexedProperty(PROPERTY_EMAIL, email);
		}
		
		final String googleUserId = model.getGoogleUserId();
		if (googleUserId != null) {
			outEntity.setUnindexedProperty(PROPERTY_GOOGLE_USER_ID, googleUserId);
		}
		
		final String phoneNumber = model.getPhoneNumber();
		if (phoneNumber != null) {
			outEntity.setUnindexedProperty(PROPERTY_PHONE_NUMBER, phoneNumber);
		}
		
		final Address address = model.getAddress();
		if (address != null) {
			final EmbeddedEntity addressEntity = createAddressEntity(address);
			outEntity.setUnindexedProperty(PROPERTY_ADDRESS, addressEntity);
		}
	}

	private EmbeddedEntity createAddressEntity(final Address address) {
		final EmbeddedEntity addressEntity = new EmbeddedEntity();
		addressEntity.setUnindexedProperty(PROPERTY_COUNTRY_KEY, address.getCountryKey());
		addressEntity.setUnindexedProperty(PROPERTY_CITY_KEY, address.getCityKey());
		addressEntity.setUnindexedProperty(PROPERTY_STREET, address.getStreet());
		addressEntity.setUnindexedProperty(PROPERTY_NUMBER, address.getNumber());
		addressEntity.setUnindexedProperty(PROPERTY_LOCATION, address.getLocation());
		return addressEntity;
	}

	@Override
	protected ProUser copyPropertiesToModel(Entity entity) {
		final ProUser user = new ProUser();
		user.setProfessionKey((Key) entity.getProperty(PROPERTY_PROFESSION_KEY));
		user.setEmail((String) entity.getProperty(PROPERTY_EMAIL));
		
		final Object ratingValue = entity.getProperty(PROPERTY_RATING);
		if (ratingValue != null) {
			user.setRating((double) ratingValue);
		}
		user.setGoogleUserId((String) entity.getProperty(PROPERTY_GOOGLE_USER_ID));
		user.setPhoneNumber((String) entity.getProperty(PROPERTY_PHONE_NUMBER));
		user.setName((String) entity.getProperty(PROPERTY_NAME));

		final EmbeddedEntity addressEntity = (EmbeddedEntity) entity.getProperty(PROPERTY_ADDRESS);
		if (addressEntity != null) {
			final Address address = createAddress(addressEntity);
			user.setAddress(address);
		}
		return user;
	}

	private Address createAddress(final EmbeddedEntity addressEntity) {
		final Address address = new Address();
		address.setCountryKey((Key) addressEntity.getProperty(PROPERTY_COUNTRY_KEY));
		address.setCityKey((Key) addressEntity.getProperty(PROPERTY_CITY_KEY));
		address.setStreet((String) addressEntity.getProperty(PROPERTY_STREET));
		address.setNumber((String) addressEntity.getProperty(PROPERTY_NUMBER));
		address.setLocation((GeoPt) addressEntity.getProperty(PROPERTY_LOCATION));
		return address;
	}

}

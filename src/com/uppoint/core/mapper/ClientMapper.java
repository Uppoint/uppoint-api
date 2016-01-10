package com.uppoint.core.mapper;

import com.google.appengine.api.datastore.Entity;
import com.uppoint.core.model.Client;

public class ClientMapper extends BaseMapper<Client> {

	private static final String PROPERTY_NAME = "name";
	private static final String PROPERTY_EMAIL = "email";
	private static final String PROPERTY_GOOGLE_USER_ID = "googleUserId";
	private static final String PROPERTY_GENDER = "gender";

	@Override
	protected void copyPropertiesToEntity(Client model, Entity outEntity) {
		outEntity.setUnindexedProperty(PROPERTY_NAME, model.getName());
		outEntity.setIndexedProperty(PROPERTY_EMAIL, model.getEmail());
		outEntity.setUnindexedProperty(PROPERTY_GOOGLE_USER_ID, model.getGoogleUserId());
		outEntity.setUnindexedProperty(PROPERTY_GENDER, model.getGender());
	}

	@Override
	protected Client copyPropertiesToModel(Entity entity) {
		final Client client = new Client();
		client.setName((String) entity.getProperty(PROPERTY_NAME));
		client.setEmail((String) entity.getProperty(PROPERTY_EMAIL));
		client.setGoogleUserId((String) entity.getProperty(PROPERTY_GOOGLE_USER_ID));
		client.setGender((int) entity.getProperty(PROPERTY_GENDER));
		return client;
	}

}

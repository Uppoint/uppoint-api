package com.uppoint.core.mapper;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.uppoint.core.model.UserDefinedService;

public class UserDefinedServiceMapper extends BaseMapper<UserDefinedService> {

	private static final String PROPERTY_SERVICE_TYPE_KEY = "serviceTypeKey";
	private static final String PROPERTY_NAME = "name";
	private static final String PROPERTY_DESCRIPTION = "description";
	private static final String PROPERTY_DURATION = "duration";
	private static final String PROPERTY_PRICE = "price";

	@Override
	protected void copyPropertiesToEntity(UserDefinedService model, Entity outEntity) {
		outEntity.setIndexedProperty(PROPERTY_SERVICE_TYPE_KEY, model.getSeriveTypeKey());
		outEntity.setUnindexedProperty(PROPERTY_NAME, model.getName());
		outEntity.setUnindexedProperty(PROPERTY_DESCRIPTION, model.getDescription());
		outEntity.setUnindexedProperty(PROPERTY_DURATION, model.getDuration());
		outEntity.setUnindexedProperty(PROPERTY_PRICE, model.getPrice());
	}

	@Override
	protected UserDefinedService copyPropertiesToModel(Entity entity) {
		final UserDefinedService userDefinedService = new UserDefinedService();
		userDefinedService.setSeriveTypeKey((Key) entity.getProperty(PROPERTY_SERVICE_TYPE_KEY));
		userDefinedService.setName((String) entity.getProperty(PROPERTY_NAME));
		userDefinedService.setDescription((String) entity.getProperty(PROPERTY_DESCRIPTION));
		userDefinedService.setDuration((int) (long) entity.getProperty(PROPERTY_DURATION));
		userDefinedService.setPrice((double) entity.getProperty(PROPERTY_PRICE));
		return userDefinedService;
	}

}

package com.uppoint.core.tansformer;

import com.google.api.server.spi.config.Transformer;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.uppoint.core.model.UserDefinedService;
import com.uppoint.core.payload.UserDefinedServicePayload;

public class UserDefinedServiceTransformer implements Transformer<UserDefinedService, UserDefinedServicePayload> {

	@Override
	public UserDefinedService transformFrom(UserDefinedServicePayload payload) {
		final UserDefinedService userDefinedService = new UserDefinedService();
		final String key = payload.getKey();
		if (key != null) {
			userDefinedService.setKey(KeyFactory.stringToKey(key));
		}
		userDefinedService.setSeriveTypeKey(KeyFactory.stringToKey(payload.getServiceTypeKey()));
		userDefinedService.setName(payload.getName());
		userDefinedService.setDescription(payload.getDescription());
		userDefinedService.setDuration(payload.getDuration());
		userDefinedService.setPrice(payload.getPrice());
		return userDefinedService;
	}

	@Override
	public UserDefinedServicePayload transformTo(UserDefinedService model) {
		final UserDefinedServicePayload payload = new UserDefinedServicePayload();
		final Key key = model.getKey();
		if (key != null) {
			payload.setKey(KeyFactory.keyToString(key));
			payload.setUserKey(KeyFactory.keyToString(key.getParent()));
		}
		payload.setServiceTypeKey(KeyFactory.keyToString(model.getSeriveTypeKey()));
		payload.setName(model.getName());
		payload.setDescription(model.getDescription());
		payload.setDuration(model.getDuration());
		payload.setPrice(model.getPrice());
		return payload;
	}

}

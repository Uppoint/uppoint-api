package com.uppoint.core.tansformer;

import com.google.appengine.api.datastore.KeyFactory;
import com.uppoint.core.model.ServiceType;
import com.uppoint.core.payload.ServiceTypePayload;

public class ServiceTypeTransformer extends NomenclatureTransformer<ServiceType, ServiceTypePayload> {

	@Override
	protected ServiceType createModel(ServiceTypePayload payload) {
		return new ServiceType();
	}

	@Override
	protected ServiceTypePayload createPayload(ServiceType model) {
		final ServiceTypePayload payload = new ServiceTypePayload();
		payload.setProfessionKey(KeyFactory.keyToString(model.getKey().getParent()));
		return payload;
	}

}

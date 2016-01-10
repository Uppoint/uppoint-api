package com.uppoint.core.tansformer;

import com.google.appengine.api.datastore.KeyFactory;
import com.uppoint.core.model.City;
import com.uppoint.core.payload.CityPayload;

public class CityTransformer extends NomenclatureTransformer<City, CityPayload> {

	@Override
	protected City createModel(CityPayload payload) {
		return new City();
	}

	@Override
	protected CityPayload createPayload(City model) {
		final CityPayload cityPayload = new CityPayload();
		cityPayload.setCountryKey(KeyFactory.keyToString(model.getKey().getParent()));
		return cityPayload;
	}

}

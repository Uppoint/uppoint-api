package com.uppoint.core.tansformer;

import com.uppoint.core.model.Country;
import com.uppoint.core.payload.CountryPayload;

public class CountryTransformer extends NomenclatureTransformer<Country, CountryPayload> {

	@Override
	protected Country createModel(CountryPayload payload) {
		return new Country();
	}

	@Override
	protected CountryPayload createPayload(Country model) {
		return new CountryPayload();
	}

}

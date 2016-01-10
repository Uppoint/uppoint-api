package com.uppoint.core.mapper;

import com.uppoint.core.model.Country;

public class CountryMapper extends NomenclatureMapper<Country> {

	@Override
	protected Country createModel() {
		return new Country();
	}

}

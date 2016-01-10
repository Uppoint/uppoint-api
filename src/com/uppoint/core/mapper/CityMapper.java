package com.uppoint.core.mapper;

import com.uppoint.core.model.City;

public class CityMapper extends NomenclatureMapper<City> {

	@Override
	protected City createModel() {
		return new City();
	}

}

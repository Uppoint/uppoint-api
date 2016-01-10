package com.uppoint.core.mapper;

import com.uppoint.core.model.ServiceType;

public class ServiceTypeMapper extends NomenclatureMapper<ServiceType> {

	@Override
	protected ServiceType createModel() {
		return new ServiceType();
	}

}

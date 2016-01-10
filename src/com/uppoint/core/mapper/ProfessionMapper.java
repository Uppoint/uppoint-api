package com.uppoint.core.mapper;

import com.uppoint.core.model.Profession;

public class ProfessionMapper extends NomenclatureMapper<Profession> {

	@Override
	protected Profession createModel() {
		return new Profession();
	}

}

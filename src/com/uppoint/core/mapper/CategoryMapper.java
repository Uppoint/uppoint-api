package com.uppoint.core.mapper;

import com.uppoint.core.model.Category;

public class CategoryMapper extends NomenclatureMapper<Category> {

	@Override
	protected Category createModel() {
		return new Category();
	}

}

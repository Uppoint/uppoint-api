package com.uppoint.core.tansformer;

import com.uppoint.core.model.Category;
import com.uppoint.core.payload.CategoryPayload;

public class CategoryTransformer extends NomenclatureTransformer<Category, CategoryPayload> {

	@Override
	protected Category createModel(CategoryPayload payload) {
		return new Category();
	}

	@Override
	protected CategoryPayload createPayload(Category model) {
		return new CategoryPayload();
	}

}

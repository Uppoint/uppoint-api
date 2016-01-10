package com.uppoint.core.mapper;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Text;
import com.uppoint.core.model.Nomenclature;

abstract class NomenclatureMapper<T extends Nomenclature> extends BaseMapper<T> {

	private static final String PROPERTY_TRANSLATION = "translation";

	@Override
	protected void copyPropertiesToEntity(T model, Entity outEntity) {
		outEntity.setUnindexedProperty(PROPERTY_TRANSLATION, model.getTranslation());
	}

	@Override
	protected T copyPropertiesToModel(Entity entity) {
		T model = createModel();
		model.setTranslation((Text) entity.getProperty(PROPERTY_TRANSLATION));
		return model;
	}

	protected abstract T createModel();

}

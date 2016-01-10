package com.uppoint.core.mapper;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.uppoint.core.model.Model;

abstract class BaseMapper<M extends Model> implements Mapper<M> {

	@Override
	public Entity toEntity(M model) {
		final Key key = model.getKey();
		Entity entity = createEntity(model, key);

		entity.setIndexedProperty(Model.PROPERTY_LAST_UPDATE, model.getLastUpdateTime());
		entity.setIndexedProperty(Model.PROPERTY_IS_DELETED, model.isDeleted());

		copyPropertiesToEntity(model, entity);

		return entity;
	}

	private Entity createEntity(M model, final Key key) {
		Entity entity = null;
		if (key == null) {
			entity = new Entity(model.getClass().getSimpleName());
		} else {
			entity = new Entity(key);
		}
		return entity;
	}

	protected abstract void copyPropertiesToEntity(M model, Entity outEntity);

	@Override
	public M toModel(Entity entity) {
		M model = copyPropertiesToModel(entity);

		model.setKey(entity.getKey());

		final Object lastUpdateProperty = entity.getProperty(Model.PROPERTY_LAST_UPDATE);
		if (lastUpdateProperty != null) {
			model.setLastUpdateTime((long) lastUpdateProperty);
		}

		Object isDeletedProperty = entity.getProperty(Model.PROPERTY_IS_DELETED);
		if (isDeletedProperty != null) {
			model.setDeleted((boolean) isDeletedProperty);
		}

		return model;
	}

	protected abstract M copyPropertiesToModel(Entity entity);
}

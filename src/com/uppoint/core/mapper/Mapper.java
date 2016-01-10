package com.uppoint.core.mapper;

import com.google.appengine.api.datastore.Entity;
import com.uppoint.core.model.Model;

public interface Mapper<M extends Model> {
	
	M toModel(Entity entity);
	
	Entity toEntity(M model);
}

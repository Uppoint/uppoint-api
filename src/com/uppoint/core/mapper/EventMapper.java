package com.uppoint.core.mapper;

import com.google.appengine.api.datastore.Entity;
import com.uppoint.core.model.Event;

public class EventMapper extends BaseMapper<Event> {

	private static final String PROPERTY_TITLE = "title";
	private static final String PROPERTY_DESCRIPTION = "description";
	private static final String PROPERTY_START_TIME = "startTime";
	private static final String PROPERTY_END_TIME = "endTime";

	@Override
	protected void copyPropertiesToEntity(Event model, Entity outEntity) {
		outEntity.setUnindexedProperty(PROPERTY_TITLE, model.getTitle());
		outEntity.setUnindexedProperty(PROPERTY_DESCRIPTION, model.getDescription());
		outEntity.setIndexedProperty(PROPERTY_START_TIME, model.getStartTime());
		outEntity.setIndexedProperty(PROPERTY_END_TIME, model.getEndTime());
	}

	@Override
	protected Event copyPropertiesToModel(Entity entity) {
		final Event event = new Event();
		event.setTitle((String) entity.getProperty(PROPERTY_TITLE));
		event.setDescription((String) entity.getProperty(PROPERTY_DESCRIPTION));
		event.setStartTime((long) entity.getProperty(PROPERTY_START_TIME));
		event.setEndTime((long) entity.getProperty(PROPERTY_END_TIME));
		return event;
	}

}

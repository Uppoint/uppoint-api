package com.uppoint.core.tansformer;

import com.google.api.server.spi.config.Transformer;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.uppoint.core.model.Event;
import com.uppoint.core.payload.EventPayload;

public class EventTransformer implements Transformer<Event, EventPayload> {

	@Override
	public Event transformFrom(EventPayload payload) {
		final Event event = new Event();
		final String key = payload.getKey();
		if (key != null) {
			event.setKey(KeyFactory.stringToKey(key));
		}
		event.setTitle(payload.getTitle());
		event.setDescription(payload.getDescription());
		event.setStartTime(payload.getStartTime());
		event.setEndTime(payload.getEndTime());
		return event;
	}

	@Override
	public EventPayload transformTo(Event model) {
		final EventPayload payload = new EventPayload();
		final Key key = model.getKey();
		if (key != null) {
			payload.setKey(KeyFactory.keyToString(key));
			payload.setUserKey(KeyFactory.keyToString(key.getParent()));
		}
		payload.setTitle(model.getTitle());
		payload.setDescription(model.getDescription());
		payload.setStartTime(model.getStartTime());
		payload.setEndTime(model.getEndTime());
		return payload;
	}

}

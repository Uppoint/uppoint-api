package com.uppoint.core.tansformer;

import com.google.api.server.spi.config.Transformer;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.uppoint.core.model.Client;
import com.uppoint.core.payload.ClientPayload;

public class ClientTransformer implements Transformer<Client, ClientPayload> {

	@Override
	public Client transformFrom(ClientPayload payload) {
		final Client model = new Client();
		final String key = payload.getKey();
		if (key != null) {
			model.setKey(KeyFactory.stringToKey(key));
		}
		model.setName(payload.getName());
		model.setEmail(payload.getEmail());
		model.setGoogleUserId(payload.getGoogleUserId());
		model.setGender(payload.getGender());
		return model;
	}

	@Override
	public ClientPayload transformTo(Client model) {
		final ClientPayload payload = new ClientPayload();
		final Key key = model.getKey();
		if (key != null) {
			payload.setKey(KeyFactory.keyToString(key));
		}
		payload.setName(model.getName());
		payload.setEmail(model.getEmail());
		payload.setGoogleUserId(model.getGoogleUserId());
		payload.setGender(model.getGender());
		return payload;
	}

}

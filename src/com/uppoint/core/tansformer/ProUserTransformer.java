package com.uppoint.core.tansformer;

import com.google.api.server.spi.config.Transformer;
import com.google.appengine.api.datastore.GeoPt;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.uppoint.core.model.Address;
import com.uppoint.core.model.ProUser;
import com.uppoint.core.payload.AddressPayload;
import com.uppoint.core.payload.ProUserPayload;

public class ProUserTransformer implements Transformer<ProUser, ProUserPayload> {

	@Override
	public ProUser transformFrom(ProUserPayload payload) {
		final ProUser user = new ProUser();
		
		final String key = payload.getKey();
		if (key != null) {
			user.setKey(KeyFactory.stringToKey(key));
		}
		
		final String professionKey = payload.getProfessionKey();
		if (professionKey != null) {
			user.setProfessionKey(KeyFactory.stringToKey(professionKey));
		}
		
		user.setName(payload.getName());
		user.setEmail(payload.getEmail());
		user.setAddress(addressFrom(payload.getAddress()));
		user.setPhoneNumber(payload.getPhoneNumber());
		user.setRating(payload.getRating());
		
		return user;
	}

	private Address addressFrom(AddressPayload payload) {
		if (payload == null) {
			return null;
		}

		final Address address = new Address();
		address.setCountryKey(KeyFactory.stringToKey(payload.getCountryKey()));
		address.setCityKey(KeyFactory.stringToKey(payload.getCityKey()));
		address.setStreet(payload.getStreet());
		address.setNumber(payload.getNumber());
		address.setLocation(new GeoPt(payload.getLatitude(), payload.getLongitude()));
		return address;
	}

	@Override
	public ProUserPayload transformTo(ProUser model) {
		final ProUserPayload payload = new ProUserPayload();
		final Key key = model.getKey();
		if (key != null) {
			payload.setKey(KeyFactory.keyToString(key));
		}
		
		final Key professionKey = model.getProfessionKey();
		if (professionKey != null) {
			payload.setProfessionKey(KeyFactory.keyToString(professionKey));
		}
		
		payload.setName(model.getName());
		payload.setEmail(model.getEmail());
		payload.setAddress(addressTo(model.getAddress()));
		payload.setPhoneNumber(model.getPhoneNumber());
		payload.setRating(model.getRating());
		return payload;
	}

	private AddressPayload addressTo(Address address) {
		if (address == null) {
			return null;
		}

		final AddressPayload payload = new AddressPayload();
		payload.setCountryKey(KeyFactory.keyToString(address.getCountryKey()));
		payload.setCityKey(KeyFactory.keyToString(address.getCityKey()));
		payload.setStreet(address.getStreet());
		payload.setNumber(address.getNumber());
		final GeoPt location = address.getLocation();
		if (location != null) {
			payload.setLatitude(location.getLatitude());
			payload.setLongitude(location.getLongitude());
		}
		return payload;
	}
}

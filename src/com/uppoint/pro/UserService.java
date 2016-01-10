package com.uppoint.pro;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.uppoint.core.mapper.Mapper;
import com.uppoint.core.mapper.ProUserMapper;
import com.uppoint.core.model.ProUser;

public class UserService {

	private final DatastoreService datastore;
	private final Mapper<ProUser> mapper;

	public UserService() {
		this.datastore = DatastoreServiceFactory.getDatastoreService();
		this.mapper = new ProUserMapper();
	}

	public ProUser findByEmail(String email) {
		final Query query = new Query(ProUser.class.getSimpleName())
				.setFilter(new Query.FilterPredicate(ProUserMapper.PROPERTY_EMAIL, FilterOperator.EQUAL, email));
		final PreparedQuery preparedQuery = this.datastore.prepare(query);
		final Entity result = preparedQuery.asSingleEntity();
		ProUser user = null;
		if (result != null) {
			user = this.mapper.toModel(result);
		}
		return user;
	}

	public ProUser create(String email, String googleUserId) {
		final ProUser user = new ProUser();
		user.setEmail(email);
		user.setGoogleUserId(googleUserId);

		final Entity userEntity = this.mapper.toEntity(user);
		this.datastore.put(userEntity);
		return this.mapper.toModel(userEntity);
	}
}

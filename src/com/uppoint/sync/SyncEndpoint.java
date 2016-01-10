package com.uppoint.sync;

import javax.inject.Named;

import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.Nullable;
import com.google.api.server.spi.response.BadRequestException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.oauth.OAuthRequestException;
import com.google.appengine.api.users.User;
import com.uppoint.core.BaseEndpoint;

public class SyncEndpoint extends BaseEndpoint {

	private final SyncService syncService;

	public SyncEndpoint() {
		this.syncService = new SyncService();
	}

	@ApiMethod(path = "users/{proUserKey}/sync", name = "users.sync")
	public SyncPayload updateUser(@Named("proUserKey") String userKeyString, SyncPayload payload,
			@Nullable @Named("timestamp") String timestampString, User user)
					throws OAuthRequestException, BadRequestException {
		if (user == null) {
			throw new OAuthRequestException("Authentication required");
		}

		if (userKeyString == null) {
			throw new BadRequestException("User key is missing");
		}

		long timestamp = SyncService.TIMESTAMP_NONE;
		if (timestampString != null) {
			timestamp = Long.parseLong(timestampString);
		}

		final Key userKey = KeyFactory.stringToKey(userKeyString);
		return syncService.sync(userKey, payload, timestamp);
	}

}

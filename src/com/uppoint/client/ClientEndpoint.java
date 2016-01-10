package com.uppoint.client;

import javax.inject.Named;

import com.google.api.server.spi.config.ApiMethod;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.oauth.OAuthRequestException;
import com.google.appengine.api.users.User;
import com.uppoint.core.BaseEndpoint;

public class ClientEndpoint extends BaseEndpoint {

	private final SearchService searchService;

	public ClientEndpoint() {
		this.searchService = new SearchService();
	}

	@ApiMethod(path = "client/search", name = "client.getSearchResults")
	public SearchResults getSearchResults(@Named("countryKey") String countryKeyString,
			@Named("cityKey") String cityKeyString, @Named("professionKey") String professionKeyString,
			@Named("serviceTypeKey") String serviceTypeKeyString, User user) throws OAuthRequestException {
		if (user == null) {
			throw new OAuthRequestException("Authentication required");
		}

		return this.searchService.search(KeyFactory.stringToKey(countryKeyString),
				KeyFactory.stringToKey(cityKeyString), KeyFactory.stringToKey(professionKeyString),
				KeyFactory.stringToKey(serviceTypeKeyString));
	}
}

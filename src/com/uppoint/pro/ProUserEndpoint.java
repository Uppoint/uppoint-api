package com.uppoint.pro;

import javax.inject.Named;

import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.response.BadRequestException;
import com.google.appengine.api.oauth.OAuthRequestException;
import com.google.appengine.api.users.User;
import com.uppoint.core.BaseEndpoint;
import com.uppoint.core.model.ProUser;

public class ProUserEndpoint extends BaseEndpoint {

	private final UserService proUserService;

	public ProUserEndpoint() {
		this.proUserService = new UserService();
	}

	@ApiMethod(path = "users/{email}", name = "users.getByEmail")
	public ProUser getProUser(@Named("email") String email, User user)
			throws OAuthRequestException, BadRequestException {
		if (user == null) {
			throw new OAuthRequestException("Authentication required");
		}

		if (email == null) {
			throw new BadRequestException("Email is required");
		}

		return proUserService.findByEmail(email);
	}

	@ApiMethod(path = "users/{email}", name = "users.insert")
	public ProUser insertProUser(@Named("email") String email, User user)
			throws OAuthRequestException, BadRequestException {
		if (user == null) {
			throw new OAuthRequestException("Authentication required");
		}

		if (email == null) {
			throw new BadRequestException("Email is required");
		}

		return proUserService.create(email, user.getUserId());
	}

}

package com.uppoint.client;

import java.util.List;

import com.uppoint.core.model.ProUser;
import com.uppoint.core.model.UserDefinedService;

class SearchResult {

	private ProUser user;
	private List<UserDefinedService> services;

	public ProUser getUser() {
		return user;
	}

	public void setUser(ProUser user) {
		this.user = user;
	}

	public List<UserDefinedService> getServices() {
		return services;
	}

	public void setServices(List<UserDefinedService> services) {
		this.services = services;
	}

}

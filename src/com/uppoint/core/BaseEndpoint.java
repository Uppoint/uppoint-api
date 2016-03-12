package com.uppoint.core;

import com.google.api.server.spi.config.Api;

@Api(name = "uppointApi",
	title = "Uppoint API",
	version = "v1",
	scopes = { Constants.EMAIL_SCOPE },
	audiences = { Constants.ANDROID_AUDIENCE },
	clientIds = { Constants.WEB_CLIENT_ID, Constants.API_EXPLORER_CLIENT_ID, Constants.IOS_CLIENT_ID, Constants.ANDROID_CLIENT_ID})
public class BaseEndpoint {
	
	public BaseEndpoint() {
		
	}
}

package com.uppoint.core;

import com.google.api.server.spi.Constant;

/**
 * Contains the client IDs and scopes for allowed clients consuming your API.
 */
public final class Constants {
  public static final String WEB_CLIENT_ID = "55945210201-njqenms4v43b16d3pdatkntknra8d0h4.apps.googleusercontent.com";
  public static final String ANDROID_CLIENT_ID = "replace this with your Android client ID";
  public static final String IOS_CLIENT_ID = "55945210201-d8qpiorvg5cvh7e61nafsv8vddqna8uj.apps.googleusercontent.com";
  public static final String API_EXPLORER_CLIENT_ID = Constant.API_EXPLORER_CLIENT_ID;
  public static final String ANDROID_AUDIENCE = WEB_CLIENT_ID;

  public static final String EMAIL_SCOPE = "https://www.googleapis.com/auth/userinfo.email";
  
  private Constants() {
	  // deny instantiation
  }
  
}

package com.uppoint.core.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public final class GsonFactory {

	private static Gson gson;

	private GsonFactory() {

	}

	public static Gson getGson() {
		if (gson == null) {
			final GsonBuilder gsonBuilder = new GsonBuilder();
			gson = gsonBuilder.create();
		}

		return gson;
	}
}

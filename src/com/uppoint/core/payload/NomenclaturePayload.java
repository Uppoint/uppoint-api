package com.uppoint.core.payload;

import java.util.List;

public abstract class NomenclaturePayload {

	private String key;
	private List<LocalePair> translation;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public List<LocalePair> getTranslation() {
		return translation;
	}

	public void setTranslation(List<LocalePair> translation) {
		this.translation = translation;
	}

}

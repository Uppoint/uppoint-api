package com.uppoint.core.model;

import com.google.appengine.api.datastore.Key;

public abstract class Model {
	
	public static final String PROPERTY_LAST_UPDATE = "lastUpdate";
	public static final String PROPERTY_IS_DELETED = "isDeleted";
	
	private Key key;

	private long lastUpdateTime;
	private boolean isDeleted;

	public Key getKey() {
		return key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public long getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(long lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (isDeleted ? 1231 : 1237);
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		result = prime * result + (int) (lastUpdateTime ^ (lastUpdateTime >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Model other = (Model) obj;
		if (isDeleted != other.isDeleted)
			return false;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		if (lastUpdateTime != other.lastUpdateTime)
			return false;
		return true;
	}

}

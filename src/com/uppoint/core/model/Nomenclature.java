package com.uppoint.core.model;

import com.google.appengine.api.datastore.Text;

public abstract class Nomenclature extends Model {

	private Text translation;

	public Text getTranslation() {
		return translation;
	}

	public void setTranslation(Text translation) {
		this.translation = translation;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((translation == null) ? 0 : translation.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Nomenclature other = (Nomenclature) obj;
		if (translation == null) {
			if (other.translation != null)
				return false;
		} else if (!translation.equals(other.translation))
			return false;
		return true;
	}

}

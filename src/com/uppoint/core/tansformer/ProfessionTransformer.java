package com.uppoint.core.tansformer;

import com.google.appengine.api.datastore.KeyFactory;
import com.uppoint.core.model.Profession;
import com.uppoint.core.payload.ProfessionPayload;

public class ProfessionTransformer extends NomenclatureTransformer<Profession, ProfessionPayload> {

	@Override
	protected Profession createModel(ProfessionPayload payload) {
		return new Profession();
	}

	@Override
	protected ProfessionPayload createPayload(Profession model) {
		final ProfessionPayload payload = new ProfessionPayload();
		payload.setCategoryKey(KeyFactory.keyToString(model.getKey().getParent()));
		return payload;
	}

}

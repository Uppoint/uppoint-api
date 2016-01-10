package com.uppoint.core.tansformer;

import java.lang.reflect.Type;
import java.util.List;

import com.google.api.server.spi.config.Transformer;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Text;
import com.google.gson.reflect.TypeToken;
import com.uppoint.core.gson.GsonFactory;
import com.uppoint.core.model.Nomenclature;
import com.uppoint.core.payload.LocalePair;
import com.uppoint.core.payload.NomenclaturePayload;

public abstract class NomenclatureTransformer<F extends Nomenclature, T extends NomenclaturePayload>
		implements Transformer<F, T> {

	@Override
	public F transformFrom(T payload) {
		F model = createModel(payload);
		final String key = payload.getKey();
		if (key != null) {
			model.setKey(KeyFactory.stringToKey(key));
		}
		model.setTranslation(serialiseTranslation(payload));

		return model;
	}

	private Text serialiseTranslation(T payload) {
		final List<LocalePair> translatioPairs = payload.getTranslation();
		final String json = GsonFactory.getGson().toJson(translatioPairs);
		return new Text(json);
	}

	protected abstract F createModel(T payload);

	@Override
	public T transformTo(F model) {
		T payload = createPayload(model);
		final Key key = model.getKey();
		if (key != null) {
			payload.setKey(KeyFactory.keyToString(key));
		}
		payload.setTranslation(deserialiseTranslation(model));

		return payload;
	}

	private List<LocalePair> deserialiseTranslation(F model) {
		final Text json = model.getTranslation();
		final Type localePairType = new TypeToken<List<LocalePair>>() {}.getType();
		final List<LocalePair> translationPairs = GsonFactory.getGson().fromJson(json.getValue(), localePairType);
		return translationPairs;
	}

	protected abstract T createPayload(F model);

}

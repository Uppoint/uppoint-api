package com.uppoint.core.model;

import com.google.api.server.spi.config.ApiTransformer;
import com.uppoint.core.tansformer.CountryTransformer;

@ApiTransformer(CountryTransformer.class)
public class Country extends Nomenclature {

}

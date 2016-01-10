package com.uppoint.core.model;

import com.google.api.server.spi.config.ApiTransformer;
import com.uppoint.core.tansformer.CityTransformer;

@ApiTransformer(CityTransformer.class)
public class City extends Nomenclature{

}

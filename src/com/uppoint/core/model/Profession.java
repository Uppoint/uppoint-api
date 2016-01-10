package com.uppoint.core.model;

import com.google.api.server.spi.config.ApiTransformer;
import com.uppoint.core.tansformer.ProfessionTransformer;

@ApiTransformer(ProfessionTransformer.class)
public class Profession extends Nomenclature {

}

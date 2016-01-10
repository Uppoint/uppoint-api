package com.uppoint.core.model;

import com.google.api.server.spi.config.ApiTransformer;
import com.uppoint.core.tansformer.ServiceTypeTransformer;

@ApiTransformer(ServiceTypeTransformer.class)
public class ServiceType extends Nomenclature {

}

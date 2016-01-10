package com.uppoint.core.model;

import com.google.api.server.spi.config.ApiTransformer;
import com.uppoint.core.tansformer.CategoryTransformer;

@ApiTransformer(CategoryTransformer.class)
public class Category extends Nomenclature {

}

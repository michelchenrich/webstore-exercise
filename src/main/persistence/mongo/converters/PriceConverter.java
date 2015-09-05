package main.persistence.mongo.converters;

import main.domain.product.Price;
import main.persistence.mongo.Converter;

public class PriceConverter implements Converter<Price, String> {
    public String to(Price entity) {
        return entity.toString();
    }

    public Price fromPersisted(String document) {
        return new Price(document);
    }
}

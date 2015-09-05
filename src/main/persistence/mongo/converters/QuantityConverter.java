package main.persistence.mongo.converters;

import main.domain.product.Quantity;
import main.persistence.mongo.Converter;

public class QuantityConverter implements Converter<Quantity, String> {
    public String to(Quantity entity) {
        return entity.toString();
    }

    public Quantity fromPersisted(String document) {
        return new Quantity(document);
    }
}

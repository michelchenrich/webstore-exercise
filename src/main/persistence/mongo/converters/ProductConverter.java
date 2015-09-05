package main.persistence.mongo.converters;

import main.domain.product.Product;

import java.util.Map;

public class ProductConverter extends EntityConverter<Product> {
    private TextConverter textConverter = new TextConverter();
    private PriceConverter priceConverter = new PriceConverter();
    private QuantityConverter quantityConverter = new QuantityConverter();

    protected void defineGetters(Map<String, Getter<Product>> getters) {
        getters.put("name", (p) -> textConverter.to(p.getName()));
        getters.put("description", (p) -> textConverter.to(p.getDescription()));
        getters.put("price", (p) -> priceConverter.to(p.getPrice()));
        getters.put("unitsInStock", (p) -> quantityConverter.to(p.getUnitsInStock()));
    }

    protected void defineSetters(Map<String, Setter<Product>> setters) {
        setters.put("_id", (p, v) -> p.setId(v.toString()));
        setters.put("name", (p, v) -> p.setName(textConverter.from(v)));
        setters.put("description", (p, v) -> p.setDescription(textConverter.from(v)));
        setters.put("price", (p, v) -> p.setPrice(priceConverter.from(v)));
        setters.put("unitsInStock", (p, v) -> p.setUnitsInStock(quantityConverter.from(v)));
    }

    protected Product makeNew() {
        return new Product();
    }
}

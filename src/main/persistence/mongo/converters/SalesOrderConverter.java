package main.persistence.mongo.converters;

import main.domain.salesOrder.SalesOrder;
import java.util.Map;
import main.persistence.mongo.converters.TextConverter;

public class SalesOrderConverter extends EntityConverter<SalesOrder> {
    private TextConverter textConverter = new TextConverter();

    protected void defineGetters(Map<String, Getter<SalesOrder>> getters) {
        getters.put("productId", (p) -> p.getProductId());
        getters.put("quantity", (p) -> p.getQuantity());
        getters.put("customerName", (p) -> p.getCustomerName());
    }

    protected void defineSetters(Map<String, Setter<SalesOrder>> setters) {
        setters.put("_id", (p, v) -> p.setId(v.toString()));
        setters.put("productId", (p, v) -> p.setProductId(v.toString()));
        setters.put("quantity", (p, v) -> p.setQuantity(Integer.parseInt((v.toString()))));
        setters.put("customerName", (p, v) -> p.setCustomerName(v.toString()));
    }

    @Override
    protected SalesOrder makeNew() {
        return new SalesOrder();
    }
}

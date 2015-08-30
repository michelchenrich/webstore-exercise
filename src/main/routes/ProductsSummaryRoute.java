package main.routes;

import com.google.gson.Gson;
import main.domain.product.reading.ProductSummary;
import main.domain.product.reading.ReadProductsSummaryUseCase;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.ArrayList;
import java.util.Collection;

public class ProductsSummaryRoute implements Route {
    private Dependencies dependencies;
    private Gson converter = new Gson();

    public ProductsSummaryRoute(Dependencies dependencies) {
        this.dependencies = dependencies;
    }

    public Object handle(Request request, Response response) throws Exception {
        Collection<ProductSummary> output = new ArrayList<>();
        new ReadProductsSummaryUseCase(dependencies.productRepository, output).execute();
        return converter.toJson(output);
    }
}

package main.routes;

import com.google.gson.Gson;
import main.domain.product.creating.CreateProductRequest;
import main.domain.product.creating.CreateProductResponse;
import main.domain.product.creating.CreateProductUseCase;
import spark.Request;
import spark.Response;
import spark.Route;

public class CreateProductRoute implements Route {
    private Dependencies dependencies;
    private Gson converter = new Gson();

    public CreateProductRoute(Dependencies dependencies) {
        this.dependencies = dependencies;
    }

    public Object handle(Request request, Response response) throws Exception {
        CreateProductResponse output = new CreateProductResponse();
        CreateProductRequest input = converter.fromJson(request.body(), CreateProductRequest.class);
        new CreateProductUseCase(dependencies.getProductRepository(), input, output).execute();
        return converter.toJson(output);
    }
}

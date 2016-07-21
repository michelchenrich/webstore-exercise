package main.routes;

import com.google.gson.Gson;
import main.domain.salesOrder.CreateSalesOrderRequest;
import main.domain.salesOrder.CreateSalesOrderResponse;
import main.domain.salesOrder.CreateSalesOrderUseCase;
import spark.Request;
import spark.Response;
import spark.Route;

public class CreateSalesOrderRoute implements Route {
    private Dependencies dependencies;
    private Gson converter = new Gson();

    public CreateSalesOrderRoute(Dependencies dependencies) {
        this.dependencies = dependencies;
    }

    public Object handle(Request request, Response response) throws Exception {
        CreateSalesOrderResponse output = new CreateSalesOrderResponse();
        CreateSalesOrderRequest input = converter.fromJson(request.body(), CreateSalesOrderRequest.class);
        new CreateSalesOrderUseCase(dependencies.getSalesOrderRepository(), dependencies.getProductRepository(), input, output).execute();
        return converter.toJson(output);
    }
}
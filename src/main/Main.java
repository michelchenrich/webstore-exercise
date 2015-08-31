package main;

import main.persistence.account.InMemoryUserRepository;
import main.persistence.product.InMemoryProductRepository;
import main.routes.*;
import static spark.Spark.*;
import static spark.SparkBase.port;

public class Main {
    public static void main(String[] arguments) {
        Dependencies dependencies = new Dependencies();
        dependencies.userRepository = new InMemoryUserRepository();
        dependencies.productRepository = new InMemoryProductRepository();
        port(8081);
        externalStaticFileLocation("resources/public");
        get("/read-user", new ReadUserRoute(dependencies));
        post("/login", new LoginRoute(dependencies));
        post("/logout", new LogoutRoute(dependencies));
        post("/register", new RegisterRoute(dependencies));
        get("/products", new ProductsSummaryRoute(dependencies));
        post("/products", new CreateProductRoute(dependencies));
        delete("/product/:id", new DeleteProductRoute(dependencies));
    }
}

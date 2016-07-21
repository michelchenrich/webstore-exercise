package main;

import main.persistence.mongo.MongoProductRepository;
import main.persistence.mongo.MongoSalesOrderRepository;
import main.persistence.mongo.MongoUserRepository;
import main.routes.*;
import main.security.JasyptEncryptor;
import static spark.Spark.*;
import static spark.SparkBase.port;

public class Main {
    public static void main(String... arguments) {
        new Main().startSparkServer();
    }

    private void startSparkServer() {
        setUpPort();
        setUpStaticFiles();
        setUpRoutes();
    }

    private void setUpPort() {
        port(Integer.parseInt(System.getenv("PORT")));
    }

    private void setUpStaticFiles() {
        externalStaticFileLocation("resources/public");
    }

    private void setUpRoutes() {
        Dependencies dependencies = buildDependencies();
        get("/read-user", new ReadUserRoute(dependencies));
        post("/login", new LoginRoute(dependencies));
        post("/logout", new LogoutRoute(dependencies));
        post("/register", new RegisterRoute(dependencies));
        get("/products", new ProductsSummaryRoute(dependencies));
        post("/products", new CreateProductRoute(dependencies));
        post("/sales-order", new CreateSalesOrderRoute(dependencies));
        delete("/products/:id", new DeleteProductRoute(dependencies));
    }

    private Dependencies buildDependencies() {
        Dependencies dependencies = new Dependencies();
        dependencies.setEncryptor(new JasyptEncryptor());
        dependencies.setUserRepository(new MongoUserRepository());
        dependencies.setProductRepository(new MongoProductRepository());
        dependencies.setSalesOrderRepository(new MongoSalesOrderRepository());
        return dependencies;
    }
}

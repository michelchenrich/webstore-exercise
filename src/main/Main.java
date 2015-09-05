package main;

import main.persistence.migration.ProductRepositoryMigrator;
import main.persistence.migration.UserRepositoryMigrator;
import main.persistence.mongo.MongoProductRepository;
import main.persistence.mongo.MongoUserRepository;
import main.routes.*;
import static spark.Spark.*;
import static spark.SparkBase.port;

public class Main {
    public static void main(String[] arguments) {
        setUpPort();
        setUpStaticFiles();
        setUpRoutes();
    }

    private static void setUpPort() {
        port(Integer.parseInt(System.getenv("PORT")));
    }

    private static void setUpStaticFiles() {
        externalStaticFileLocation("resources/public");
    }

    private static void setUpRoutes() {
        Dependencies dependencies = buildDependencies();
        get("/read-user", new ReadUserRoute(dependencies));
        post("/login", new LoginRoute(dependencies));
        post("/logout", new LogoutRoute(dependencies));
        post("/register", new RegisterRoute(dependencies));
        get("/products", new ProductsSummaryRoute(dependencies));
        post("/products", new CreateProductRoute(dependencies));
        delete("/product/:id", new DeleteProductRoute(dependencies));
    }

    private static Dependencies buildDependencies() {
        Dependencies dependencies = new Dependencies();
        dependencies.setUserRepository(new UserRepositoryMigrator(new MongoUserRepository(), new MongoUserRepository()));
        dependencies.setProductRepository(new ProductRepositoryMigrator(new MongoProductRepository(), new MongoProductRepository()));
        return dependencies;
    }
}

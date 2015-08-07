package main;

import main.account.InMemoryUserRepository;
import main.routes.*;
import static spark.Spark.*;
import static spark.SparkBase.port;

public class Main {
    public static void main(String[] arguments) {
        Dependencies dependencies = new Dependencies();
        dependencies.userRepository = new InMemoryUserRepository();
        port(8081);
        staticFileLocation("/public");
        get("/read-user", new ReadUserRoute(dependencies));
        post("/login", new LoginRoute(dependencies));
        post("/logout", new LogoutRoute(dependencies));
        post("/register", new RegisterRoute(dependencies));
    }
}

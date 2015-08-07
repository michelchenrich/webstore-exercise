package main;

import main.account.InMemoryUserRepository;
import main.account.UserRepository;
import main.routes.LoginRoute;
import main.routes.LogoutRoute;
import main.routes.ReadUserRoute;
import main.routes.RegisterRoute;
import static spark.Spark.*;
import static spark.SparkBase.port;

public class Main {
    public static final UserRepository userRepository = new InMemoryUserRepository();

    public static void main(String[] arguments) {
        port(8081);
        staticFileLocation("/public");
        get("/read-user", new ReadUserRoute());
        post("/login", new LoginRoute());
        post("/logout", new LogoutRoute());
        post("/register", new RegisterRoute());
    }
}

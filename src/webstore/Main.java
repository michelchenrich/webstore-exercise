package webstore;

import static spark.Spark.*;
import static spark.SparkBase.port;

public class Main {
    private static final JSONConverter converter = new JSONConverter();
    private static final String SESSION_ID = "somerandomshit";

    public static void main(String[] arguments) {
        configureUrl();
        staticFileLocation("/public");
        allowCrossOriginRequests();
        get("/has-session", (request, response) -> {
            HasSessionResponse hasSessionResponse = new HasSessionResponse();
            String session = request.cookie("session");
            hasSessionResponse.hasSession = session != null && session.equals(SESSION_ID);
            return converter.toJson(hasSessionResponse);
        });
        post("/login", (request, response) -> {
            LoginResponse loginResponse = new LoginResponse();
            LoginRequest loginRequest = converter.fromJson(request.body(), LoginRequest.class);
            loginResponse.success = !loginRequest.email.isEmpty() && !loginRequest.password.isEmpty();
            if (loginResponse.success)
                response.cookie("session", SESSION_ID);
            loginResponse.message = "Invalid e-mail/password";
            return converter.toJson(loginResponse);
        });
        post("/logout", (request, response) -> {
            response.removeCookie("session");
            return converter.toJson(null);
        });
        post("/register", (request, response) -> {
            LoginResponse loginResponse = new LoginResponse();
            RegisterRequest registerRequest = converter.fromJson(request.body(), RegisterRequest.class);
            loginResponse.success = true;
            response.cookie("session", SESSION_ID);
            return converter.toJson(loginResponse);
        });
    }

    private static void configureUrl() {
        port(8081);
    }

    private static void allowCrossOriginRequests() {
        before((request, response) -> {
            response.header("Access-Control-Allow-Origin", "http://localhost:8080");
            response.header("Access-Control-Allow-Credentials", "true");
        });
    }

    private static class HasSessionResponse {
        public boolean hasSession;
    }
}

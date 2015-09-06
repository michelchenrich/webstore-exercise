package main.routes;

import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import spark.Route;

public class LogoutRoute implements Route {
    private Dependencies dependencies;
    private Gson converter;

    public LogoutRoute(Dependencies dependencies) {
        this.dependencies = dependencies;
        this.converter = new Gson();
    }

    public Object handle(Request request, Response response) throws Exception {
        response.removeCookie("user-id");
        return converter.toJson(null);
    }
}

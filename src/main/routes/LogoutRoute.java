package main.routes;

import com.google.gson.Gson;
import spark.Request;
import spark.Response;

public class LogoutRoute extends JsonRoute {
    private Gson converter = new Gson();

    public Object handle(Request request, Response response) throws Exception {
        response.removeCookie("user-id");
        return converter.toJson(null);
    }
}

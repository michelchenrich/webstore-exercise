package main.routes;

import spark.Request;
import spark.Response;

public class LogoutRoute extends UserRoute {
    public LogoutRoute(Dependencies dependencies) {
        super(dependencies);
    }

    public Object handle(Request request, Response response) throws Exception {
        response.removeCookie("user-id");
        return converter.toJson(null);
    }
}

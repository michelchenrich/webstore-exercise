package main.routes;

import com.google.gson.Gson;
import spark.Route;

public abstract class UserRoute implements Route {
    protected final Gson converter;
    protected final Dependencies dependencies;

    public UserRoute(Dependencies dependencies) {
        this.converter = new Gson();
        this.dependencies = dependencies;
    }
}

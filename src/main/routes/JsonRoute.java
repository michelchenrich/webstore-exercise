package main.routes;

import com.google.gson.Gson;
import spark.Route;

public abstract class JsonRoute implements Route {
    protected Gson converter = new Gson();
}

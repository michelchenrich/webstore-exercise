package main.routes;

import com.google.gson.Gson;
import main.domain.account.reading.ReadUserRequest;
import main.domain.account.reading.ReadUserResponse;
import main.domain.account.reading.ReadUserUseCase;
import spark.Request;
import spark.Response;
import spark.Route;

public class ReadUserRoute implements Route {
    private Dependencies dependencies;
    private Gson converter;

    public ReadUserRoute(Dependencies dependencies) {
        this.dependencies = dependencies;
        this.converter = new Gson();
    }

    public Object handle(Request request, Response response) throws Exception {
        ReadUserResponse output = executeUseCase(request);
        return converter.toJson(output);
    }

    private ReadUserResponse executeUseCase(Request request) {
        ReadUserRequest input = new ReadUserRequest();
        input.id = request.cookie("user-id");
        ReadUserResponse output = new ReadUserResponse();
        new ReadUserUseCase(dependencies.getUserRepository(), input, output).execute();
        return output;
    }
}

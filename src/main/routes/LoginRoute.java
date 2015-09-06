package main.routes;

import com.google.gson.Gson;
import main.domain.account.login.LoginRequest;
import main.domain.account.login.LoginResponse;
import main.domain.account.login.LoginUseCase;
import spark.Request;
import spark.Response;
import spark.Route;

public class LoginRoute implements Route {
    private Dependencies dependencies;
    private Gson converter;

    public LoginRoute(Dependencies dependencies) {
        this.dependencies = dependencies;
        this.converter = new Gson();
    }

    public Object handle(Request request, Response response) throws Exception {
        LoginResponse output = executeUseCase(request);
        response.cookie("user-id", output.id);
        return converter.toJson(output);
    }

    private LoginResponse executeUseCase(Request request) {
        LoginRequest input = converter.fromJson(request.body(), LoginRequest.class);
        LoginResponse output = new LoginResponse();
        new LoginUseCase(dependencies.getUserRepository(), input, output, dependencies.getEncryptor()).execute();
        return output;
    }
}

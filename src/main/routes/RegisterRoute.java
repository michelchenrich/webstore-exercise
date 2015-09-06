package main.routes;

import com.google.gson.Gson;
import main.domain.account.registration.RegisterRequest;
import main.domain.account.registration.RegisterResponse;
import main.domain.account.registration.RegisterUseCase;
import spark.Request;
import spark.Response;
import spark.Route;

public class RegisterRoute implements Route {
    private Dependencies dependencies;
    private Gson converter;

    public RegisterRoute(Dependencies dependencies) {
        this.dependencies = dependencies;
        this.converter = new Gson();
    }

    public Object handle(Request request, Response response) throws Exception {
        RegisterResponse output = executeUseCase(request);
        response.cookie("user-id", output.id);
        return converter.toJson(output);
    }

    private RegisterResponse executeUseCase(Request request) {
        RegisterResponse output = new RegisterResponse();
        RegisterRequest input = converter.fromJson(request.body(), RegisterRequest.class);
        new RegisterUseCase(dependencies.getUserRepository(), input, output, dependencies.getEncryptor()).execute();
        return output;
    }
}

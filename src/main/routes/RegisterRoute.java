package main.routes;

import main.domain.account.registration.RegisterRequest;
import main.domain.account.registration.RegisterResponse;
import main.domain.account.registration.RegisterUseCase;
import spark.Request;
import spark.Response;

public class RegisterRoute extends UserRoute {
    public RegisterRoute(Dependencies dependencies) {
        super(dependencies);
    }

    public Object handle(Request request, Response response) throws Exception {
        RegisterResponse output = executeUseCase(request);
        updateCookie(response, output);
        return converter.toJson(output);
    }

    private RegisterResponse executeUseCase(Request request) {
        RegisterResponse output = new RegisterResponse();
        RegisterRequest input = converter.fromJson(request.body(), RegisterRequest.class);
        new RegisterUseCase(dependencies.userRepository, input, output).execute();
        return output;
    }

    private void updateCookie(Response response, RegisterResponse output) {
        response.removeCookie("user-id");
        if (output.success)
            response.cookie("user-id", output.id);
    }
}

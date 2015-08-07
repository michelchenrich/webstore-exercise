package main.routes;

import main.account.LoginRequest;
import main.account.LoginResponse;
import main.account.LoginUseCase;
import spark.Request;
import spark.Response;

public class LoginRoute extends UserRoute {
    public LoginRoute(Dependencies dependencies) {
        super(dependencies);
    }

    public Object handle(Request request, Response response) throws Exception {
        LoginResponse output = executeUseCase(request);
        updateCookie(response, output);
        return converter.toJson(output);
    }

    private LoginResponse executeUseCase(Request request) {
        LoginRequest input = converter.fromJson(request.body(), LoginRequest.class);
        LoginResponse output = new LoginResponse();
        new LoginUseCase(dependencies.userRepository, input, output).execute();
        return output;
    }

    private void updateCookie(Response response, LoginResponse output) {
        response.removeCookie("user-id");
        if (output.success)
            response.cookie("user-id", output.id);
    }
}

package main.routes;

import main.Main;
import main.account.LoginRequest;
import main.account.LoginResponse;
import main.account.LoginUseCase;
import spark.Request;
import spark.Response;

public class LoginRoute extends JsonRoute {
    public Object handle(Request request, Response response) throws Exception {
        LoginRequest input = converter.fromJson(request.body(), LoginRequest.class);
        LoginResponse output = new LoginResponse();
        new LoginUseCase(Main.userRepository, input, output).execute();

        response.removeCookie("user-id");
        if (output.success)
            response.cookie("user-id", output.id);

        return converter.toJson(output);
    }
}

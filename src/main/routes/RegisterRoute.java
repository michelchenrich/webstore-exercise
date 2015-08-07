package main.routes;

import com.google.gson.Gson;
import main.Main;
import main.account.RegisterRequest;
import main.account.RegisterResponse;
import main.account.RegisterUseCase;
import spark.Request;
import spark.Response;

public class RegisterRoute extends JsonRoute {
    private Gson converter = new Gson();

    public Object handle(Request request, Response response) throws Exception {
        RegisterResponse output = new RegisterResponse();
        RegisterRequest input = converter.fromJson(request.body(), RegisterRequest.class);
        new RegisterUseCase(Main.userRepository, input, output).execute();

        response.removeCookie("user-id");
        if (output.success)
            response.cookie("user-id", output.id);

        return converter.toJson(output);
    }
}

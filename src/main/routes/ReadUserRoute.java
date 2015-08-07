package main.routes;

import main.Main;
import main.account.ReadUserRequest;
import main.account.ReadUserResponse;
import main.account.ReadUserUseCase;
import spark.Request;
import spark.Response;

public class ReadUserRoute extends JsonRoute {
    public Object handle(Request request, Response response) throws Exception {
        ReadUserRequest input = new ReadUserRequest();
        input.id = request.cookie("user-id");
        ReadUserResponse output = new ReadUserResponse();
        new ReadUserUseCase(Main.userRepository, input, output).execute();
        return converter.toJson(output);
    }
}

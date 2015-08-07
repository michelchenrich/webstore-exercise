package main.routes;

import main.account.ReadUserRequest;
import main.account.ReadUserResponse;
import main.account.ReadUserUseCase;
import spark.Request;
import spark.Response;

public class ReadUserRoute extends UserRoute {
    public ReadUserRoute(Dependencies dependencies) {
        super(dependencies);
    }

    public Object handle(Request request, Response response) throws Exception {
        ReadUserResponse output = executeUseCase(request);
        return converter.toJson(output);
    }

    private ReadUserResponse executeUseCase(Request request) {
        ReadUserRequest input = new ReadUserRequest();
        input.id = request.cookie("user-id");
        ReadUserResponse output = new ReadUserResponse();
        new ReadUserUseCase(dependencies.userRepository, input, output).execute();
        return output;
    }
}

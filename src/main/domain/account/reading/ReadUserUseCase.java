package main.domain.account.reading;

import main.domain.account.User;
import main.domain.account.UserRepository;

public class ReadUserUseCase {
    private final UserRepository repository;
    private final ReadUserRequest request;
    private final ReadUserResponse response;

    public ReadUserUseCase(UserRepository repository, ReadUserRequest request, ReadUserResponse response) {
        this.repository = repository;
        this.request = request;
        this.response = response;
    }

    public void execute() {
        if (userExists())
            sendUser();
    }

    private boolean userExists() {
        return repository.hasWithId(request.id);
    }

    private void sendUser() {
        User user = repository.getById(request.id);
        response.success = true;
        response.email = user.getEmail().toString();
    }
}
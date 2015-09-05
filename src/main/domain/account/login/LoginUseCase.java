package main.domain.account.login;

import main.domain.account.Email;
import main.domain.account.Password;
import main.domain.account.User;
import main.domain.account.UserRepository;

public class LoginUseCase {
    private final UserRepository repository;
    private final LoginResponse response;
    private final Email email;
    private final Password password;

    public LoginUseCase(UserRepository repository, LoginRequest request, LoginResponse response) {
        this.repository = repository;
        password = new Password(request.password);
        email = new Email(request.email);
        this.response = response;
    }

    public void execute() {
        if (emailIsRegistered())
            checkPassword();
        else
            sendInvalidEmailOrPassword();
    }

    private boolean emailIsRegistered() {
        return repository.hasWithEmail(email);
    }

    private void checkPassword() {
        User user = repository.getByEmail(email);
        if (user.matchesPassword(password))
            sendSuccess(user.getId());
        else
            sendInvalidEmailOrPassword();
    }

    private void sendInvalidEmailOrPassword() {
        response.invalidEmailOrPassword = true;
    }

    private void sendSuccess(String id) {
        response.id = id;
        response.success = true;
    }
}

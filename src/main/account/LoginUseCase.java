package main.account;

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
        if (isPasswordCorrect(user))
            sendSuccess(user.getId());
        else
            sendInvalidEmailOrPassword();
    }

    private boolean isPasswordCorrect(User user) {
        return user.getPassword().matches(password);
    }

    private void sendInvalidEmailOrPassword() {
        response.invalidEmailOrPassword = true;
    }

    private void sendSuccess(String id) {
        response.id = id;
        response.success = true;
    }
}

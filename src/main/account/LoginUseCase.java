package main.account;

public class LoginUseCase {
    private final UserRepository repository;
    private final LoginRequest request;
    private final LoginResponse response;

    public LoginUseCase(UserRepository repository, LoginRequest request, LoginResponse response) {
        this.repository = repository;
        this.request = request;
        this.response = response;
    }

    public void execute() {
        if (emailIsRegistered())
            checkPassword();
        else
            sendInvalidEmailOrPassword();
    }

    private boolean emailIsRegistered() {
        return repository.hasWithEmail(request.email);
    }

    private void checkPassword() {
        User user = repository.getByEmail(request.email);
        if (isPasswordCorrect(user))
            sendSuccess(user.getId());
        else
            sendInvalidEmailOrPassword();
    }

    private boolean isPasswordCorrect(User user) {
        return user.getPassword().equals(request.password);
    }

    private void sendInvalidEmailOrPassword() {
        response.invalidEmailOrPassword = true;
    }

    private void sendSuccess(String id) {
        response.id = id;
        response.success = true;
    }
}

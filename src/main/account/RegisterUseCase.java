package main.account;

public class RegisterUseCase {
    private final UserRepository repository;
    private final RegisterRequest request;
    private final RegisterResponse response;

    public RegisterUseCase(UserRepository repository, RegisterRequest request, RegisterResponse response) {
        this.repository = repository;
        this.request = request;
        this.response = response;
    }

    public void execute() {
        User user = new User();
        user.setEmail(request.email);
        user.setPassword(request.password);
        repository.save(user);
        response.success = true;
        response.id = user.getId();
    }
}

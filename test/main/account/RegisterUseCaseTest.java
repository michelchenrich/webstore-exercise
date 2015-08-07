package main.account;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class RegisterUseCaseTest {
    private RegisterRequest request;
    private RegisterResponse response;
    private UserRepository repository;

    private void givenRegistrationData(String email, String password, String passwordConfirmation) {
        request.email = email;
        request.password = password;
        request.passwordConfirmation = passwordConfirmation;
    }

    private void whenRegistering() {
        new RegisterUseCase(repository, request, response).execute();
    }

    private void thenItShouldCreateAUserWithEmail(String email) {
        assertTrue(response.success);
        assertEquals(email, readUser(response.id).email);
    }

    private ReadUserResponse readUser(String id) {
        ReadUserRequest request = new ReadUserRequest();
        request.id = id;
        ReadUserResponse response = new ReadUserResponse();
        new ReadUserUseCase(repository, request, response).execute();
        return response;
    }

    @Before
    public void setUp() throws Exception {
        request = new RegisterRequest();
        response = new RegisterResponse();
        repository = new InMemoryUserRepository();
    }

    @Test
    public void whenRegisteringWithValidData_itMustReturnTheUserId_andBeSuccessful() {
        givenRegistrationData("email@host.com", "password", "password");
        whenRegistering();
        thenItShouldCreateAUserWithEmail("email@host.com");
    }
}
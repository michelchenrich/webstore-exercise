package main.account;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class LoginUseCaseTest {
    private LoginRequest request;
    private LoginResponse response;
    private UserRepository repository;

    private void givenUser(String email, String password) {
        RegisterRequest request = new RegisterRequest();
        request.email = email;
        request.password = password;
        request.passwordConfirmation = password;
        new RegisterUseCase(repository, request, new RegisterResponse()).execute();
    }

    private void givenLogInData(String email, String password) {
        request.email = email;
        request.password = password;
    }

    private void whenLoggingIn() {
        new LoginUseCase(repository, request, response).execute();
    }

    private void thenEmailOrPasswordShouldBeInvalid() {
        assertFalse(response.success);
        assertTrue(response.invalidEmailOrPassword);
    }

    private void thenItMustHaveLoggedInWithUser(String email) {
        assertTrue(response.success);
        assertFalse(response.invalidEmailOrPassword);
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
        request = new LoginRequest();
        response = new LoginResponse();
        repository = new InMemoryUserRepository();
    }

    @Test
    public void whenNoUserExists_logInMustReturnError() {
        givenLogInData("email@host.com", "password");
        whenLoggingIn();
        thenEmailOrPasswordShouldBeInvalid();
    }

    @Test
    public void givenAUser_butLoggingWithAnotherEmail_itMustReturnError() {
        givenUser("email@host.com", "password");
        givenLogInData("another.email@host.com", "password");
        whenLoggingIn();
        thenEmailOrPasswordShouldBeInvalid();
    }

    @Test
    public void givenAUser_butLoggingWithIncorrectPassword_itMustReturnError() {
        givenUser("email@host.com", "password");
        givenLogInData("email@host.com", "incorrect password");
        whenLoggingIn();
        thenEmailOrPasswordShouldBeInvalid();
    }

    @Test
    public void givenAUser_andLoggingWithCorrectData_itMustBeSuccessful() {
        givenUser("email@host.com", "password");
        givenLogInData("email@host.com", "password");
        whenLoggingIn();
        thenItMustHaveLoggedInWithUser("email@host.com");
    }
}
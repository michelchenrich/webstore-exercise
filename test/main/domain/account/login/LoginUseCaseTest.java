package main.domain.account.login;

import main.domain.account.UserRepository;
import main.domain.account.reading.ReadUserRequest;
import main.domain.account.reading.ReadUserResponse;
import main.domain.account.reading.ReadUserUseCase;
import main.domain.account.registration.RegisterRequest;
import main.domain.account.registration.RegisterResponse;
import main.domain.account.registration.RegisterUseCase;
import main.persistence.inmemory.InMemoryUserRepository;
import main.security.FakeEncryptor;
import org.junit.Assert;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
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
        new RegisterUseCase(repository, request, new RegisterResponse(), new FakeEncryptor()).execute();
    }

    private void givenLogInData(String email, String password) {
        request.email = email;
        request.password = password;
    }

    private void whenLoggingIn() {
        new LoginUseCase(repository, request, response, new FakeEncryptor()).execute();
    }

    private void thenEmailOrPasswordShouldBeInvalid() {
        assertFalse(response.success);
        assertTrue(response.invalidEmailOrPassword);
    }

    private void thenItMustHaveLoggedInWithUser(String email) {
        assertTrue(response.success);
        assertFalse(response.invalidEmailOrPassword);
        Assert.assertEquals(email, readUser(response.id).email);
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
        givenLogInData("email@host.com", "Passw0rd");
        whenLoggingIn();
        thenEmailOrPasswordShouldBeInvalid();
    }

    @Test
    public void givenAUser_butLoggingWithAnotherEmail_itMustReturnError() {
        givenUser("email@host.com", "Passw0rd");
        givenLogInData("another.email@host.com", "Passw0rd");
        whenLoggingIn();
        thenEmailOrPasswordShouldBeInvalid();
    }

    @Test
    public void givenAUser_butLoggingWithIncorrectPassword_itMustReturnError() {
        givenUser("email@host.com", "Passw0rd");
        givenLogInData("email@host.com", "incorrect password");
        whenLoggingIn();
        thenEmailOrPasswordShouldBeInvalid();
    }

    @Test
    public void givenAUser_andLoggingWithCorrectData_itMustBeSuccessful() {
        givenUser("email@host.com", "Passw0rd");
        givenLogInData("email@host.com", "Passw0rd");
        whenLoggingIn();
        thenItMustHaveLoggedInWithUser("email@host.com");
    }
}
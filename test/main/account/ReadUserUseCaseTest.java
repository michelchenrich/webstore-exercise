package main.account;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class ReadUserUseCaseTest {
    private ReadUserResponse response;
    private UserRepository repository;
    private String idToRead;

    private void givenNoRegisteredUser() {
        idToRead = "whatever";
    }

    private void givenRegisteredUser(String email) {
        RegisterRequest request = new RegisterRequest();
        request.email = email;
        request.password = request.passwordConfirmation = "some password";
        RegisterResponse response = new RegisterResponse();
        new RegisterUseCase(repository, request, response).execute();
        idToRead = response.id;
    }

    private void whenReadingUser() {
        ReadUserRequest request = new ReadUserRequest();
        request.id = idToRead;
        new ReadUserUseCase(repository, request, response).execute();
    }

    private void thenItShouldNotExist() {
        assertFalse(response.success);
        assertNull(response.email);
    }

    private void thenItShouldReturn(String email) {
        assertTrue(response.success);
        assertEquals(email, response.email);
    }

    @Before
    public void setUp() {
        response = new ReadUserResponse();
        repository = new InMemoryUserRepository();
    }

    @Test
    public void whenReadingAUserThatDoesNotExist_itMustIndicate() {
        givenNoRegisteredUser();
        whenReadingUser();
        thenItShouldNotExist();
    }

    @Test
    public void afterRegistering_itMustReturnTheRegisteredData() {
        givenRegisteredUser("email@host.com");
        whenReadingUser();
        thenItShouldReturn("email@host.com");
    }
}
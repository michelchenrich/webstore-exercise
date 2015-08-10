package main.persistence.account;

import main.domain.account.Email;
import main.domain.account.Password;
import main.domain.account.User;
import main.persistence.EntityNotFoundException;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class InMemoryUserRepositoryTest {
    private UserRepository repo;

    private User makeUser(String email, String password) {
        User user = new User();
        user.setEmail(new Email(email));
        user.setPassword(new Password(password));
        return user;
    }

    private void changeEmail(User user, String email) {
        user.setEmail(new Email(email));
    }

    private void assertEmail(User returnedUser, String email) {
        assertEquals(email, returnedUser.getEmail().toString());
    }

    private void assertPassword(User returnedUser, String password) {
        assertEquals(password, returnedUser.getPassword().toString());
    }

    private User getByEmail(String email) {
        return repo.getByEmail(new Email(email));
    }

    private boolean hasWithEmail(String email) {
        return repo.hasWithEmail(new Email(email));
    }

    @Before
    public void setUp() throws Exception {
        repo = new InMemoryUserRepository();
    }

    @Test
    public void withNoUsers_itMustNotHaveAny() {
        assertFalse(hasWithEmail("whatever"));
    }

    @Test
    public void givenAUser_itMustNotHaveWithAnotherEmail() {
        repo.save(makeUser("email@host.com", "password"));
        assertFalse(hasWithEmail("another.email@host.com"));
    }

    @Test
    public void givenAUser_itMustHaveWithTheEmail() {
        repo.save(makeUser("email@host.com", "password"));
        assertTrue(hasWithEmail("email@host.com"));
    }

    @Test(expected = EntityNotFoundException.class)
    public void whenGettingAUserWithIncorrectEmail_itMustThrowAnError() {
        getByEmail("whatever");
    }

    @Test
    public void whenGettingAUserWithCorrectEmail_itMustReturnAnotherObjectWithTheSameData() {
        User user = makeUser("email@host.com", "password");
        repo.save(user);
        User returnedUser = getByEmail("email@host.com");
        assertEmail(returnedUser, "email@host.com");
        assertPassword(returnedUser, "password");
    }

    @Test
    public void canSaveMoreThanOneUser_andRetrieveThemAll() {
        User u1 = makeUser("email1@host.com", "password1");
        repo.save(u1);
        User u2 = makeUser("email2@host.com", "password2");
        repo.save(u2);

        User returnedU1 = getByEmail("email1@host.com");
        User returnedU2 = getByEmail("email2@host.com");

        assertPassword(returnedU1, "password1");
        assertPassword(returnedU2, "password2");
    }

    @Test
    public void changingTheEmailThenSavingAgain_makesTheRepoNotFindIt() {
        User user = makeUser("old.email@host.com", "password");
        repo.save(user);
        changeEmail(user, "new.email@host.com");
        repo.save(user);
        assertFalse(hasWithEmail("old.email@host.com"));
        assertTrue(hasWithEmail("new.email@host.com"));
    }
}
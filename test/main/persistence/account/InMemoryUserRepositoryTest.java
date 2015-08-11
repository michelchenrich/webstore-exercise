package main.persistence.account;

import main.domain.account.Email;
import main.domain.account.Password;
import main.domain.account.User;
import main.persistence.EntityNotFoundException;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class InMemoryUserRepositoryTest {
    private static final Email EMAIL1 = new Email("email1@host.com");
    private static final Email EMAIL2 = new Email("email2@host.com");
    private static final Password PASSWORD1 = new Password("password1");
    private static final Password PASSWORD2 = new Password("password2");
    private UserRepository repo;

    private User makeUser(Email email, Password password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        return user;
    }

    @Before
    public void setUp() throws Exception {
        repo = new InMemoryUserRepository();
    }

    @Test
    public void withNoUsers_itMustNotHaveAny() {
        assertFalse(repo.hasWithEmail(EMAIL1));
    }

    @Test
    public void givenAUser_itMustNotHaveWithAnotherEmail() {
        repo.save(makeUser(EMAIL1, PASSWORD1));
        assertFalse(repo.hasWithEmail(EMAIL2));
    }

    @Test
    public void givenAUser_itMustHaveWithTheEmail() {
        repo.save(makeUser(EMAIL1, PASSWORD1));
        assertTrue(repo.hasWithEmail(EMAIL1));
    }

    @Test(expected = EntityNotFoundException.class)
    public void whenGettingAUserWithIncorrectEmail_itMustThrowAnError() {
        repo.getByEmail(EMAIL1);
    }

    @Test
    public void whenGettingAUserWithCorrectEmail_itMustReturnAnotherObjectWithTheSameData() {
        User user = makeUser(EMAIL1, PASSWORD1);
        repo.save(user);
        User returnedUser = repo.getByEmail(EMAIL1);
        assertEquals(EMAIL1, returnedUser.getEmail());
        assertEquals(PASSWORD1, returnedUser.getPassword());
    }

    @Test
    public void canSaveMoreThanOneUser_andRetrieveThem() {
        User u1 = makeUser(EMAIL1, PASSWORD1);
        repo.save(u1);
        User u2 = makeUser(EMAIL2, PASSWORD2);
        repo.save(u2);

        User returnedU1 = repo.getByEmail(EMAIL1);
        User returnedU2 = repo.getByEmail(EMAIL2);

        assertEquals(PASSWORD1, returnedU1.getPassword());
        assertEquals(PASSWORD2, returnedU2.getPassword());
    }

    @Test
    public void changingTheEmailThenSavingAgain_makesTheRepoNotFindIt() {
        User user = makeUser(EMAIL1, PASSWORD1);
        repo.save(user);
        user.setEmail(EMAIL2);
        repo.save(user);
        assertFalse(repo.hasWithEmail(EMAIL1));
        assertTrue(repo.hasWithEmail(EMAIL2));
    }
}
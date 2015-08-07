package main.account;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class InMemoryUserRepositoryTest {
    private UserRepository repo;

    private User makeUser(String email, String password) {
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
        assertFalse(repo.hasWithEmail("whatever"));
    }

    @Test
    public void givenAUser_itMustNotHaveWithAnotherEmail() {
        repo.save(makeUser("email@host.com", "password"));
        assertFalse(repo.hasWithEmail("another.email@host.com"));
    }

    @Test
    public void givenAUser_itMustHaveWithTheEmail() {
        repo.save(makeUser("email@host.com", "password"));
        assertTrue(repo.hasWithEmail("email@host.com"));
    }

    @Test(expected = EntityNotFoundException.class)
    public void whenGettingAUserWithIncorrectEmail_itMustThrowAnError() {
        repo.getByEmail("whatever");
    }

    @Test
    public void whenSavingAUserWithoutAnId_itMustSetAnIdToIt() {
        User user = new User();
        repo.save(user);
        assertTrue(user.hasId());
        assertFalse(user.getId().isEmpty());
    }

    @Test
    public void whenSavingAUserWithAnId_itMustNotChangeIt() {
        User user = new User();
        user.setId("id");
        repo.save(user);
        assertEquals("id", user.getId());
    }

    @Test
    public void whenGettingAUserWithCorrectEmail_itMustReturnAnotherObjectWithTheSameData() {
        User user = makeUser("email@host.com", "password");
        repo.save(user);
        User returnedUser = repo.getByEmail("email@host.com");
        assertNotSame(user, returnedUser);
        assertEquals(user.getId(), returnedUser.getId());
        assertEquals("email@host.com", returnedUser.getEmail());
        assertEquals("password", returnedUser.getPassword());
    }

    @Test
    public void canSaveMoreThanOneUser_andRetrieveThemAll() {
        User u1 = makeUser("email1@host.com", "password1");
        repo.save(u1);
        User u2 = makeUser("email2@host.com", "password2");
        repo.save(u2);

        User returnedU1 = repo.getByEmail("email1@host.com");
        User returnedU2 = repo.getByEmail("email2@host.com");

        assertEquals(returnedU1.getPassword(), "password1");
        assertEquals(returnedU2.getPassword(), "password2");
    }

    @Test
    public void changingTheEmailThenSavingAgain_makesTheRepoNotFindIt() {
        User user = makeUser("old.email@host.com", "password");
        repo.save(user);
        user.setEmail("new.email@host.com");
        repo.save(user);
        assertFalse(repo.hasWithEmail("old.email@host.com"));
        assertTrue(repo.hasWithEmail("new.email@host.com"));
    }

    @Test
    public void changesToTheUserAfterSaving_mustNotReflectInRepo() {
        User user = makeUser("email@host.com", "old password");
        repo.save(user);
        user.setPassword("new password");

        User returnedUser = repo.getByEmail("email@host.com");
        assertEquals("old password", returnedUser.getPassword());
    }

    @Test
    public void changesToTheReturnedUserBeforeSaving_mustNotReflectInRepo() {
        User user = makeUser("email@host.com", "old password");
        repo.save(user);

        User returnedUser = repo.getByEmail("email@host.com");
        returnedUser.setPassword("new password");
        assertEquals("old password", repo.getByEmail("email@host.com").getPassword());
    }

    @Test(expected = EntityNotFoundException.class)
    public void withNoUsers_gettingById_mustThrowAnError() {
        repo.getById("whatever");
    }

    @Test
    public void afterSavingAUser_repoCanGetItBackByItsId() {
        User user = new User();
        user.setEmail("email@host.com");

        repo.save(user);

        User returnedUser = repo.getById(user.getId());
        assertEquals(user.getEmail(), returnedUser.getEmail());
    }

    @Test
    public void afterSavingAUser_repoHasItByItsId() {
        User user = new User();
        repo.save(user);
        assertTrue(repo.hasWithId(user.getId()));
    }

    @Test
    public void withNoUsers_repoDoesNotKnowAnyUsersById() {
        assertFalse(repo.hasWithId("whatever"));
    }
}
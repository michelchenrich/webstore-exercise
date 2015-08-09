package main.account;

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

    private void changePassword(User user, String password) {
        user.setPassword(new Password(password));
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
        User returnedUser = getByEmail("email@host.com");
        assertNotSame(user, returnedUser);
        assertEquals(user.getId(), returnedUser.getId());
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

    @Test
    public void changesToTheUserAfterSaving_mustNotReflectInRepo() {
        User user = makeUser("email@host.com", "old password");
        repo.save(user);
        changePassword(user, "new password");

        User returnedUser = getByEmail("email@host.com");
        assertPassword(returnedUser, "old password");
    }

    @Test
    public void changesToTheReturnedUserBeforeSaving_mustNotReflectInRepo() {
        User user = makeUser("email@host.com", "old password");
        repo.save(user);

        User returnedUser = getByEmail("email@host.com");
        changePassword(returnedUser, "new password");
        assertPassword(getByEmail("email@host.com"), "old password");
    }

    @Test(expected = EntityNotFoundException.class)
    public void withNoUsers_gettingById_mustThrowAnError() {
        repo.getById("whatever");
    }

    @Test
    public void afterSavingAUser_repoCanGetItBackByItsId() {
        User user = new User();
        String email = "email@host.com";
        changeEmail(user, email);

        repo.save(user);

        User returnedUser = repo.getById(user.getId());
        assertEmail(returnedUser, "email@host.com");
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
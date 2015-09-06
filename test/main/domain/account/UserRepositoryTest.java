package main.domain.account;

import main.domain.Repository;
import main.domain.RepositoryTest;
import main.persistence.EntityNotFoundException;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public abstract class UserRepositoryTest extends RepositoryTest<User> {
    private static final Email EMAIL1 = new Email("email1@host.com");
    private static final Email EMAIL2 = new Email("email2@host.com");
    private static final EncryptedPassword PASSWORD1 = new EncryptedPassword("", "password1");
    private static final EncryptedPassword PASSWORD2 = new EncryptedPassword("", "password2");
    private UserRepository repository;

    private User makeUser(Email email, EncryptedPassword password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        return user;
    }

    protected abstract UserRepository getRepository();

    protected Repository<User> getAbstractRepository() {
        return getRepository();
    }

    protected User makeNewEntity() {
        return new User();
    }

    protected User makeEntityWithId(String id) {
        User user = makeUser(EMAIL1, PASSWORD1);
        user.setId(id);
        return user;
    }

    protected void changeEntity(User user) {
        user.setEmail(EMAIL2);
        user.setPassword(PASSWORD2);
    }

    protected void assertEntityHasSameValues(User original, User saved) {
        assertEquals(original.getId(), saved.getId());
        assertEquals(original.getEmail(), saved.getEmail());
        assertEquals(original.getPassword(), saved.getPassword());
    }

    protected void assertEntityDoesNotHaveSameValues(User original, User saved) {
        assertEquals(original.getId(), saved.getId());
        assertNotEquals(original.getEmail(), saved.getEmail());
        assertNotEquals(original.getPassword(), saved.getPassword());
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
        repository = getRepository();
    }

    @Test
    public void withNoUsers_itMustNotHaveAny() {
        assertFalse(repository.hasWithEmail(EMAIL1));
    }

    @Test
    public void givenNullEmailText_mustNotCrash() {
        assertFalse(repository.hasWithEmail(new Email(null)));
    }

    @Test
    public void givenAUser_itMustNotHaveWithAnotherEmail() {
        repository.save(makeUser(EMAIL1, PASSWORD1));
        assertFalse(repository.hasWithEmail(EMAIL2));
    }

    @Test
    public void givenAUser_itMustHaveWithTheEmail() {
        repository.save(makeUser(EMAIL1, PASSWORD1));
        assertTrue(repository.hasWithEmail(EMAIL1));
    }

    @Test(expected = EntityNotFoundException.class)
    public void whenGettingAUserWithIncorrectEmail_itMustThrowAnError() {
        repository.getByEmail(EMAIL1);
    }

    @Test
    public void whenGettingAUserWithCorrectEmail_itMustReturnAnotherObjectWithTheSameData() {
        User user = makeUser(EMAIL1, PASSWORD1);
        repository.save(user);
        User returnedUser = repository.getByEmail(EMAIL1);
        assertEquals(EMAIL1, returnedUser.getEmail());
        assertEquals(PASSWORD1, returnedUser.getPassword());
    }

    @Test
    public void canSaveMoreThanOneUser_andRetrieveThem() {
        User u1 = makeUser(EMAIL1, PASSWORD1);
        repository.save(u1);
        User u2 = makeUser(EMAIL2, PASSWORD2);
        repository.save(u2);

        User returnedU1 = repository.getByEmail(EMAIL1);
        User returnedU2 = repository.getByEmail(EMAIL2);

        assertEquals(PASSWORD1, returnedU1.getPassword());
        assertEquals(PASSWORD2, returnedU2.getPassword());
    }

    @Test
    public void changingTheEmailThenSavingAgain_makesTheRepoNotFindIt() {
        User user = makeUser(EMAIL1, PASSWORD1);
        repository.save(user);
        user.setEmail(EMAIL2);
        repository.save(user);
        assertFalse(repository.hasWithEmail(EMAIL1));
        assertTrue(repository.hasWithEmail(EMAIL2));
    }
}

package main.domain.account;

import main.domain.EntityTest;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class UserTest extends EntityTest<User> {
    protected User makeNewSubject() {
        return new User();
    }

    protected User makeSubjectWithData() {
        User user = makeNewSubject();
        user.setId("id");
        user.setEmail(new Email("email@host.com"));
        user.setPassword(new EncryptedPassword("", "password"));
        return user;
    }

    protected void assertSameData(User entity, User copy) {
        assertEquals(entity.getId(), copy.getId());
        assertEquals(entity.getEmail(), copy.getEmail());
        assertEquals(entity.getPassword(), copy.getPassword());
    }

    @Test
    public void newUser_hasEmptyAttributes() {
        assertEquals(Email.EMPTY, subject.getEmail());
        assertEquals(EncryptedPassword.EMPTY, subject.getPassword());
    }

    @Test
    public void userKeepsTheEmailSet() {
        Email email = new Email("whatever");
        subject.setEmail(email);
        assertEquals(email, subject.getEmail());
    }

    @Test
    public void userKeepsThePasswordSet() {
        EncryptedPassword password = new EncryptedPassword("", "whatever");
        subject.setPassword(password);
        assertEquals(password, subject.getPassword());
    }
}
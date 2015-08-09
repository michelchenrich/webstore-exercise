package main.account;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class UserTest {
    private User user;

    @Before
    public void setUp() throws Exception {
        user = new User();
    }

    @Test
    public void newUser_hasEmptyAttributes() {
        assertId(user, false, "");
        assertEmail(user, "");
        assertPassword(user, "");
    }

    @Test
    public void afterSettingAnId_itMustHaveTheId() {
        setId(user, "id");
        assertId(user, true, "id");
    }

    @Test
    public void settingAnIdWithOnlySpacesIsNotValid() {
        setId(user, "   ");
        assertId(user, false, "");
    }

    @Test
    public void settingANullIdIsNotValid() {
        setId(user, null);
        assertId(user, false, "");
    }

    @Test
    public void userKeepsTheEmailSet_asIs() {
        String email = "  whatever   ";
        setEmail(user, email);
        assertEmail(user, email);
    }

    @Test
    public void whenSettingTheEmailToNull_itMustBeEmptied() {
        setEmail(user, "first");
        user.setEmail(null);
        assertEmail(user, "");
    }

    @Test
    public void userKeepsThePasswordSet_asIs() {
        setPassword(user, "  whatever   ");
        assertPassword(user, "  whatever   ");
    }

    @Test
    public void whenSettingThePasswordToNull_itMustBeEmptied() {
        setPassword(user, "first");
        user.setPassword(null);
        assertPassword(user, "");
    }

    @Test
    public void copyHasTheSameDataAsOriginal() {
        setId(user, "id");
        setEmail(user, "email@host.com");
        setPassword(user, "password");

        User copy = user.copy();

        assertId(copy, true, "id");
        assertEmail(copy, "email@host.com");
        assertPassword(copy, "password");
    }

    private void setPassword(User user, String password) {
        user.setPassword(new Password(password));
    }

    private void setEmail(User user, String email) {
        user.setEmail(new Email(email));
    }

    private void setId(User user, String id) {
        user.setId(id);
    }

    private void assertId(User user, boolean hasId, String id) {
        assertEquals(hasId, user.hasId());
        assertEquals(id, user.getId());
    }

    private void assertEmail(User user, String email) {
        assertEquals(email, user.getEmail().toString());
    }

    private void assertPassword(User user, String password) {
        assertEquals(password, user.getPassword().toString());
    }
}
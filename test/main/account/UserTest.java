package main.account;

import static org.junit.Assert.*;
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
        assertFalse(user.hasId());
        assertEquals("", user.getId());
        assertEquals("", user.getEmail());
        assertEquals("", user.getPassword());
    }

    @Test
    public void afterSettingAnId_itMustHaveTheId() {
        user.setId("id");
        assertTrue(user.hasId());
        assertEquals("id", user.getId());
    }

    @Test
    public void settingAnIdWithOnlySpacesIsNotValid() {
        user.setId("   ");
        assertFalse(user.hasId());
        assertEquals("", user.getId());
    }

    @Test
    public void settingANullIdIsNotValid() {
        user.setId(null);
        assertFalse(user.hasId());
        assertEquals("", user.getId());
    }

    @Test
    public void userKeepsTheEmailSet_asIs() {
        String email = "  whatever   ";
        user.setEmail(email);
        assertEquals(email, user.getEmail());
    }

    @Test
    public void whenSettingTheEmailToNull_itMustBeEmptied() {
        user.setEmail("first");
        user.setEmail(null);
        assertEquals("", user.getEmail());
    }

    @Test
    public void userKeepsThePasswordSet_asIs() {
        String password = "  whatever   ";
        user.setPassword(password);
        assertEquals(password, user.getPassword());
    }

    @Test
    public void whenSettingThePasswordToNull_itMustBeEmptied() {
        user.setPassword("first");
        user.setPassword(null);
        assertEquals("", user.getPassword());
    }

    @Test
    public void copyHasTheSameDataAsOriginal() {
        user.setId("id");
        user.setEmail("email@host.com");
        user.setPassword("password");

        User copy = user.copy();
        assertEquals("id", copy.getId());
        assertEquals("email@host.com", copy.getEmail());
        assertEquals("password", copy.getPassword());
    }
}
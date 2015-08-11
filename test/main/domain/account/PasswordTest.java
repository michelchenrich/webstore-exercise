package main.domain.account;

import static org.junit.Assert.*;
import org.junit.Test;

public class PasswordTest {
    private Password makePassword(String value) {
        return new Password(value);
    }

    @Test
    public void giveNullValue_printsEmptyString() {
        Password password = makePassword(null);
        assertEquals("", password.toString());
    }

    @Test
    public void itPrintsItsExactValue() {
        Password password = makePassword("  ABC d e fGH ");
        assertEquals("  ABC d e fGH ", password.toString());
    }

    @Test
    public void givenNullValue_equalsAnotherWithEmptyValue() {
        Password nullPassword = makePassword(null);
        Password emptyPassword = makePassword("");
        assertTrue(nullPassword.equals(emptyPassword));
    }

    @Test
    public void anyDifferenceInValue_andItWillNotEqualTheOther() {
        Password correctPassword = makePassword("  ABC d e fGH ");
        Password incorrectPassword = makePassword(" ABC d e fGH ");
        assertFalse(correctPassword.equals(incorrectPassword));
    }

    @Test
    public void givenNullValue_isNotValid() {
        Password password = makePassword(null);
        assertFalse(password.isValid());
    }

    @Test
    public void givenEmptyValue_isNotValid() {
        Password password = makePassword("");
        assertFalse(password.isValid());
    }

    @Test
    public void givenPasswordWithSevenCharacters_isNotValid() {
        Password password = makePassword("Abcdef7");
        assertFalse(password.isValid());
    }

    @Test
    public void givenPasswordWithSixCharacters_isNotValid() {
        Password password = makePassword("Abcde6");
        assertFalse(password.isValid());
    }

    @Test
    public void givenPasswordWithEightCharacters_isValid() {
        Password password = makePassword("Abcdefg8");
        assertTrue(password.isValid());
    }

    @Test
    public void givenPasswordWithNoUpperCaseLetter_isNotValid() {
        Password password = makePassword("abcdefg8");
        assertFalse(password.isValid());
    }

    @Test
    public void givenPasswordWithNoLowerCaseLetter_isNotValid() {
        Password password = makePassword("ABCDEFG8");
        assertFalse(password.isValid());
    }

    @Test
    public void givenPasswordWithNoNumber_isNotValid() {
        Password password = makePassword("ABCdefGH");
        assertFalse(password.isValid());
    }

    @Test
    public void givenValidPasswordSurroundedBySpaces_itIsStillValid() {
        Password password = makePassword("  ABC d e fG8 ");
        assertTrue(password.isValid());
    }
}
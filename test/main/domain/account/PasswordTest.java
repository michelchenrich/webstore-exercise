package main.domain.account;

import static org.junit.Assert.*;
import org.junit.Test;

public class PasswordTest {
    @Test
    public void giveNullValue_printsEmptyString() {
        Password password = new Password(null);
        assertEquals("", password.toString());
    }

    @Test
    public void itPrintsItsExactValue() {
        Password password = new Password("  ABC d e fGH ");
        assertEquals("  ABC d e fGH ", password.toString());
    }

    @Test
    public void givenNullValue_equalsAnotherWithEmptyValue() {
        Password nullPassword = new Password(null);
        Password emptyPassword = new Password("");
        assertTrue(nullPassword.equals(emptyPassword));
    }

    @Test
    public void anyDifferenceInValue_andItWillNotEqualTheOther() {
        Password correctPassword = new Password("  ABC d e fGH ");
        Password incorrectPassword = new Password(" ABC d e fGH ");
        assertFalse(correctPassword.equals(incorrectPassword));
    }

    @Test
    public void givenNullValue_isNotValid() {
        Password password = new Password(null);
        assertFalse(password.isValid());
    }

    @Test
    public void givenEmptyValue_isNotValid() {
        Password password = new Password("");
        assertFalse(password.isValid());
    }

    @Test
    public void givenPasswordWithSevenCharacters_isNotValid() {
        Password password = new Password("Abcdef7");
        assertFalse(password.isValid());
    }

    @Test
    public void givenPasswordWithSixCharacters_isNotValid() {
        Password password = new Password("Abcde6");
        assertFalse(password.isValid());
    }

    @Test
    public void givenPasswordWithEightCharacters_isValid() {
        Password password = new Password("Abcdefg8");
        assertTrue(password.isValid());
    }

    @Test
    public void givenPasswordWithNoUpperCaseLetter_isNotValid() {
        Password password = new Password("abcdefg8");
        assertFalse(password.isValid());
    }

    @Test
    public void givenPasswordWithNoLowerCaseLetter_isNotValid() {
        Password password = new Password("ABCDEFG8");
        assertFalse(password.isValid());
    }

    @Test
    public void givenPasswordWithNoNumber_isNotValid() {
        Password password = new Password("ABCdefGH");
        assertFalse(password.isValid());
    }

    @Test
    public void givenValidPasswordSurroundedBySpaces_itIsStillValid() {
        Password password = new Password("  ABC d e fG8 ");
        assertTrue(password.isValid());
    }
}
package main.account;

import static org.junit.Assert.*;
import org.junit.Test;

public class EmailTest {
    @Test
    public void givenNullValue_printsEmptyString() {
        Email email = new Email(null);
        assertEquals("", email.toString());
    }

    @Test
    public void givenNullValue_equalsAnotherWithEmptyValue() {
        Email nullEmail = new Email(null);
        Email emptyEmail = new Email("");
        assertTrue(nullEmail.equals(emptyEmail));
    }

    @Test
    public void givenEmailSurroundedBySpaces_itIgnoresTheSpaces() {
        Email trimmedEmail = new Email("   email@host.com  ");
        assertEquals("email@host.com", trimmedEmail.toString());
    }

    @Test
    public void givenEmailSurroundedBySpaces_itShouldEqualAnotherWithoutTheSpaces() {
        Email trimmedEmail = new Email("   email@host.com  ");
        Email email = new Email("email@host.com");
        assertTrue(trimmedEmail.equals(email));
    }

    @Test
    public void givenCorrectEmail_itPrintsItAsIs() {
        Email email = new Email("email@host.com");
        assertEquals("email@host.com", email.toString());
    }

    @Test
    public void anyDifferenceInValue_andItWillNotEqualTheOther() {
        Email email1 = new Email("email1@host.com");
        Email email2 = new Email("email2@host.com");
        assertFalse(email1.equals(email2));
    }

    @Test
    public void givenCorrectEmail_itIsValid() {
        Email email = new Email("email@host.com");
        assertTrue(email.isValid());
    }

    @Test
    public void givenEmailWithTwoRepeatedAtSymbols_itIsInvalid() {
        Email email = new Email("email@@host.com");
        assertFalse(email.isValid());
    }

    @Test
    public void givenEmailWithAnExtraAtSymbolAtTheEnd_itIsInvalid() {
        Email email = new Email("email@host.com@");
        assertFalse(email.isValid());
    }

    @Test
    public void givenEmailWithoutAHostPart_itIsInvalid() {
        Email email = new Email("email@");
        assertFalse(email.isValid());
    }

    @Test
    public void givenEmailWithoutAccountPart_itIsInvaid() {
        Email email = new Email("@host.com");
        assertFalse(email.isValid());
    }
}
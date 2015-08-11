package main.domain.account;

import static org.junit.Assert.*;
import org.junit.Test;

public class EmailTest {
    private Email makeEmail(String value) {
        return new Email(value);
    }

    @Test
    public void givenNullValue_printsEmptyString() {
        Email email = makeEmail(null);
        assertEquals("", email.toString());
    }

    @Test
    public void givenNullValue_equalsAnotherWithEmptyValue() {
        Email nullEmail = makeEmail(null);
        Email emptyEmail = makeEmail("");
        assertTrue(nullEmail.equals(emptyEmail));
    }

    @Test
    public void givenEmailSurroundedBySpaces_itIgnoresTheSpaces() {
        Email trimmedEmail = makeEmail("   email@host.com  ");
        assertEquals("email@host.com", trimmedEmail.toString());
    }

    @Test
    public void givenEmailSurroundedBySpaces_itShouldEqualAnotherWithoutTheSpaces() {
        Email trimmedEmail = makeEmail("   email@host.com  ");
        Email email = makeEmail("email@host.com");
        assertTrue(trimmedEmail.equals(email));
    }

    @Test
    public void givenCorrectEmail_itPrintsItAsIs() {
        Email email = makeEmail("email@host.com");
        assertEquals("email@host.com", email.toString());
    }

    @Test
    public void anyDifferenceInValue_andItWillNotEqualTheOther() {
        Email email1 = makeEmail("email1@host.com");
        Email email2 = makeEmail("email2@host.com");
        assertFalse(email1.equals(email2));
    }

    @Test
    public void givenCorrectEmail_itIsValid() {
        Email email = makeEmail("email@host.com");
        assertTrue(email.isValid());
    }

    @Test
    public void givenEmailWithTwoRepeatedAtSymbols_itIsInvalid() {
        Email email = makeEmail("email@@host.com");
        assertFalse(email.isValid());
    }

    @Test
    public void givenEmailWithAnExtraAtSymbolAtTheEnd_itIsInvalid() {
        Email email = makeEmail("email@host.com@");
        assertFalse(email.isValid());
    }

    @Test
    public void givenEmailWithoutAHostPart_itIsInvalid() {
        Email email = makeEmail("email@");
        assertFalse(email.isValid());
    }

    @Test
    public void givenEmailWithoutAccountPart_itIsInvalid() {
        Email email = makeEmail("@host.com");
        assertFalse(email.isValid());
    }
}
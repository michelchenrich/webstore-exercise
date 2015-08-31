package main.domain.product;

import static org.junit.Assert.*;
import org.junit.Test;

public class QuantityTest {
    @Test
    public void givenAnIntegerAsString_itShouldConvertBackToIt() {
        assertEquals(10, new Quantity("10").toInteger());
    }

    @Test
    public void givenAnInvalidIntegerString_itShouldConvertToZero() {
        assertEquals(0, new Quantity("not a number").toInteger());
    }

    @Test
    public void givenNullValue_itShouldConvertToZero() {
        assertEquals(0, new Quantity(null).toInteger());
    }

    @Test
    public void itIsInvalidWhenNotANumber() {
        assertFalse(new Quantity("not a number").isValid());
    }

    @Test
    public void itIsInvalidWhenNegative() {
        assertFalse(new Quantity("-1").isValid());
    }

    @Test
    public void itIsValidWhenZeroOrGreater() {
        assertTrue(new Quantity("0").isValid());
        assertTrue(new Quantity("1").isValid());
    }

    @Test
    public void validNumbersAreEqualWhenTheyHaveTheSameValue() {
        assertEquals(new Quantity("10"), new Quantity("10"));
    }

    @Test
    public void invalidNumbers_forDifferentReasons_areEqualNonetheless() {
        assertEquals(new Quantity("not a number"), new Quantity(null));
    }
}
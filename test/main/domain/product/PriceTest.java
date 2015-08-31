package main.domain.product;

import static org.junit.Assert.*;
import org.junit.Test;

public class PriceTest {
    @Test
    public void givenADoubleAsString_itConvertBackToTheDouble() {
        assertEquals(10.0, new Price("10.0").toDouble(), .001);
    }

    @Test
    public void givenAnInvalidDoubleString_itShouldConvertToZero() {
        assertEquals(0.0, new Price("not a number").toDouble(), .001);
    }

    @Test
    public void givenNullValue_itShouldConvertToZero() {
        assertEquals(0.0, new Price(null).toDouble(), .001);
    }

    @Test
    public void itIsNotValidWhenNegative() {
        assertFalse(new Price("-10.0").isValid());
    }

    @Test
    public void itIsNotValidWhenZero() {
        assertFalse(new Price("0.0").isValid());
    }

    @Test
    public void itIsNotValidWhenGreaterThanZero() {
        assertTrue(new Price("0.00001").isValid());
    }

    @Test
    public void validPricesAreEqualWhenTheyHaveTheSameValue() {
        assertEquals(new Price("10.0"), new Price("10.0"));
    }

    @Test
    public void invalidPrices_forDifferentReasons_areEqualNonetheless() {
        assertEquals(new Price("not a number"), new Price(null));
    }
}
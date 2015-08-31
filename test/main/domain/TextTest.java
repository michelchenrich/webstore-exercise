package main.domain;

import static org.junit.Assert.*;
import org.junit.Test;

public class TextTest {
    @Test
    public void givenNullValue_itPrintsAnEmptyString() {
        assertEquals("", new Text(null).toString());
    }

    @Test
    public void givenEmptyValue_itEqualsANullValue() {
        assertEquals(new Text(""), new Text(null));
    }

    @Test
    public void givenTextSurroundedBySpaces_itShouldOnlyKeepTheTrimmedText() {
        assertEquals("surrounded by spaces", new Text("   surrounded by spaces   ").toString());
    }

    @Test
    public void givenTextSurroundedBySpaces_itShouldEqualAnotherWithoutTheSpaces() {
        assertEquals(new Text("   surrounded by spaces   "), new Text("surrounded by spaces"));
    }

    @Test
    public void givenValidText_itPrintsAsIs() {
        assertEquals("A valid text", new Text("A valid text").toString());
    }

    @Test
    public void givenAnyDifference_theyShouldNotEqual() {
        assertNotEquals(new Text("some text"), new Text("som3 text"));
    }

    @Test
    public void comparingWithAStringWithSameValue_shouldNotEqual() {
        assertNotEquals("some text", new Text("some text"));
    }

    @Test
    public void anEmptyTextIsNotValid() {
        assertFalse(new Text("").isValid());
    }

    @Test
    public void aTextWithAtLeastOneCharacterIsValid() {
        assertTrue(new Text("a").isValid());
    }
}
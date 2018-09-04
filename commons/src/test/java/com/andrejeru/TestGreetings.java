package com.andrejeru;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestGreetings {

    @Test
    public void testGreeting() {
        var greetings = new Greetings();

        assertEquals("Hello", greetings.greeting());
    }
}

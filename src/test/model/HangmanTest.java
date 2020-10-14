package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HangmanTest {

    Hangman h;

    @BeforeEach
    public void setup() {
        h = new Hangman();
    }

    @Test
    public void wrongGuessTest() {
        h.wrongGuess();
        assertEquals(5, h.getLives());
        h.wrongGuess();
        assertEquals(4, h.getLives());
    }

    @Test
    public void randomWordTestWordInDomain() {

    }

    @Test
    public void randomWordTestWordNotInDomain() {

    }
}
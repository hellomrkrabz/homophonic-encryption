package pl.polsl.anna.pogorzelska.tests;

/**
 *Responsible for testing Validator class.
 * @author Anna Pogorzelska
 * @version 1.0
 */

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

public class ValidatorTest {
    
    @ParameterizedTest
    @ValueSource(strings = {"e", "d", "q"})
    public void testValidityOfModeWithCorrectInput(String candidate) {
        assertEquals(true, candidate.matches("^[dqe]$"));
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"eine Katze", "two cats", "TRZY KOTY"})
    public void testValidityOfStringWithCorrectInput(String candidate) {
        assertEquals(true, candidate.matches("[a-zA-Z\\s]+"));
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"909", "991 100 206", "407552"})
    public void testValidityOfNumbersWithCorrectInput(String candidate) {
        assertEquals(true, candidate.matches("[0-9\\s]+"));
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"exit", "d!", "1 more"})
    public void testValidityOfModeWithIncorrectInput(String candidate) {
        assertEquals(false, candidate.matches("^[dqe]$"));
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"один кот", "2 cats", "!kot"})
    public void testValidityOfStringWithIncorrectInput(String candidate) {
        assertEquals(false, candidate.matches("[a-zA-Z\\s]+"));
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"9@9", "twelve", "111 cats"})
    public void testValidityOfNumbersWithIncorrectInput(String candidate) {
        assertEquals(false, candidate.matches("[0-9\\s]+"));
    }
}


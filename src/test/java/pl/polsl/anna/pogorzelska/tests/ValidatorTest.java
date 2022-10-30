package pl.polsl.anna.pogorzelska.tests;

/**
 *Responsible for testing Validator class.
 * @author Anna Pogorzelska
 * @version 1.0
 */

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import org.junit.platform.commons.util.StringUtils;

public class ValidatorTest {
    
    @ParameterizedTest
    @ValueSource(strings = {"e", "d", "q"})
    public void testValidityOfMode(String candidate) {
        assertEquals(true, candidate.matches("^[dqe]$"));
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"cat", "two cats"})
    public void testValidityOfString(String candidate) {
        assertEquals(true, candidate.matches("[a-zA-Z\s]+"));
    }
    
    @ParameterizedTest
    @ValueSource(strings = {"909", "991100206 407552"})
    public void testValidityOfNumbers(String candidate) {
        assertEquals(true, candidate.matches("[0-9\s]+"));
    }
}

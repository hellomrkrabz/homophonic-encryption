package pl.polsl.anna.pogorzelska.tests;

/**
 *Responsible for testing Transcriptor class.
 * @author Anna Pogorzelska
 * @version 1.0
 */

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import pl.polsl.anna.pogorzelska.model.Transcriptor;
import pl.polsl.anna.pogorzelska.model.exceptions.NotNumberInputException;
import pl.polsl.anna.pogorzelska.model.exceptions.ReadFileFailureException;

public class TranscriptorTest {
    
    private Transcriptor transcriptor;
    
    @BeforeEach 
    public void setTranscriptor() throws ReadFileFailureException {
        transcriptor = new Transcriptor();
    }
    
    @Test
    public void testDecryptionWithCorrectInput() {
        try {  
            assertEquals("kot", transcriptor.decrypiton("399156514"));
        } catch (NotNumberInputException ex) {
            fail("Correct input, incorrect result");
        }
    }
    
      @Test
    public void testDecryptionWithIncorrectInput() {
        try {
            assertNotEquals("cat", transcriptor.decrypiton("399156514"));
            
        } catch (NotNumberInputException ex) {
            fail("Incorrect input, correct result");
        }
    }  
    
}

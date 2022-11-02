package pl.polsl.anna.pogorzelska.tests;

/**
 *Responsible for testing Transcriptor class.
 * @author Anna Pogorzelska
 * @version 1.0
 */

import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import pl.polsl.anna.pogorzelska.model.Transcriptor;
import pl.polsl.anna.pogorzelska.model.exceptions.NonEnglishInputException;
import pl.polsl.anna.pogorzelska.model.exceptions.NotNumberInputException;
import pl.polsl.anna.pogorzelska.model.exceptions.ReadFileFailureException;

public class TranscriptorTest {
    
    private Transcriptor transcriptor;
    
    @BeforeEach 
    public void setTranscriptor() throws ReadFileFailureException {
        transcriptor = new Transcriptor();
    }
    
    @Test
    public void testEncryptionWithCorrectInput() {
        try {
            assertEquals("571725", transcriptor.encryption("hf"));
        } catch (NonEnglishInputException ex) {
            fail("Correct input, incorrect result");
        }
    }
    
    @Test
    public void testEncryptionWithIncorrectInput() {
        try {
            assertNotEquals("571725", transcriptor.encryption("cat"));
        } catch (NonEnglishInputException ex) {
                        fail("Incorrect input, correct result");

        }
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

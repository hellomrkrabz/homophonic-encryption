package pl.polsl.anna.pogorzelska.tests;

/**
 *Responsible for testing FileManager class.
 * @author Anna Pogorzelska
 * @version 1.0
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import pl.polsl.anna.pogorzelska.model.FileManager;
import pl.polsl.anna.pogorzelska.model.exceptions.ReadFileFailureException;

public class FileManagerTest {
    
    private FileManager manager;
    
    @BeforeEach 
    public void setFileManager()  {
        manager = new FileManager();
    }
    
    @Test
    public void testOpeningFileWithCorrectInput() {
        HashMap<String, ArrayList<String>> expectedResult = new HashMap<>();
        expectedResult.put("A", new ArrayList<>(Arrays.asList("123")));
        expectedResult.put("a", new ArrayList<>(Arrays.asList("222")));
        expectedResult.put("B", new ArrayList<>(Arrays.asList("897")));
        expectedResult.put("b", new ArrayList<>(Arrays.asList("668")));
        try {
            assertEquals(expectedResult, manager.readFile("test.txt"));
        } catch (ReadFileFailureException ex) {
            fail("Incorrect file read");
        }
    }
    
    @Test
    public void testOpeningFileWithIncorrectInput() {
        HashMap<String, ArrayList<String>> expectedResult = new HashMap<>();
        expectedResult.put("c", new ArrayList<>(Arrays.asList("228")));
        expectedResult.put("i", new ArrayList<>(Arrays.asList("9088")));
        expectedResult.put("Z", new ArrayList<>(Arrays.asList("83")));
        expectedResult.put("I", new ArrayList<>(Arrays.asList("9")));
        try {
            assertNotEquals(expectedResult, manager.readFile("test.txt"));
        } catch (ReadFileFailureException ex) {
            fail("Incorrect file read");
        }
    }
}

package pl.polsl.anna.pogorzelska.model.exceptions;

/**
 *
 * @author Anna Pogorzelska
 * @version 1.0
 */
public class NonEnglishInputException extends InvalidUserInputException {
    
    public NonEnglishInputException(String message) {
        super(message);
    }
    
}

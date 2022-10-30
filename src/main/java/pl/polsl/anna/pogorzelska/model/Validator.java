package pl.polsl.anna.pogorzelska.model;

import pl.polsl.anna.pogorzelska.model.exceptions.NonEnglishInputException;
import pl.polsl.anna.pogorzelska.model.exceptions.NotNumberInputException;
import pl.polsl.anna.pogorzelska.model.exceptions.NotValidModeException;

/** 
 * Class responsible for validating inputs provided by the user.
 * 
 * @author Anna Pogorzelska
 * @version 1.1
 */

public class Validator {
    
     /**
     * Function responsible for checking whether the input contains only letters from english alphabet.
     *
     * @param message message provided by the user
     * @return Information whether the input contains only letters from english alphabet and spaces
     */
    
    private boolean inLatinLettersRange(String message) {
        return message.matches("[a-zA-Z\s]+");
    }
    
    /**
     * Function responsible for checking whether the input contains only numbers.
     *
     * @param message message provided by the user
     * @return Information whether the input contains only numbers and spaces
     */
    
    private boolean inNumberRange(String message) {
        return message.matches("[0-9\s]+");
    }
    
    /**
     * Function responsible for checking whether the provided mode fits the requirements
     * @param mode the input provided by the user
     * @return Information whether the conditions are met
     * @throws NotValidModeException 
     */
    
    public boolean checkValidityOfMode(String mode) throws NotValidModeException {
        if (mode.matches("^[dqe]$") == false) {
            throw new NotValidModeException("Please provide a proper mode value; e.g. '-d' , '-e' ");
        }
        return true;
    }
    
    /**
     * Function responsible for providing information whether the validity check was successful
     * @param message input provided by the user
     * @throws NonEnglishInputException 
     */
    
    public void checkValidityOfString(String message) throws NonEnglishInputException {
        if (inLatinLettersRange(message) == false)
            throw new NonEnglishInputException("Content of input outside english alphabet.");
    }
    
    /**
     * Function responsible for providing information whether the validity check was successful
     * @param message input provided by the user
     * @throws NotNumberInputException 
     */
    
    public void checkValidityOfNumbers(String message) throws NotNumberInputException {
        if (inNumberRange(message) == false)
            throw new NotNumberInputException("Content of input outside number range.");
    }
    

}

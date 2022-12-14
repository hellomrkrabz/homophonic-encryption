package pl.polsl.anna.pogorzelska.htmlhomophonicencryption.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import pl.polsl.anna.pogorzelska.htmlhomophonicencryption.exceptions.NonEnglishInputException;
import pl.polsl.anna.pogorzelska.htmlhomophonicencryption.exceptions.NotNumberInputException;
import pl.polsl.anna.pogorzelska.htmlhomophonicencryption.exceptions.NotValidModeException;

/** 
 * Class responsible for validating inputs provided by the user.
 * 
 * @author Anna Pogorzelska
 * @version 2.0
 */

public class Validator {
    
    
     /**
     * Function responsible for checking whether the input contains only letters from english alphabet.
     *
     * @param message message provided by the user
     * @return Information whether the input contains only letters from english alphabet and spaces
     */
    
    private boolean inLatinLettersRange(String message) {
        return message.matches("[a-zA-Z\\s]+");
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
     * Function responsible for checking whether provided input is in alphabet range.
     *
     * @param encryptedMessage the message to decrypt
     * @param dictionary alphabet structure
     * @return whether element of the input is contained in the alphabet file
     */  
    public boolean alphabetCorrectness(String encryptedMessage, HashMap<String,ArrayList<String>> dictionary) {
        boolean correctInput = false;
        encryptedMessage = encryptedMessage.replaceAll(" ", "");
        String currentlyDecrypted ="";
        for (int i = 0; i < encryptedMessage.length(); i++) {
                currentlyDecrypted += encryptedMessage.charAt(i);
                if (currentlyDecrypted.length() == 3) {
                    for (Map.Entry<String,ArrayList<String>> entry : dictionary.entrySet()) {
                        if (entry.getValue().contains(currentlyDecrypted)) 
                            correctInput = true;
                    }
                    currentlyDecrypted = "";
                }
            }
        return correctInput;
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
     * @return 
     * @throws NonEnglishInputException 
     */
    
    public boolean checkValidityOfString(String message) throws NonEnglishInputException {
        if (inLatinLettersRange(message) == false) 
            throw new NonEnglishInputException("Content of input outside english alphabet.");
        else
            return true;
    }
    
    /**
     * Function responsible for providing information whether the validity check was successful
     * @param message input provided by the user
     * @return whether the input contains only numbers
     * @throws NotNumberInputException 
     */
    
    public boolean checkValidityOfNumbers(String message) throws NotNumberInputException {
        if (inNumberRange(message) == false)
            throw new NotNumberInputException("Content of input outside number range.");
        else
            return true;
    }
    

}

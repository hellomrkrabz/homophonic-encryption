package pl.polsl.anna.pogorzelska.controller;
import pl.polsl.anna.pogorzelska.model.*;
import pl.polsl.anna.pogorzelska.model.exceptions.NonEnglishInputException;
import pl.polsl.anna.pogorzelska.model.exceptions.NotNumberInputException;
import pl.polsl.anna.pogorzelska.model.exceptions.ReadFileFailureException;
import pl.polsl.anna.pogorzelska.view.*;

/** 
 * Class responsible for parsing the arguments.
 * 
 * @author Anna Pogorzelska
 * @version 2.0
 */

public class Controller {
    GraphicalUserInterface view;
    Transcriptor transcriptor;
    Validator validator;
    public Controller() {
    }
    /**
     * Function responsible for parsing the arguments.
     *
     * @param args command line parameters
     * @throws pl.polsl.anna.pogorzelska.model.exceptions.ReadFileFailureException
     */
    
    public static void main(String[] args) throws ReadFileFailureException {
        Controller controller = new Controller();
        controller.view = new GraphicalUserInterface(controller);
        //Transcriptor transcriptor = new Transcriptor();
        controller.transcriptor = new Transcriptor();
        controller.validator = new Validator();
    }
    
    /**
     * Function that starts the encryption procedure
     * @param userInput String provided by the user
     */
    public void encryptionStarted(String userInput) {
        try {
            if (this.validator.checkValidityOfString(userInput) == true) {
                String output = this.transcriptor.encryption(userInput);
                this.view.setOutput(userInput, output);
            }  
        } catch (NonEnglishInputException ex) {
            view.showPopUpMessage("Wrong input");
        }
        } 
       
    /**
     * Function that starts the decryption procedure
     * @param userInput String provided by the user
     */
    public void decryptionStarted(String userInput) {
        try {
            if (this.validator.checkValidityOfNumbers(userInput) == true) {
                String output = this.transcriptor.decrypiton(userInput);
                this.view.setOutput(userInput, output);
            }
        } catch (NotNumberInputException ex) {
            view.showPopUpMessage("Wrong input");
        }
}
}
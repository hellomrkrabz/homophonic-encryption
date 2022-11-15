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
 * @version 1.2
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
        String input = "";
        boolean terminate = false;
        //Transcriptor transcriptor = new Transcriptor();
        controller.transcriptor = new Transcriptor();
        controller.validator = new Validator();
    }
    
    public String encryptionStarted(String userInput) throws NonEnglishInputException {
        if (this.validator.checkValidityOfString(userInput) == true) {
        String output = this.transcriptor.encryption(userInput);
        return output;
    }
        else 
            return "Something went wrong";
}       
    public String decryptionStarted(String userInput) throws NotNumberInputException {
        if (this.validator.checkValidityOfNumbers(userInput) == true) {
        String output = this.transcriptor.decrypiton(userInput);
        return output;
    }
        else 
            return "Something went wrong";
}
}
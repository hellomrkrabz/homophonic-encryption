package pl.polsl.anna.pogorzelska.controller;
import pl.polsl.anna.pogorzelska.view.View;

import pl.polsl.anna.pogorzelska.model.Mode;
import pl.polsl.anna.pogorzelska.model.exceptions.InvalidUserInputException;
import pl.polsl.anna.pogorzelska.model.Transcriptor;
import pl.polsl.anna.pogorzelska.model.exceptions.ReadFileFailureException;

/** 
 * Class responsible for parsing the arguments.
 * 
 * @author Anna Pogorzelska
 * @version 1.2
 */

public class Controller {

        /**
     * Function responsible for parsing the arguments.
     *
     * @param args command line parameters
     * @throws pl.polsl.anna.pogorzelska.model.exceptions.ReadFileFailureException
     */
    
    public static void main(String[] args) throws ReadFileFailureException {
        View view = new View();        
        view.showModesOptions();
        String input = "";
        boolean terminate = false;
        Transcriptor transcriptor = new Transcriptor();

        while (true) {
            String mode = view.chooseMode();
            try {
                transcriptor.validateTheMode(mode);
                
                if (mode.equals(Mode.QUIT.getValue())) {
                    break;
                }
                input = view.getConsoleInput();
                if (mode.equals(Mode.ENCRYPTION.getValue())) {
                    String encryptedMessage = transcriptor.encryption(input);
                    view.printMessage(String.format("Encrypted message is %s", encryptedMessage));
                }
                else if (mode.equals(Mode.DECRYPTION.getValue())) {
                    String decryptedMessage = transcriptor.decrypiton(input);
                    view.printMessage(String.format("Decrypted message is %s", decryptedMessage));
                }
            }
            catch(InvalidUserInputException exc) {
                view.printErrorMessage(exc.getMessage());
             }
        }
    }
}

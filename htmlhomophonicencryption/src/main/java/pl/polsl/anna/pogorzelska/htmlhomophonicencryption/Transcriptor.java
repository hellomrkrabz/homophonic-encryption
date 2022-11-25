package pl.polsl.anna.pogorzelska.htmlhomophonicencryption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
 import java.util.Map.Entry;
import pl.polsl.anna.pogorzelska.htmlhomophonicencryption.exceptions.NotNumberInputException;
import pl.polsl.anna.pogorzelska.htmlhomophonicencryption.exceptions.NotValidModeException;
import pl.polsl.anna.pogorzelska.htmlhomophonicencryption.exceptions.ReadFileFailureException;

/** 
 * Class responsible for the logic behind transcription.
 * 
 * @author Anna Pogorzelska
 * @version 1.2
 */

public class Transcriptor {
    private Random randomGenerator = new Random(); 
    private FileManager alphabetmanager;
    private HashMap<String,ArrayList<String>> dictionary;
    private Validator validator = new Validator();
    private final String filePath = "/src/main/resources/files/alphabet.txt";

     /**
     * Default constructor function for transcriptor. It preloads the dictionary file.
     *
     * @throws pl.polsl.anna.pogorzelska.model.exceptions.ReadFileFailureException
     */
    
    public Transcriptor () throws ReadFileFailureException {
        this.alphabetmanager = new FileManager();
        this.dictionary = this.alphabetmanager.readFile(this.filePath);
    }
    
    /**
     * Function responsible for encrypting the input provided.
     *
     * @param secretMessage message provided by the user
     * @return the encrypted version of provided input
     */
        
    public String encryption(String secretMessage) {
        String encrypted = "";
        for (int i = 0; i < secretMessage.length(); i++) {
            if (Character.isWhitespace(secretMessage.charAt(i))) {
                    encrypted += " ";
            }
            else {
                int index = randomGenerator.nextInt(this.dictionary.get(Character.toString(secretMessage.charAt(i))).size());
                String randomValue = this.dictionary.get(Character.toString(secretMessage.charAt(i))).get(index);
                encrypted += randomValue;
            }
        }
        return encrypted;
    }
    
      /**
     * Function responsible for decrypting the input provided.
     *
     * @param encryptedMessage the encrypted version of message
     * @return the decrypted version of provided input
     */  
    
  
    public String decrypiton(String encryptedMessage) {
        String decrypted = "";
        String currentlyDecrypted ="";
        for (int i = 0; i < encryptedMessage.length(); i++) {
            if (Character.isWhitespace(encryptedMessage.charAt(i))) {
                decrypted += " ";
            }
            else {
                currentlyDecrypted += encryptedMessage.charAt(i);
                if (currentlyDecrypted.length() == 3) {
                    for (Entry<String,ArrayList<String>> entry : this.dictionary.entrySet()) {
                        if (entry.getValue().contains(currentlyDecrypted)) 
                            decrypted += entry.getKey();
                    }
                    currentlyDecrypted = "";
                }
            }
        }
        return decrypted;
    }
    
}

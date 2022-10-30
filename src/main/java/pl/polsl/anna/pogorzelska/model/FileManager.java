package pl.polsl.anna.pogorzelska.model;

import java.io.File;  
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import pl.polsl.anna.pogorzelska.model.exceptions.ReadFileFailureException;


/** 
 * Class responsible for opening and reading the transcription dictionary file.
 * 
 * @author Anna Pogorzelska
 * @version 1.2
 */

public class FileManager {
    
    /**
     * Function responsible for opening the dictionary file.
     *
     * @param filePath path to the dictionary file
     * @return the BufferedReader of provided file
     * @throws java.io.FileNotFoundException
     */
    
    private BufferedReader openFile(String filePath) throws FileNotFoundException {
        File dictionary = new File(filePath);
        BufferedReader myReader = new BufferedReader(new FileReader(dictionary));
        return myReader;
  }
    
    /**
     * Function responsible for reading the dictionary file.
     *
     * @param filePath path to the dictionary file
     * @return the HashMap containing all letters and its encryption possibilities
     * @throws pl.polsl.anna.pogorzelska.model.exceptions.ReadFileFailureException
     */
      
      public HashMap<String,ArrayList<String>> readFile(String filePath) throws ReadFileFailureException  {
        String line;
        String key = null;
        HashMap<String,ArrayList<String>> letters = new HashMap<>();
        try (BufferedReader alphabethFile = this.openFile(filePath)) {
            while ((line = alphabethFile.readLine()) != null) {
                if (line.matches("[a-zA-Z]")) {
                    key = line;
                    letters.putIfAbsent(line, new ArrayList<String>());
                }
                else
                    letters.get(key).add(line);
            }
        }
        catch (IOException exc) {
            throw new ReadFileFailureException("Something wrong with the file, please check that it exiests");
        }
        return letters;
      }
}

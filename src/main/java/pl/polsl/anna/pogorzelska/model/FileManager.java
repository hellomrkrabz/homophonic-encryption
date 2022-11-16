package pl.polsl.anna.pogorzelska.model;

import java.io.File;  
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import pl.polsl.anna.pogorzelska.model.exceptions.ReadFileFailureException;


/** 
 * Class responsible for opening and reading the transcription dictionary file.
 * 
 * @author Anna Pogorzelska
 * @version 1.3
 */

public class FileManager {
    
    /**
     * Function responsible for reading the dictionary file.
     *
     * @param filePath path to the dictionary file
     * @return the HashMap containing all letters and its encryption possibilities
     * @throws pl.polsl.anna.pogorzelska.model.exceptions.ReadFileFailureException
     */
    
public HashMap<String,ArrayList<String>> readFile(String filePath) throws ReadFileFailureException {
    HashMap<String, ArrayList<String>> letters = new HashMap<>();
    try (Stream<String> lines = Files.lines(Paths.get(filePath).getFileName())) {
        lines.filter(line -> line.contains(":"))
          .forEach(line -> {
              String[] keyValuePair = line.split(":", 2);
              String key = keyValuePair[0];
              String value = keyValuePair[1];
              if (letters.containsKey(key)) {
                  letters.get(key).add(value);
              } else {
                  letters.put(key, (ArrayList<String>) Stream.of(value).collect(Collectors.toList()));
              }
          });
    } 
    catch (IOException exc) {
        throw new ReadFileFailureException("Something wrong with the file, please check that it exists");
        }
    return letters;
}
}

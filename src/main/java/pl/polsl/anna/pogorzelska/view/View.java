package pl.polsl.anna.pogorzelska.view;

import java.util.Scanner;

/** 
 * Class responsible for communicating with the user.
 * 
 * @author Anna Pogorzelska
 * @version 1.0
 */

public class View {
    public GraphicalUserInterface graphicalUserInterface;
    
    public View () {
        this.graphicalUserInterface= new GraphicalUserInterface();
    }
    
    /**
     * Function responsible for showing the user possible modes.
     */
    public void showModesOptions() {
        System.out.print("Possible modes: \n -d -decryption \n -e -encryption \n -q exit the program \n");
    }
    
    /**
     * Function responsible for getting input from the user.
     * @return input provided by the user
     */
    
    public String getConsoleInput(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Provide input text: ");
        String userInput = scanner.nextLine();
        return userInput;
    }
    
    /**
     * Function responsible for printing provided input.
     * @param message input to be printed
     */
    
    public void printMessage(String message){
        System.out.println(message);
    }
    
    /**
     * Function responsible for printing the error message.
     * @param errorMessage error message to be printed
     */
    
    public void printErrorMessage(String errorMessage){
        System.err.println(errorMessage);
    }
    
    /**
     * Function responsible for getting the mode input from the user.
     * @return mode selected by the user
     */
    
    public String chooseMode() {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Please choose mode: ");
    String userMode = scanner.next();
    return userMode;
    }
 
}

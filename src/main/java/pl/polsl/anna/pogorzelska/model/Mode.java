package pl.polsl.anna.pogorzelska.model;

/** 
 * Enumerated type representing options available to the user.
 * 
 * @author Anna Pogorzelska
 * @version 1.0
 */

public enum Mode {
    ENCRYPTION("e"),
    DECRYPTION("d"),
    QUIT("q");
    
    private final String modeFromUser;

    /**
     * Constructor for enum.
     *
     * @param modeFromUser mode chosen by the user
     */
    private Mode(String modeFromUser) {
    this.modeFromUser = modeFromUser;
}
    /**
     * Function responsible for getting input from the user.
     *
     * @return input provided by the user
     */
    public String getValue(){
    return modeFromUser;
}
}


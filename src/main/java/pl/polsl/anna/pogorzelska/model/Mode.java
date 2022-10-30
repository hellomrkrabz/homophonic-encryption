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

    private Mode(String modeFromUser) {
    this.modeFromUser = modeFromUser;
}
    public String getValue(){
    return modeFromUser;
}
}


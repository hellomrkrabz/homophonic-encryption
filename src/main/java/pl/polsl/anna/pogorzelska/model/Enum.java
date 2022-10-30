package pl.polsl.anna.pogorzelska.model;

enum Mode {
    ENCRYPTION("e"),
    DECRYPTION("d"),
    QUIT("q");
    
    private final String modeFromUser;

    private Mode(String modeFromUser) {
    this.modeFromUser = modeFromUser;
}
}


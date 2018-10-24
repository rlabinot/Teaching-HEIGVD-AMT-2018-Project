package ch.heig.amt.gamification.model;

public class InputError {
    private static final String EMPTY_FIELD_MESSAGE = "Field cannot be empty";
    private static final String WRONG_FORMAT_EMAIL = "Invalid format for email";
    private static final String WEAK_PASSWORD = "Password doesn't contain a number, an uppercase and 8 characters without blanks";
    private static final String WRONG_LOGIN = "Wrong login or password";
    private static final String USER_ALREADY_REGISTERED = "User already registered";
    private static final String APP_ALREADY_REGISTERED = "App already registered";

    // Create user
    private boolean emptyName;
    private boolean emptyEmail;
    private boolean wrongFormatEmail;
    private boolean emptyPassword;
    private boolean weakPassword;

    // Create app
    private boolean emptyDescription;
    private boolean emptyApiKey;
    private boolean emptyApiSecret;

    // Login
    private boolean wrongLogin;

    public boolean checkErrors() {
        return emptyName || emptyEmail || wrongFormatEmail ||
                emptyPassword || weakPassword || emptyDescription || emptyApiKey || emptyApiSecret;
    }


    // BOOLEAN SETTEURS AND GETTEURS
    public boolean isEmptyName() {
        return emptyName;
    }

    public void setEmptyName(boolean emptyName) {
        this.emptyName = emptyName;
    }

    public boolean isEmptyEmail() {
        return emptyEmail;
    }

    public void setEmptyEmail(boolean emptyEmail) {
        this.emptyEmail = emptyEmail;
    }

    public boolean isWrongFormatEmail() {
        return wrongFormatEmail;
    }

    public void setWrongFormatEmail(boolean wrongFormatEmail) {
        this.wrongFormatEmail = wrongFormatEmail;
    }

    public boolean isEmptyPassword() {
        return emptyPassword;
    }

    public void setEmptyPassword(boolean emptyPassword) {
        this.emptyPassword = emptyPassword;
    }

    public boolean isWeakPassword() {
        return weakPassword;
    }

    public void setWeakPassword(boolean weakPassword) {
        this.weakPassword = weakPassword;
    }

    public boolean isEmptyDescription() {
        return emptyDescription;
    }

    public void setEmptyDescription(boolean emptyDescription) {
        this.emptyDescription = emptyDescription;
    }

    public boolean isEmptyApiKey() {
        return emptyApiKey;
    }

    public void setEmptyApiKey(boolean emptyApiKey) {
        this.emptyApiKey = emptyApiKey;
    }

    public boolean isEmptyApiSecret() {
        return emptyApiSecret;
    }

    public void setEmptyApiSecret(boolean emptyApiSecret) {
        this.emptyApiSecret = emptyApiSecret;
    }

    public boolean isWrongLogin() {
        return wrongLogin;
    }

    public void setWrongLogin(boolean wrongLogin) {
        this.wrongLogin = wrongLogin;
    }

    // MESSAGES ERRORS GETTEURS
    public static String getEmptyFieldMessage() {
        return EMPTY_FIELD_MESSAGE;
    }

    public static String getWrongFormatEmail() {
        return WRONG_FORMAT_EMAIL;
    }

    public static String getWeakPassword() {
        return WEAK_PASSWORD;
    }

    public static String getWrongLogin() {
        return WRONG_LOGIN;
    }

    public static String getUserAlreadyRegistred() {
        return USER_ALREADY_REGISTRED;
    }

    public static String getAppAlreadyRegistred() {
        return APP_ALREADY_REGISTRED;
    }
}

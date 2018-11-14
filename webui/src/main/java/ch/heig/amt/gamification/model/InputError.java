package ch.heig.amt.gamification.model;

public class InputError {
    // Messages
    private static final String EMPTY_FIELD_MESSAGE = "Field cannot be empty";
    private static final String WRONG_FORMAT_EMAIL_MESSAGE = "Invalid format for email";
    private static final String WEAK_PASSWORD_MESSAGE = "Password doesn't contain a number, an uppercase and 8 characters without blanks";
    private static final String WRONG_LOGIN_MESSAGE = "Wrong login or password";
    private static final String USER_ALREADY_REGISTERED = "Email already registered";
    private static final String APP_ALREADY_REGISTERED = "Application already registered";
    private static final String USER_MUST_CHANGE_PASSWORD = "Your password has been reset, please enter a new password";
    private static final String BOTH_PASSWORD_DIFFERENT = "Both passwords should be equal";
    private static final String PASSWORD_REUSED = "You have already used this password, please enter a new password";
    private static final String INACTIVE_USER_MESSAGE = "Your account has been suspended";
    private static final String USER_PASSWORD_RESET = "Your password has been reset, please check your mails";


    // Create user
    private boolean emptyName;
    private boolean emptyEmail;
    private boolean wrongFormatEmail;
    private boolean emptyPassword;
    private boolean weakPassword;
    private boolean emailAlreadyInUse;
    private boolean mustChangePassword;
    private boolean bothPasswordDifferent;
    private boolean passwordReused;

    // Create app
    private boolean emptyDescription;
    private boolean appAlreadyInUse;

    // Login
    private boolean wrongLogin;
    private boolean inactiveUser;
    private boolean passwordReset;


    public boolean checkErrors() {
        return emptyName || emptyEmail ||wrongFormatEmail || emptyPassword || weakPassword || emailAlreadyInUse ||
                mustChangePassword || bothPasswordDifferent || passwordReused || emptyDescription || appAlreadyInUse ||
                wrongLogin || inactiveUser || passwordReset;
    }


    // BOOLEAN SETTEURS AND GETTEURS
    public boolean isEmptyName() {return emptyName;}
    public void setEmptyName(boolean emptyName) {this.emptyName = emptyName;}

    public boolean isEmptyEmail() {return emptyEmail;}
    public void setEmptyEmail(boolean emptyEmail) {this.emptyEmail = emptyEmail;}

    public boolean isWrongFormatEmail() {return wrongFormatEmail;}
    public void setWrongFormatEmail(boolean wrongFormatEmail) {this.wrongFormatEmail = wrongFormatEmail;}

    public boolean isEmptyPassword() { return emptyPassword;}
    public void setEmptyPassword(boolean emptyPassword) { this.emptyPassword = emptyPassword; }

    public boolean isWeakPassword() { return weakPassword; }
    public void setWeakPassword(boolean weakPassword) { this.weakPassword = weakPassword; }

    public boolean isEmptyDescription() { return emptyDescription; }
    public void setEmptyDescription(boolean emptyDescription) {this.emptyDescription = emptyDescription; }

    public boolean isWrongLogin() {return wrongLogin;}
    public void setWrongLogin(boolean wrongLogin) { this.wrongLogin = wrongLogin;}

    public boolean isEmailAlreadyInUse() { return emailAlreadyInUse; }
    public void setEmailAlreadyInUse(boolean emailAlreadyInUse) { this.emailAlreadyInUse = emailAlreadyInUse; }

    public boolean isAppAlreadyInUse() { return appAlreadyInUse; }
    public void setAppAlreadyInUse(boolean appAlreadyInUse) { this.appAlreadyInUse = appAlreadyInUse; }

    public boolean isMustChangePassword() { return mustChangePassword; }
    public void setMustChangePassword(boolean mustChangePassword) { this.mustChangePassword = mustChangePassword; }

    public boolean isBothPasswordDifferent() {
        return bothPasswordDifferent;
    }
    public void setBothPasswordDifferent(boolean bothPasswordDifferent) {this.bothPasswordDifferent = bothPasswordDifferent;}

    public boolean isPasswordReused() {return passwordReused;}
    public void setPasswordReused(boolean passwordReused) {
        this.passwordReused = passwordReused;
    }

    public boolean isInactiveUser() {
        return inactiveUser;
    }
    public void setInactiveUser(boolean inactiveUser) {
        this.inactiveUser = inactiveUser;
    }

    public boolean isPasswordReset() {return passwordReset;}
    public void setPasswordReset(boolean passwordReset) {this.passwordReset = passwordReset;}

    // MESSAGES ERRORS GETTEURS
    public String getEmptyFieldMessage() { return EMPTY_FIELD_MESSAGE; }
    public String getWrongFormatEmailMessage() { return WRONG_FORMAT_EMAIL_MESSAGE; }
    public String getWeakPasswordMessage() { return WEAK_PASSWORD_MESSAGE; }
    public String getWrongLoginMessage() { return WRONG_LOGIN_MESSAGE; }
    public String getUserAlreadyRegistred() { return USER_ALREADY_REGISTERED; }
    public String getAppAlreadyRegistred() { return APP_ALREADY_REGISTERED; }
    public String getUserAlreadyRegistered() { return USER_ALREADY_REGISTERED; }
    public String getAppAlreadyRegistered() { return APP_ALREADY_REGISTERED; }
    public String getUserMustChangePassword() { return USER_MUST_CHANGE_PASSWORD; }
    public String getBothPasswordDifferent() { return BOTH_PASSWORD_DIFFERENT; }
    public String getPasswordReusedMessage() { return PASSWORD_REUSED; }
    public String getInactiveUserMessage() { return INACTIVE_USER_MESSAGE; }
    public String getUserPasswordReset() {return USER_PASSWORD_RESET;}
}

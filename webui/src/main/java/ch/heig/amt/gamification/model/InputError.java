package ch.heig.amt.gamification.model;

public class InputError {
    private final String wrongLoginMessage = "Wrong Credentials !";
    private final String inactivLoginMessage = "Your account has been suspend";
    private final String emptyFieldMessage = "This fields can't be empty";
    private final String emailFormatMessage = "Incorrect email format";
    private final String emailAlreadyExistMessage = "This email is already registered";
    private final String passwordMessage = "Your password must have at least 8 characters";

    private boolean wrongLogin = false;
    private boolean inactivLogin = false;
    private boolean emptyField = false;
    private boolean emailFormat = false;
    private boolean emailAlreadyExist = false;
    private boolean password = false;

    public boolean isWrongLogin() {
        return wrongLogin;
    }

    public boolean isInactivLogin() {
        return inactivLogin;
    }

    public boolean isEmptyField() {
        return emptyField;
    }

    public boolean isEmailFormat() {
        return emailFormat;
    }

    public boolean isEmailAlreadyExist() {
        return emailAlreadyExist;
    }

    public boolean isPassword() {
        return password;
    }

    public String getWrongLoginMessage() {
        return wrongLoginMessage;
    }

    public String getInactivLoginMessage() {
        return inactivLoginMessage;
    }

    public String getEmptyFieldMessage() {
        return emptyFieldMessage;
    }

    public String getEmailFormatMessage() {
        return emailFormatMessage;
    }

    public String getEmailAlreadyExistMessage() {
        return emailAlreadyExistMessage;
    }

    public String getPasswordMessage() {
        return passwordMessage;
    }

}

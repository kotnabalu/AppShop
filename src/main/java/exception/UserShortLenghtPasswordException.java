package exception;

public class UserShortLenghtPasswordException extends Exception {

    public UserShortLenghtPasswordException() {
    }

    public UserShortLenghtPasswordException(String message) {
        super(message);
    }
}

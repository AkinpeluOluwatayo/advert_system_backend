package enterprise.elroi.exceptions.authServiceExceptions;

public class InvalidPasswordException extends AuthException {
    public InvalidPasswordException(String message) {
        super(message);
    }
}

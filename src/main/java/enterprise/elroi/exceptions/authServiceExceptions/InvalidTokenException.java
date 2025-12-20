package enterprise.elroi.exceptions.authServiceExceptions;

public class InvalidTokenException extends AuthException {
    public InvalidTokenException(String message) {
        super(message);
    }
}

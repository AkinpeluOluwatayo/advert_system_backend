package enterprise.elroi.exceptions.authServiceExceptions;

public class UserNotFoundTokenException extends AuthException {
    public UserNotFoundTokenException(String message) {
        super(message);
    }
}

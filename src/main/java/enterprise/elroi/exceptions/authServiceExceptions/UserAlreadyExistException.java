package enterprise.elroi.exceptions.authServiceExceptions;

public class UserAlreadyExistException extends AuthException {
    public UserAlreadyExistException(String message) {
        super(message);
    }
}

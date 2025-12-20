package enterprise.elroi.exceptions.authServiceExceptions;

public class UserLoginNotFoundException extends AuthException {
    public UserLoginNotFoundException(String message) {
        super(message);
    }
}

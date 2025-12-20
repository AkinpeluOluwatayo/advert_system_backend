package enterprise.elroi.exceptions.authServiceExceptions;

public class UserCurrentLoginNotFoundException extends AuthException {
    public UserCurrentLoginNotFoundException(String message) {
        super(message);
    }
}

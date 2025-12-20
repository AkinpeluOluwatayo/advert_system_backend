package enterprise.elroi.exceptions.authServiceExceptions;

public class UserIsNotAnAdminException extends AuthException {
    public UserIsNotAnAdminException(String message) {
        super(message);
    }
}

package enterprise.elroi.exceptions.authServiceExceptions;

public class UserForgotPasswordEmailNotFoundException extends AuthException {
    public UserForgotPasswordEmailNotFoundException(String message) {
        super(message);
    }
}

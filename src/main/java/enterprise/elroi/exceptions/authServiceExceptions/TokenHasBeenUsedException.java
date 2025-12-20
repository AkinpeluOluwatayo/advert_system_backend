package enterprise.elroi.exceptions.authServiceExceptions;

public class TokenHasBeenUsedException extends AuthException {
    public TokenHasBeenUsedException(String message) {
        super(message);
    }
}

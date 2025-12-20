package enterprise.elroi.exceptions.userServiceException;

public class GetUserByIdNotFoundException extends RuntimeException {
    public GetUserByIdNotFoundException(String message) {
        super(message);
    }
}

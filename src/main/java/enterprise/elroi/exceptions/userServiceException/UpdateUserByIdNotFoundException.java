package enterprise.elroi.exceptions.userServiceException;

public class UpdateUserByIdNotFoundException extends GetUserByIdNotFoundException {
    public UpdateUserByIdNotFoundException(String message) {
        super(message);
    }
}

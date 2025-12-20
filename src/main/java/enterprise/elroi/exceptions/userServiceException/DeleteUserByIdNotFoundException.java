package enterprise.elroi.exceptions.userServiceException;

public class DeleteUserByIdNotFoundException extends GetUserByIdNotFoundException {
    public DeleteUserByIdNotFoundException(String message) {
        super(message);
    }
}

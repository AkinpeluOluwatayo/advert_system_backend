package enterprise.elroi.exceptions.categoryServiceExceptions;

public class UpdateCategoryNotFoundException extends GetCategoryByIdNotFoundException {
    public UpdateCategoryNotFoundException(String message) {
        super(message);
    }
}

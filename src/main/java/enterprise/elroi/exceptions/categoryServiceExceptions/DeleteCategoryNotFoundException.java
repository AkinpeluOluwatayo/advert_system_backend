package enterprise.elroi.exceptions.categoryServiceExceptions;

public class DeleteCategoryNotFoundException extends GetCategoryByIdNotFoundException {
    public DeleteCategoryNotFoundException(String message) {
        super(message);
    }
}

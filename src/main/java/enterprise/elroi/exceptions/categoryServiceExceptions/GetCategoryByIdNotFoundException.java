package enterprise.elroi.exceptions.categoryServiceExceptions;

public class GetCategoryByIdNotFoundException extends RuntimeException {
    public GetCategoryByIdNotFoundException(String message) {
        super(message);
    }
}

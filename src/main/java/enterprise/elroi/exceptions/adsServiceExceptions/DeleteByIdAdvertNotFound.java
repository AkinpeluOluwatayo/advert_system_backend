package enterprise.elroi.exceptions.adsServiceExceptions;

public class DeleteByIdAdvertNotFound extends GetByIdAdvertNotFoundException {
    public DeleteByIdAdvertNotFound(String message) {
        super(message);
    }
}

package enterprise.elroi.exceptions.adsServiceExceptions;

public class NotAllowedTodeleteAdvertException extends GetByIdAdvertNotFoundException {
    public NotAllowedTodeleteAdvertException(String message) {
        super(message);
    }
}

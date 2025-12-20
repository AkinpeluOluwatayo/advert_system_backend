package enterprise.elroi.exceptions.adsServiceExceptions;

public class GetByIdAdvertNotFoundException extends RuntimeException {
    public GetByIdAdvertNotFoundException(String message) {
        super(message);
    }
}

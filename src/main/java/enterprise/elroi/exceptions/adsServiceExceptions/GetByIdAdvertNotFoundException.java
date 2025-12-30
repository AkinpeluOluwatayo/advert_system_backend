package enterprise.elroi.exceptions.adsServiceExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class GetByIdAdvertNotFoundException extends RuntimeException {
    public GetByIdAdvertNotFoundException(String message) {
        super(message);
    }
}

package testing.app.exceptions.repositories;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ModelNotFoundException extends Exception {

    public ModelNotFoundException() {
        super("Model not found.");
    }

    public ModelNotFoundException(String message) {
        super(message);
    }

    public ResponseStatusException toResponse() {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, this.getMessage());
    }
}

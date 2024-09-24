package org.university.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class DocumentNotFoundException extends ResponseStatusException {
    public DocumentNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
    @Override
    public String getMessage(){
        return (super.getReason() != null ? " \"" + super.getReason() + "\"" : "");
    }
}

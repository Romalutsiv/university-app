package org.university.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CreateDocumentException extends ResponseStatusException {
    public CreateDocumentException(String s) {
        super(HttpStatus.BAD_REQUEST, s);
    }
    @Override
    public String getMessage(){
        return (super.getReason() != null ? " \"" + super.getReason() + "\"" : "");
    }
}

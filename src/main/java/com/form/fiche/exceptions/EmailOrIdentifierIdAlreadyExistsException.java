package com.form.fiche.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EmailOrIdentifierIdAlreadyExistsException extends RuntimeException{
    public EmailOrIdentifierIdAlreadyExistsException(String message){
        super(message);
    }
}

package br.com.elizeustachuka.cliente.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundExceptionCopy extends RuntimeException{
    private static final long serialVersionUID = 1L;
    public ResourceNotFoundExceptionCopy(String message) {
        super(message);
    }
}
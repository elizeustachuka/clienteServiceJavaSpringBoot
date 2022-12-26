package br.com.elizeustachuka.cliente.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.GATEWAY_TIMEOUT)
public class GatewayUnavailable extends RuntimeException{
    public GatewayUnavailable(String message) {
        super(message);
    }
}
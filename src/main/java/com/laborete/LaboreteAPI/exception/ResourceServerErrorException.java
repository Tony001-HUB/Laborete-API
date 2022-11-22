package com.laborete.LaboreteAPI.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE)
public class ResourceServerErrorException extends RuntimeException{
    public ResourceServerErrorException(String message){
        super(message);
    }
}

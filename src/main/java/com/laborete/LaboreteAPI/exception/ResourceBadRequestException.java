package com.laborete.LaboreteAPI.exception;
public class ResourceBadRequestException extends RuntimeException {
    public ResourceBadRequestException(String errorMessage){
        super(errorMessage);
    }
}
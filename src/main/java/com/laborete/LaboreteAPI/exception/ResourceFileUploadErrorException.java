package com.laborete.LaboreteAPI.exception;

public class ResourceFileUploadErrorException extends RuntimeException {
    private String errorMessage;
    public ResourceFileUploadErrorException(String message){
        super(message);
        this.errorMessage = message;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}

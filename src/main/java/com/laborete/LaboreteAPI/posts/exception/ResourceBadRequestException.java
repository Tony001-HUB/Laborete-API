package com.laborete.LaboreteAPI.posts.exception;

public class ResourceBadRequestException extends RuntimeException {
    private String errorMessage;

    public ResourceBadRequestException(String errorMessage) {
        super(errorMessage);
        this.setErrorMessage(errorMessage);
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}

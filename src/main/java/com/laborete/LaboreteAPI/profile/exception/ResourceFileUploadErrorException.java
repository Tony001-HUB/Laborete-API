package com.laborete.LaboreteAPI.profile.exception;

public class ResourceFileUploadErrorException extends RuntimeException {
    private String errorMessage;
    private Throwable cause;
    public ResourceFileUploadErrorException(String message){
        super(message);
        this.errorMessage = message;
    }

    public ResourceFileUploadErrorException(String message, Throwable cause) {
        super(message, cause);
        this.cause = cause;
        this.errorMessage = message;
    }

    public String getErrorMessage() {
        return errorMessage + ":" + this.getCause().getMessage();
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public Throwable getCause() {
        return cause;
    }

    public void setCause(Throwable cause) {
        this.cause = cause;
    }
}

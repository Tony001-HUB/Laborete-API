package com.laborete.LaboreteAPI.exception;

import com.laborete.LaboreteAPI.profile.models.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import springfox.documentation.annotations.ApiIgnore;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ResourceBadRequestException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgument(ResourceBadRequestException ex) {
        ErrorResponse error = new ErrorResponse();
        error.setError(ex.getErrorMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFound(ResourceNotFoundException ex) {
        ErrorResponse error = new ErrorResponse();
        error.setError(ex.getErrorMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceFileUploadErrorException.class)
    public ResponseEntity<ErrorResponse> handleErrorFileUpload(ResourceFileUploadErrorException ex) {
        ErrorResponse error = new ErrorResponse();
        error.setError(ex.getErrorMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

package com.laborete.LaboreteAPI.exception;

import com.laborete.LaboreteAPI.modal.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ResourceBadRequestException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgument(ResourceBadRequestException ex) {
        ErrorResponse error = new ErrorResponse();
        error.setError(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}

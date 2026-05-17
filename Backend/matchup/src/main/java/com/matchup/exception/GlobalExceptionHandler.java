package com.matchup.exception;


import jakarta.validation.constraints.Email;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * this method handles custom errors that hibernate threw from database errors and returns them to frontend in a meaningful way.
     * in order for this method to work all defined custom-made exceptions should have a meaningful messages
     *
     * @param e - Runtime exception is a parent of all custom-made db errors so it is the only suitable argument
     * @return example for EmailAlreadyExistsException:
     * {"errorMessage":"This email has already been registered"}
     *
     */
    @ExceptionHandler({
            EntityNotFoundException.class,
            EmailAlreadyExistsException.class
    })
    public ResponseEntity<ErrorResponse> handleDbExceptions(RuntimeException e){
        ErrorResponse errorResponse = ErrorResponse.builder().errorMessage(e.getMessage()).build();
        return ResponseEntity.status(404).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidation(MethodArgumentNotValidException e){
        Map<String, String> errors = new HashMap<>();

        e.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        return ResponseEntity.badRequest().body(errors);
    }


}

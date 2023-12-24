package com.example.employeecollaborationtracker.config;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.employeecollaborationtracker.util.common.ExceptionMessages.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<List<Object>> handleConstraintExceptions(ConstraintViolationException exception) {
        List<Object> errorList = new ArrayList<>();
        for (ConstraintViolation<?> e : exception.getConstraintViolations()) {
            errorList.add(e.getMessage());
        }
        return new ResponseEntity<>(errorList, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<Map<Object, String>> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        Map<Object, String> errorListResponse = new HashMap<>();

        List<FieldError> errorList = ex
                .getBindingResult().getFieldErrors();
        for (FieldError fieldError : errorList) {

            errorListResponse.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return new ResponseEntity<>(errorListResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<String> customHandler(CustomException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<String> noResourceFoundHttpHandler() {
        return new ResponseEntity<>(INVALID_URL_PATH, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<String> dateParserHandler(DateTimeParseException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<String> httpMethodHandler() {
        return new ResponseEntity<>(INVALID_URL_METHOD, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> dataIntegrityHandler() {
        return new ResponseEntity<>(INVALID_DATA_TO_PERSIST, HttpStatus.BAD_REQUEST);
    }
}

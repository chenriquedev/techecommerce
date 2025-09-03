package com.henrique.ecommerce_back.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.henrique.ecommerce_back.model.dto.ResponseDTO;

@RestControllerAdvice

public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ResponseDTO> handleNotFound(ProductNotFoundException ex) {
        ResponseDTO responseDto = new ResponseDTO(ex.getMessage(), HttpStatus.NOT_FOUND.value(), null);
        return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(ArgumentInvalidException.class)
    public ResponseEntity<ResponseDTO> handleArgumentInvalidException(ArgumentInvalidException ex) {

        ResponseDTO responseDto = new ResponseDTO(ex.getMessage(), HttpStatus.BAD_REQUEST.value(), null);
        return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDTO> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        ResponseDTO response = new ResponseDTO("Validation error", HttpStatus.BAD_REQUEST.value(), errors);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ImageNotFoundException.class)
    public ResponseEntity<ResponseDTO> handleImageNotFoundException(
            ImageNotFoundException ex) {
        ResponseDTO responseDto = new ResponseDTO(ex.getMessage(), HttpStatus.NOT_FOUND.value(), null);
        return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(StorageException.class)
    public ResponseEntity<ResponseDTO> handleStorageException(
            StorageException ex) {
        ResponseDTO responseDto = new ResponseDTO(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
        return new ResponseEntity<>(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BrandAlreadyExistsException.class)
    public ResponseEntity<ResponseDTO> handleBrandExistsException(
            BrandAlreadyExistsException ex) {
        ResponseDTO responseDto = new ResponseDTO(ex.getMessage(), HttpStatus.CONFLICT.value(), null);
        return new ResponseEntity<>(responseDto, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(BrandNotFoundException.class)
    public ResponseEntity<ResponseDTO> handleBrandNotFoundException(
            BrandNotFoundException ex) {
        ResponseDTO responseDto = new ResponseDTO(ex.getMessage(), HttpStatus.NOT_FOUND.value(), null);
        return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidImageTypeException.class)
    public ResponseEntity<ResponseDTO> handleInvalidImageTypeException(
            InvalidImageTypeException ex) {
        ResponseDTO responseDto = new ResponseDTO(ex.getMessage(), HttpStatus.BAD_REQUEST.value(), null);
        return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BrandImageNotFoundException.class)
    public ResponseEntity<ResponseDTO> handleBrandImageNotFoundException(
            BrandImageNotFoundException ex) {
        ResponseDTO responseDto = new ResponseDTO(ex.getMessage(), HttpStatus.NOT_FOUND.value(), null);
        return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ResponseDTO> handleCategoryNotFoundException(
            CategoryNotFoundException ex) {
        ResponseDTO responseDto = new ResponseDTO(ex.getMessage(), HttpStatus.NOT_FOUND.value(), null);
        return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InsufficientStockException.class)
    public ResponseEntity<ResponseDTO> handleInsufficientStockException(
            InsufficientStockException ex) {
        ResponseDTO responseDto = new ResponseDTO(ex.getMessage(), HttpStatus.BAD_REQUEST.value(), null);
        return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CategoryAlreadyExistsException.class)
    public ResponseEntity<ResponseDTO> handleCategoryAlreadyExistsException(CategoryAlreadyExistsException ex) {
        ResponseDTO responseDto = new ResponseDTO(ex.getMessage(), HttpStatus.BAD_REQUEST.value(), null);
        return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CategoryImageNotFoundException.class)
    public ResponseEntity<ResponseDTO> handleCategoryImageNotFoundException(CategoryImageNotFoundException ex) {
        ResponseDTO responseDto = new ResponseDTO(ex.getMessage(), HttpStatus.NOT_FOUND.value(), null);
        return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
    }

}

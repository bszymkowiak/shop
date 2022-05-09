package com.bartek.shop.controller;

import com.bartek.shop.model.dto.ErrorDto;
import com.bartek.shop.model.dto.FieldErrorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j //adnotacje dodaje nam statyczna finalna zmienna o nazwie log
public class AdviceController {

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto handleConstraintViolationException(ConstraintViolationException exception) {
        log.warn(exception.getMessage(), exception);
        return new ErrorDto(exception.getMessage());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto handleEntityNotFoundException(EntityNotFoundException exception) {
        log.warn(exception.getMessage(), exception);
        return new ErrorDto(exception.getMessage());
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorDto handleSqlIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException exception) {
        log.warn(exception.getMessage(), exception);
        return new ErrorDto(exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<FieldErrorDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        log.warn(exception.getMessage(), exception);
        return exception.getBindingResult().getAllErrors().stream()
                .map(objectError -> (FieldError) objectError)
                .map(fieldError -> new FieldErrorDto(fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(Collectors.toList());
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorDto handleBadCredentialsException(BadCredentialsException exception) {
        return new ErrorDto(exception.getMessage());
    }
}
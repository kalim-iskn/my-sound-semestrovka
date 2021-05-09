package ru.kpfu.itis.iskander.mysound.rest.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.kpfu.itis.iskander.mysound.exceptions.ListenerAlreadyExistException;
import ru.kpfu.itis.iskander.mysound.exceptions.NotFoundException;
import ru.kpfu.itis.iskander.mysound.rest.dto.ApiError;

@RestControllerAdvice("ru.kpfu.itis.iskander.mysound.rest.controllers")
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(value = {NotFoundException.class})
    protected ResponseEntity<Object> handleNotFound(NotFoundException ex, WebRequest request) {
        logger.warn(ex.getMessage());
        return sendErrorResponse(ex, request, HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(value = {MethodArgumentTypeMismatchException.class})
    protected ResponseEntity<Object> handleMethodArgumentMismatch(
            MethodArgumentTypeMismatchException ex, WebRequest request) {
        logger.warn(ex.getMessage());
        return sendErrorResponse(ex, request, HttpStatus.BAD_REQUEST, "Invalid resource variable");
    }

    @ExceptionHandler(value = {ListenerAlreadyExistException.class, IllegalArgumentException.class})
    protected ResponseEntity<Object> handleCommonBadRequest(ListenerAlreadyExistException ex, WebRequest request) {
        logger.warn(ex.getMessage());
        return sendErrorResponse(ex, request, HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    private ResponseEntity<Object> sendErrorResponse(Exception e, WebRequest request, HttpStatus status, String msg) {
        return handleExceptionInternal(e, new ApiError(status, msg), new HttpHeaders(), status, request);
    }

}

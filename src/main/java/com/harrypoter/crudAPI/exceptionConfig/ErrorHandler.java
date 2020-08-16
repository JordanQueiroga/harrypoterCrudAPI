/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.harrypoter.crudAPI.exceptionConfig;

import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 *
 * @author JORDAN QUEIROGA
 */
@CrossOrigin
@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders hh, HttpStatus hs, WebRequest request) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        String message = "";
        for (int i = 0; i < fieldErrors.size(); i++) {
            message += fieldErrors.get(i).getDefaultMessage();
            if (i < fieldErrors.size() - 1) {
                message += "\n";
            }
        }

        return handleExceptionInternal(
                ex, ErrorOutputDto.builder()
                        .addDetalhe("Invalid validation")
                        .addMessage(message)
                        .addStatus(HttpStatus.BAD_REQUEST)
                        .addErroCause(ex.getMessage())
                        .addHttpMethod(getHttpMethod(request))
                        .addPath(getPath(request))
                        .build(),
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);

    }
    
    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<Object> entityNotFound(NotFoundException ex, WebRequest request) {
        return handleExceptionInternal(
                ex, ErrorOutputDto.builder()
                        .addDetalhe("Resource not found in the database")
                        .addMessage(ex.getMessage())
                        .addStatus(HttpStatus.NOT_FOUND)
                        .addErroCause(ex.getCause() != null ? ex.getCause().toString() : "")
                        .addHttpMethod(getHttpMethod(request))
                        .addPath(getPath(request))
                        .build(),
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({BadRequesException.class})
    public ResponseEntity<Object> badRequestException(RuntimeException ex, WebRequest request) {
        ex.printStackTrace();
        return handleExceptionInternal(
                ex, ErrorOutputDto.builder()
                        .addDetalhe("One exception was thrown")
                        .addMessage(ex.getMessage() != null ? ex.getMessage() : ex.toString())
                        .addErroCause(ex.getCause() != null ? ex.getCause().toString() : "")
                        .addStatus(HttpStatus.BAD_REQUEST)
                        .addHttpMethod(getHttpMethod(request))
                        .addPath(getPath(request))
                        .build(),
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<Object> serverException(RuntimeException ex, WebRequest request) {
        ex.printStackTrace();
        return handleExceptionInternal(
                ex, ErrorOutputDto.builder()
                        .addDetalhe("One exception was thrown")
                        .addMessage(ex.getMessage() != null ? ex.getMessage() : ex.toString())
                        .addErroCause(ex.getCause() != null ? ex.getCause().toString() : "")
                        .addStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                        .addHttpMethod(getHttpMethod(request))
                        .addPath(getPath(request))
                        .build(),
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    private String getPath(WebRequest request) {
        return ((ServletWebRequest) request).getRequest().getRequestURI();
    }

    private String getHttpMethod(WebRequest request) {

        return ((ServletWebRequest) request).getRequest().getMethod();
    }

}

package com.github.mrag.helloim.common;

import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exceptions.GlobalException.class)
    public HttpResponse defaultExceptionHandler(Exceptions.GlobalException exception) {
        return HttpResponse.fail(exception);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public HttpResponse methodArgumentNotValidException(MethodArgumentNotValidException e) {
        ObjectError error = e.getBindingResult().getAllErrors().get(0);
        return HttpResponse.fail(Exceptions.badRequest(error.getDefaultMessage()));
    }
}

package com.github.mrag.helloim.common;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exceptions.GlobalException.class)
    public HttpResponse defaultExceptionHandler(Exceptions.GlobalException exception) {
        return HttpResponse.fail(exception);
    }
}

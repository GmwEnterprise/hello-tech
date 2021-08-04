package com.github.mrag.web.config;

import com.github.mrag.web.common.Resp;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    Resp handleMapping(MethodArgumentNotValidException exp) {
        String msg = exp.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        return Resp.error("400", msg);
    }
}

package com.example.minibom.exception;

import com.example.minibom.common.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 如果抛出的的是CustomException，则调用该方法
     * @param se 业务异常
     * @return Result
     */
    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public Result handle(CustomException se){
        return Result.error(se.getMessage());
    }
}
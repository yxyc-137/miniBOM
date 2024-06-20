package com.example.minibom.exception;

import lombok.Data;

@Data
public class CustomException extends RuntimeException {

    //继承RuntimeException方法
    private static final long serialVersionUID = 1L;

    public  CustomException(String msg){
        super(msg);
    }
}

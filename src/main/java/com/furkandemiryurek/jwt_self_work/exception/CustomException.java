package com.furkandemiryurek.jwt_self_work.exception;

import com.furkandemiryurek.jwt_self_work.enums.ErrorType;
import lombok.Getter;

@Getter
public class CustomException extends RuntimeException{
    private final ErrorType errorType;

    public CustomException(ErrorType errorType){
        super(errorType.getMessage());
        this.errorType = errorType;
    }
}

package com.furkandemiryurek.jwt_self_work.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ErrorResponse <E> {
    private E errorCode;
    private String message;
    private int status;
}

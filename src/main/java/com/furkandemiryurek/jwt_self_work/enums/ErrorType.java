package com.furkandemiryurek.jwt_self_work.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorType {
    USER_NOT_FOUND("E001", "Kullanıcı bulunamadı"),
    PRODUCT_NOT_FOUND("E002", "Ürün bulunamadı"),
    INVALID_REQUEST("E003", "Geçersiz istek"),
    USER_EXIST("E004", "Kullanıcı adına sahip kullanıcı mevcut"),
    INVALID_USERNAME_PASSWORD("E005", "Kullanıcı adı veya şifre hatalı"),
    INVALID_TOKEN("E006", "Geçersiz veya eksik token"),
    UNEXPECTED_ERROR("E999", "Beklenmeyen bir hata oluştu");

    private final String code;
    private final String message;
}

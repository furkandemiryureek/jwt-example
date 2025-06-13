package com.furkandemiryurek.jwt_self_work.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginResDto {
    private String token;
}

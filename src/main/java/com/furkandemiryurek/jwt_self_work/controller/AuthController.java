package com.furkandemiryurek.jwt_self_work.controller;

import com.furkandemiryurek.jwt_self_work.dto.LoginReqDto;
import com.furkandemiryurek.jwt_self_work.dto.LoginResDto;
import com.furkandemiryurek.jwt_self_work.dto.UserDto;
import com.furkandemiryurek.jwt_self_work.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody UserDto request) {
        authService.register(request);
        return ResponseEntity.ok("Kayıt başarılı! Lütfen e-mail adresinizi doğrulayın.");
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResDto> login(@Valid @RequestBody LoginReqDto request){
        return ResponseEntity.ok(authService.login(request));
    }
}

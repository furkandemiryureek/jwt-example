package com.furkandemiryurek.jwt_self_work.controller;

import com.furkandemiryurek.jwt_self_work.dto.UserDto;
import com.furkandemiryurek.jwt_self_work.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findById(@Valid @PathVariable("id") Long id) {
        UserDto userDto = userService.findById(id);
        return ResponseEntity.ok().body(userDto);
    }

}

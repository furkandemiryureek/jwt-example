package com.furkandemiryurek.jwt_self_work.service;

import com.furkandemiryurek.jwt_self_work.dto.LoginReqDto;
import com.furkandemiryurek.jwt_self_work.dto.LoginResDto;
import com.furkandemiryurek.jwt_self_work.dto.UserDto;
import com.furkandemiryurek.jwt_self_work.entity.User;
import com.furkandemiryurek.jwt_self_work.enums.ErrorType;
import com.furkandemiryurek.jwt_self_work.exception.CustomException;
import com.furkandemiryurek.jwt_self_work.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final JwtService jwtService;

    public void register(UserDto request) {
        if (userService.existUserByUsername(request.getUsername())){
            throw new CustomException(ErrorType.USER_EXIST);
        }
        UserDto savedUser = userService.save(request);
    }

    public LoginResDto login(LoginReqDto request){
        UserDto userFromDb;
        if (!userService.existUserByUsername(request.getUsername())){
            throw new CustomException(ErrorType.USER_NOT_FOUND);
        } else {
            userFromDb = userService.findByUsername(request.getUsername());
        }

        if (!request.getPassword().equals(userFromDb.getPassword())){
            throw new CustomException(ErrorType.INVALID_USERNAME_PASSWORD);
        }

        String token = jwtService.generateToken(userFromDb);

        return new LoginResDto(token);
    }
}

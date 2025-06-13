package com.furkandemiryurek.jwt_self_work.service;

import com.furkandemiryurek.jwt_self_work.dto.UserDto;
import com.furkandemiryurek.jwt_self_work.entity.User;
import com.furkandemiryurek.jwt_self_work.enums.ErrorType;
import com.furkandemiryurek.jwt_self_work.exception.CustomException;
import com.furkandemiryurek.jwt_self_work.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserDto save(UserDto userDto) {
        User user = User.builder().
                username(userDto.getUsername())
                .password(userDto.getPassword())
                .build();

        try {
            user = userRepository.save(user);
            return UserDto.builder()
                    .username(user.getUsername())
                    .password(user.getPassword()).build();
        }catch (Exception e) {
            System.out.printf(e.getMessage());
            throw new CustomException(ErrorType.UNEXPECTED_ERROR);
        }
    }

    public UserDto findById(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorType.USER_NOT_FOUND));
        return UserDto.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .build();
    }

    public UserDto findByUsername(String username){
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new CustomException(ErrorType.USER_NOT_FOUND));
        return UserDto.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .build();
    }

    public Boolean existUserByUsername(String username){
        Optional<User> user = Optional.ofNullable(userRepository.findByUsername(username)).orElseThrow(() -> new CustomException(ErrorType.USER_NOT_FOUND));
        if (user.isPresent())
            return Boolean.TRUE;
        return Boolean.FALSE;
    }

}

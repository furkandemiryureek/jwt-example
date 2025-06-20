package com.furkandemiryurek.jwt_self_work.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    @JsonIgnore
    private Long id;
    @NotNull(message = "Username is required")
    @Size(min = 2, max = 100, message = "Username must be between 2 and 50 characters long")
    private String username;
    @NotNull(message = "Password is required")
    @Size(min = 8, max = 100, message = "Password must be between 8 and 50 characters long")
    private String password;
}

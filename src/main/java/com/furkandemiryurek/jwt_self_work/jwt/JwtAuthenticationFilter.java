package com.furkandemiryurek.jwt_self_work.jwt;

import com.furkandemiryurek.jwt_self_work.dto.UserDto;
import com.furkandemiryurek.jwt_self_work.enums.ErrorType;
import com.furkandemiryurek.jwt_self_work.exception.CustomException;
import com.furkandemiryurek.jwt_self_work.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        final String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            throw new CustomException(ErrorType.INVALID_TOKEN);
        }

        final String token = header.substring(7);
        final String username = jwtService.extractUsername(token);

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDto userDto = userService.findByUsername(username);
            if (userDto != null && jwtService.isTokenValid(token, userDto)){
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(userDto, null, List.of(new SimpleGrantedAuthority("USER")));

                authToken.setDetails((new WebAuthenticationDetailsSource().buildDetails(request)));

                SecurityContextHolder.getContext().setAuthentication(authToken);
            } else {
                throw new CustomException(ErrorType.INVALID_TOKEN);
            }
        }
        filterChain.doFilter(request, response);
    }
}

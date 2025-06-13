package com.furkandemiryurek.jwt_self_work.repository;

import com.furkandemiryurek.jwt_self_work.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}

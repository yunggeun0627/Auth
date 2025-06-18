package com.korit.authstudy.controller;

import com.korit.authstudy.dto.LoginDto;
import com.korit.authstudy.dto.UserRegisterDto;
import com.korit.authstudy.service.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UsersController {

    private final UsersService usersService;

    @PostMapping
    public ResponseEntity<?> registerUser(@RequestBody UserRegisterDto dto) {
        log.info("DTO: {}", dto);
        return ResponseEntity.ok(usersService.register(dto));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto dto) {
        return ResponseEntity.ok(usersService.login(dto));
    }
}

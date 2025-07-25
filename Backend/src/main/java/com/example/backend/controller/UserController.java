//프론트 요청을 받는 창구
package com.example.backend.controller;

import com.example.backend.domain.User;
import com.example.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.backend.dto.LoginRequestDto;
import com.example.backend.dto.UserRegistrationRequestDto;

//여기가 REST API 컨트롤러임을 나타냄
@RestController
@RequiredArgsConstructor
//이 컨트롤러의 모든 API는 공통적으로 /api/users 라는 경로를 앞에 가짐
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    /**
     * 회원가입 API
     * HTTP Method: POST
     * URL: /api/users/register
     */
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody UserRegistrationRequestDto requestDto) {

        User userToRegister = User.builder()
                .userId(requestDto.getUserId())
                .password(requestDto.getPassword())
                .name(requestDto.getName())
                .phone(requestDto.getPhone())
                .build();

        // UserService를 호출하여 회원가입 로직 수행
        User registeredUser = userService.register(userToRegister);

        // 성공적으로 생성되었음을 의미하는 201 Created 상태 코드와 함께 사용자 정보 반환
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

    public static class LoginRequest {
        public String userId;
        public String password;
    }

    /**
     * 로그인 API
     * HTTP Method: POST
     * URL: /api/users/login
     */
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody LoginRequestDto requestDto) {
        // UserService를 호출하여 로그인 로직 수행
        User user = userService.login(requestDto.getUserId(), requestDto.getPassword());
        System.out.println(requestDto.getUserId());
        System.out.println(requestDto.getPassword());
        // 성공적으로 조회되었음을 의미하는 200 OK 상태 코드와 함께 사용자 정보 반환
        return ResponseEntity.ok(user);
    }

}

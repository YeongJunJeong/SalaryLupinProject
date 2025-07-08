package com.example.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.HashMap;

// @RestControllerAdvice: 모든 @RestController에서 발생하는 예외를 이 클래스에서 처리하도록 함
@RestControllerAdvice
public class GlobalExceptionHandler {

    // @ExceptionHandler: 특정 예외(여기서는 IllegalArgumentException)를 잡아서 처리하는 메소드
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleIllegalArgumentException(IllegalArgumentException ex) {
        // 클라이언트에게 반환할 에러 메시지를 Map 형태로 구성
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("message", ex.getMessage()); // UserService에서 던진 메시지를 그대로 담음

        // HTTP 400 Bad Request 상태 코드와 함께 에러 메시지를 반환
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}

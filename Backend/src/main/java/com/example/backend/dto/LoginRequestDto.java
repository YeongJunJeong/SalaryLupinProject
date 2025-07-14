package com.example.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter // Spring의 Jackson 라이브러리가 JSON 데이터를 객체 필드에 채워넣기 위해 Setter가 필요합니다.
public class LoginRequestDto {
    private String userId;
    private String password;
}
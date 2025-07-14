package com.example.backend.domain;

//JPA에서 제공하는 기능
//JPA는 DB와 객체를 연결해주는 기술
import jakarta.persistence.*;

//Lombok - 코드 자동완성 라이브러리
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

//생성시간 기록
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

//클래스를 DB와 연결하기
@Entity

//Lombok이 자동으로 get함수 만들어줌
@Getter

//실수로 비어 있는 객체를 만들지 못하게 하고, @Builder로만 생성하게 유도하기 위함
@NoArgsConstructor(access = AccessLevel.PROTECTED)

//테이블 연결
@Table(name = "users")
public class User {
    //기본키 기본 숫자 증가하게 하는거 (행넘버)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키 생성을 DB에 위임 (AUTO_INCREMENT) 행숫자 자동생성 DB가 알아서 하라고 명시
    @Column(name = "id") // id 필드는 DB의 'id' 컬럼에 매핑됩니다.
    private Long id; // PK (고유 식별자) 숫자형 고유값

    // userId 필드는 DB의 'user_id' 컬럼에 매핑됩니다.
    @Column(name = "user_id", nullable = false, unique = true, length = 50)
    private String userId; // 사용자 아이디

    @Column(name = "password", nullable = false)
    private String password; // 비밀번호 (암호화해서 저장될 것)

    @Column(name = "name", nullable = false, length = 100)
    private String name; // 이름

    @Column(name = "phone", nullable = false, unique = true, length = 20)
    private String phone; // 전화번호

    @CreationTimestamp // 엔티티가 생성될 때 자동으로 시간 기록

    @Column(name = "created_at")
    private Timestamp createdAt;

    //이걸 이용해서 User에 객체를 만든다.
    @Builder
    public User(String userId, String password, String name, String phone) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.phone = phone;
    }
}
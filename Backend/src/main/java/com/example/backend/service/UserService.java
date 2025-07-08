//진짜 로직은 여기

package com.example.backend.service;

import com.example.backend.domain.User;
import com.example.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder; // Spring Security의 암호화 기능
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service // 이 클래스가 비즈니스 로직을 담당하는 서비스 클래스임을 Spring에게 알려줌
@RequiredArgsConstructor // final이 붙은 필드의 생성자를 자동으로 만들어줌
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * 회원가입 로직
     * @param user 가입할 사용자 정보를 담은 User 객체 (userId, password, name, phone)
     * @return 데이터베이스에 저장된 User 정보
     */

    @Transactional // 데이터베이스 상태를 변경하므로 트랜잭션 처리 필수
    public User registerUser(User user) {
        // 1. 아이디 중복 체크
        // userRepository의 findByUserId 메소드를 호출해 이미 같은 아이디가 있는지 확인

        if(userRepository.findByUserId(user.getUserId()).isPresent()) {
            throw new IllegalArgumentException("User already exists");
        }

        // 2. 전화번호 중복 체크 (User 엔티티에 unique=true 제약조건을 추가했으므로)
        if(userRepository.findByPhone(user.getPhone()).isPresent()) {
            throw new IllegalArgumentException("Phone already exists");
        }

        // 3. 비밀번호 암호화
        // 사용자가 입력한 원본 비밀번호를 가져와 BCrypt 알고리즘으로 암호화
        String encodedPassword = passwordEncoder.encode(user.getPassword());

        // 4. 암호화된 비밀번호로 User 객체 재생성 및 저장
        // Builder 패턴을 사용하여 필수 값들과 암호화된 비밀번호로 새로운 User 객체를 생성
        User newUser = User.builder()
                .userId(user.getUserId())
                .password(encodedPassword) // 암호화된 비밀번호로 교체
                .name(user.getName())
                .phone(user.getPhone())
                .build();

        // 5. userRepository의 save 메소드를 통해 DB에 최종 저장
        return userRepository.save(newUser);
    }

    /**
     * 로그인 로직
     * @param userId 로그인 시도하는 아이디
     * @param rawPassword 사용자가 입력한 암호화되지 않은 원본 비밀번호
     * @return 로그인 성공 시 해당 User 객체
     */
    @Transactional(readOnly = true) // DB 데이터를 변경하지 않는 조회 작업이므로 readOnly = true로 성능 최적화
    public User login(String userId, String rawPassword) {
        // 1. 아이디로 사용자 조회
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // 2. 비밀번호 일치 여부 확인
        // 사용자가 입력한 원본 비밀번호(rawPassword)와 DB에 저장된 암호화된 비밀번호(user.getPassword())를 비교
        if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
            throw new IllegalArgumentException("Wrong password");
        }

        // 3. 로그인 성공 시 사용자 정보 반환
        return user;
    }

}

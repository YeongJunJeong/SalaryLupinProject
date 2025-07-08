//DB에 명령을 내리는 곳
// findByUserId같은 메소드를 정확히 어떤 기능을 하도록 하는지 정의를 해준거고
// 추가적으로 개발이 들어갈 경우 예를 들어 findByPassword 같은걸 여기서 정의하고 관리한다


package com.example.backend.repository;

import com.example.backend.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

//User 엔티티를 쓸거고 그 엔티티의 ID 타입은 Long 이다.
public interface UserRepository extends JpaRepository<User, Long>{

    //Optional을 쓰면 있을수도 있고 없을수도 있는 상황을 모두 처리해 줌.
    //그래서 정리하면 User 엔티티에 있는 데이터 중에 userId가 있을수도 있고 없을수도 있다 이 얘기
    Optional<User> findByUserId(String userId);

    Optional<User> findByPhone(String phone);
}

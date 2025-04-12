package com.likelion.springprac.repository;

import com.likelion.springprac.domain.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
// Spring이 이 클래스를 저장소(데이터 관리) 역할로 인식하게 함
public class UserRepository {

    // user 데이터를 임시 데이터베이스처럼 저장할 리스트
    // List: 구현이 간단하고 학습용으로 직관적임
    // (참고) 실제 서비스에선 Map 사용이 효율적 (id 기반 접근에 빠름)
    private final List<User> userList = new ArrayList<>();

    // 사용자 ID 자동 증가를 위한 카운터 변수
    private Long idCounter = 1L;

    // Create - 사용자 저장
    // ID를 자동 부여하고 리스트에 추가한 후 저장된 사용자 반환
    public User save(User user) {
        user.setId(idCounter++); // ID 자동 설정
        userList.add(user); // 리스트에 저장
        return user;
    }

    // Read - 전체 사용자 목록 조회
    // 리스트 복사본을 반환하여 외부에서 변경되지 않도록 보호
    public List<User> findAll() {
        return List.copyOf(userList); // 불변 리스트 반환
    }

    // Read - ID로 사용자 조회
    // Optional로 감싸 null 방지
    public Optional<User> findById(Long id) {
        return userList.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }

    // Update - 사용자 정보 수정
    // ID로 사용자 조회 후 이름과 이메일을 수정
    // 없을 경우 예외 발생 (null 반환 대신 명확한 에러 유도)
    public Optional<User> update(Long id, User updateUser) {
        return findById(id).map(user -> {
            user.setName(updateUser.getName());
            user.setEmail(updateUser.getEmail());
            return user;
        });
    }

    // Delete - 사용자 삭제
    // ID로 일치하는 사용자를 삭제하고 성공 여부 반환
    public boolean delete(Long id) {
        return userList.removeIf(user -> user.getId().equals(id));
    }
}

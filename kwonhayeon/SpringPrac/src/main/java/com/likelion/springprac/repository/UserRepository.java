package com.likelion.springprac.repository;

import com.likelion.springprac.domain.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
// Spring이 이 클래스를 저장소(데이터 관리) 역할로 인식하게 함
public class UserRepository {

    //user 데이터를 임시 데이터베이스처럼 사용하도록 저장하는 리스트
    private final List<User> userList = new ArrayList<>();
    // 사용자 ID를 자동으로 증가시키기 위한 변수
    private Long idCounter = 1L;

    // Create (데이터 생성)
    // 새로운 user를 리스트에 저장하고, ID를 자동 부여한 후 반환
    public User save(User user) {
        user.setId(idCounter++); // ID 자동 증가
        userList.add(user); // 리스트에 user 추가
        return user; // 저장된 user 반환
    }

    // Read (모든 user 조회)
    // 현재 저장된 전체 사용자 목록을 반환함
    public List<User> findAll() {
        return userList;
    }

    // Read (특정 user 조회)
    // ID로 특정 사용자를 찾아서 Optional로 감싸 반환 (null 방지용)
    public Optional<User> findById(Long id) {
        return userList.stream()
                .filter(u -> u.getId().equals(id)) // id가 일치하는 user 찾기
                .findFirst(); // 가장 먼저 찾은 결과 반환
    }

    // Update (특정 user 정보 수정)
    // ID로 기존 사용자를 찾아서, 이름과 이메일을 수정함
    public User update(Long id, User updateUser) {
        Optional<User> optionalUser = findById(id);
        optionalUser.ifPresent(user -> {
            user.setName(updateUser.getName()); // 이름 수정
            user.setEmail(updateUser.getEmail()); // 이메일 수정
        });
        return optionalUser.orElse(null); // 수정 결과 반환 (없으면 null)
    }

    // Delete (특정 user 삭제)
    // ID로 사용자를 찾아서 리스트에서 삭제, 성공하면 true 반환함
    public boolean delete(Long id) {
        return userList.removeIf(user -> user.getId().equals(id));
    }
}

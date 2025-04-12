package com.likelion.springprac.service;

import com.likelion.springprac.domain.User;
import com.likelion.springprac.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // Spring이 이 클래스를 서비스(비즈니스 로직 처리)로 인식하게 함
public class UserService {

    private final UserRepository userRepository;

    // 생성자 주입
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Create - 사용자 등록
    // 이미 동일한 이메일이 존재할 경우 예외 발생
    public User createUser(User user) {
        // 이메일 중복 체크
        boolean isEmailDuplicate = userRepository.findAll().stream()
                .anyMatch(u -> u.getEmail().equals(user.getEmail()));

        if (isEmailDuplicate) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다: " + user.getEmail());
        }

        return userRepository.save(user);
    }

    // Read - 전체 사용자 조회
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Read - ID로 특정 사용자 조회
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 사용자를 찾을 수 없습니다: " + id));
    }

    // Update - 사용자 정보 수정
    public User updateUser(Long id, User updateUser) {
        // 예외 발생 처리 포함
        User user = getUserById(id); // 존재하지 않으면 예외 발생
        user.setName(updateUser.getName());
        user.setEmail(updateUser.getEmail());
        return user;
    }

    // Delete - 사용자 삭제
    public void deleteUser(Long id) {
        boolean deleted = userRepository.delete(id);
        if (!deleted) {
            throw new IllegalArgumentException("해당 ID의 사용자를 삭제할 수 없습니다: " + id);
        }
    }
}

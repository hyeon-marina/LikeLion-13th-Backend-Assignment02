package com.likelion.springprac.service;

import com.likelion.springprac.domain.User;
import com.likelion.springprac.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// 비즈니스 로직을 처리하는 계층
// Spring에 알려주는 어노테이션
@Service
public class UserService {

    private UserRepository userRepository;

    // 생성자를 통해 Repository를 주입함 (Spring이 자동으로 연결해줌)
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 사용자 등록
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // 전체 사용자 조회
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    //ID로 사용자 조회
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // 사용자 정보 수정
    public User updateUser(Long id, User updateUser) {
        return userRepository.update(id, updateUser);
    }

    // 사용자 삭제
    public boolean deleteUser(Long id) {
        return userRepository.delete(id);
    }
}

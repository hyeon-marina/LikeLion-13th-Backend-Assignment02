package com.likelion.springprac.controller;

import com.likelion.springprac.domain.User;
import com.likelion.springprac.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// REST API 응답을 위한 컨트롤러
@RestController
// API의 기본 주소를 /users로 지정
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    // 생성자 주입
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Create (사용자 등록)
    // 전달받은 사용자 정보를 등록하고, 결과를 응답으로 반환
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = userService.createUser(user);
        return ResponseEntity.ok(savedUser); // 성공 시 200 OK + 저장된 사용자 정보
    }

    // Read All (전체 사용자 목록 조회)
    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users); // 성공 시 200 OK + 사용자 목록
    }

    // Read One (특정 사용자 조회)
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id); // 예외 발생 시 자동 처리
        return ResponseEntity.ok(user);
    }

    // Update (사용자 정보 수정)
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        User updatedUser = userService.updateUser(id, user); // 존재하지 않으면 예외 발생
        return ResponseEntity.ok(updatedUser);
    }

    // Delete (사용자 삭제)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id); // 실패 시 예외 발생
        return ResponseEntity.noContent().build(); // 204 No Content 반환
    }
}

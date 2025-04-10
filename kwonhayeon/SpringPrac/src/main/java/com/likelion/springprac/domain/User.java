package com.likelion.springprac.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // getter, setter, toString 등 자동 생성
@AllArgsConstructor // 모든 필드를 받는 생성자
@NoArgsConstructor // 기본 생성자

public class User {
    private Long id;
    private String name;
    private String email;
}

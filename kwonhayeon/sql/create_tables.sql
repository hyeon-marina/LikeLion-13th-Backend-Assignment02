-- 새 데이터베이스 생성 및 선택
CREATE DATABASE library_db;
USE library_db;

-- 사용자 테이블
CREATE TABLE user (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL, -- 이름은 필수 입력 항목이므로 NOT NULL 지정
    email VARCHAR(100) NOT NULL -- 이메일은 중복 되면 안 되므로 UNIQUE 지정
);

-- 책 테이블
CREATE TABLE book (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100) NOT NULL, -- 제목은 반드시 있어야 하므로 NOT NULL
    author VARCHAR(100),
    borrower_id INT,
    FOREIGN KEY (borrower_id) REFERENCES user(id)
);

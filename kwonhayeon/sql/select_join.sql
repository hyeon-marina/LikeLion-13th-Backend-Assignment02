USE library_db;

SELECT user.name AS 사용자, book.title AS 대출한_책
FROM user
JOIN book ON user.id = book.borrower_id;
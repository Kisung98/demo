SELECT * FROM test;

SHOW tables;

SELECT  * FROM korean1;
SELECT  count(*)  FROM korean1;
SELECT * FROM  korean1 WHERE  id = 231213; 


SELECT  * FROM korean1;
INSERT  INTO korean1 VALUES (0,'데이터1');

SELECT * FROM KOREAN1 LIMIT 1000000;

SELECT  * FROM member;



CREATE PROCEDURE InsertKoreanData(IN start_num INT, IN end_num INT)
BEGIN
    DECLARE i INT DEFAULT start_num;
    
    WHILE i <= end_num DO
        INSERT INTO korean1 (name) VALUES (CONCAT('데이터', i));
        SET i = i + 1;
    END WHILE;
END //;



CALL InsertKoreanData(2, 500);
SELECT * FROM KOREAN1 LIMIT 1000;
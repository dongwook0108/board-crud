INSERT INTO ARTICLE(id, title, content)VALUES (1, '가', '1');
INSERT INTO ARTICLE(id, title, content)VALUES (2, '나', '2');
INSERT INTO ARTICLE(id, title, content)VALUES (3, '다', '3');

--article 더미 데이터
INSERT INTO ARTICLE(id, title, content)VALUES (4, '뭡니', '댓글 냥냥');
INSERT INTO ARTICLE(id, title, content)VALUES (5, '소울푸드', '러러럴');
INSERT INTO ARTICLE(id, title, content)VALUES (6, '오홍', '끼루룩');

--comment 더미 데이터
INSERT INTO Comment(id, article_id, nickname, body)VALUES (1, 4, '동욱', '냥냥1');
INSERT INTO Comment(id, article_id, nickname, body)VALUES (2, 4, '동지', '냥냥2');
INSERT INTO Comment(id, article_id, nickname, body)VALUES (3, 4, '동구', '냥냥3');

INSERT INTO Comment(id, article_id, nickname, body)VALUES (4, 5, '이지', '냥냥1');
INSERT INTO Comment(id, article_id, nickname, body)VALUES (5, 5, '이술', '냥냥2');
INSERT INTO Comment(id, article_id, nickname, body)VALUES (6, 5, '이호', '냥냥3');

INSERT INTO Comment(id, article_id, nickname, body)VALUES (7, 6, '라지', '냥냥1');
INSERT INTO Comment(id, article_id, nickname, body)VALUES (8, 6, '라기', '냥냥2');
INSERT INTO Comment(id, article_id, nickname, body)VALUES (9, 6, '라스', '냥냥3');




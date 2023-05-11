package com.example.boardcrud.service;

import static org.junit.jupiter.api.Assertions.*;

import com.example.boardcrud.dto.ArticleForm;
import com.example.boardcrud.entity.Article;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest //해당 클래스는 스프링 부트와 연동되어 테스팅된다
class ArticleServiceTest {

    @Autowired
    ArticleService articleService;

    @Test
    void index() {
        //given
        Article a = new Article(1L, "가", "1");
        Article b = new Article(2L, "나", "2");
        Article c = new Article(3L, "다", "3");
        ArrayList<Article> expected = new ArrayList<>(Arrays.asList(a, b, c));

        //when
        List<Article> articles = articleService.index();

        //then
        assertEquals(expected.toString(), articles.toString());
    }

    @Test
    void show_성공() {
        //given
        Long id = 1L;
        Article expected = new Article(id, "가", "1");

        //when
        Article article = articleService.show(id);

        //then
        assertEquals(expected.toString(), article.toString());

    }

    @Test
    void show_실패__존재x() {
        //given
        Long id = 4L;
        Article expected = null;

        //when
        Article article = articleService.show(id);

        //then
        assertEquals(expected, article);

    }

    @Test
    @Transactional
    void create_성공() {
        //given
        String title = "라";
        String content = "4";
        ArticleForm dto = new ArticleForm(null, title, content);
        Article expected = new Article(4L, title, content);

        //when
        Article article = articleService.create(dto);

        //then
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    @Transactional
    void create_실패() {
        //given
        String title = "라";
        String content = "4";
        ArticleForm dto = new ArticleForm(4L, title, content);
        Article expected = null;

        //when
        Article article = articleService.create(dto);

        //then
        assertEquals(expected, article);

    }
}

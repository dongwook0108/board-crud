package com.example.boardcrud.controller;

import com.example.boardcrud.dto.ArticleForm;
import com.example.boardcrud.entity.Article;
import com.example.boardcrud.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ArticleController {

    private final ArticleRepository articleRepository;

    @GetMapping("/articles/new")
    public String newArticleForm() {
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form) {
//        System.out.println(form.toString());
        log.info("form.toString={}", form.toString());

        // 1. Dto -> Entity 변환
        Article article = form.toEntity();
        log.info("article={}", article);

        // 2. Repository에게 Entity를 Db안에 저장하게 시킴
        Article saved = articleRepository.save(article);
        log.info("saved={}", saved);


        return "";
    }



}

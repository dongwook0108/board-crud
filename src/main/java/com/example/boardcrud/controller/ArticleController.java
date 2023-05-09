package com.example.boardcrud.controller;

import com.example.boardcrud.dto.ArticleForm;
import com.example.boardcrud.entity.Article;
import com.example.boardcrud.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleRepository articleRepository;

    @GetMapping("/articles/new")
    public String newArticleForm() {
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form) {
        System.out.println(form.toString());

        // 1. Dto -> Entity 변환
        Article article = form.toEntity();
        System.out.println(article.toString());

        // 2. Repository에게 Entity를 Db안에 저장하게 시킴
        Article saved = articleRepository.save(article);
        System.out.println(saved);


        return "";
    }



}

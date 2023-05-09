package com.example.boardcrud.controller;

import com.example.boardcrud.dto.ArticleForm;
import com.example.boardcrud.entity.Article;
import com.example.boardcrud.repository.ArticleRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

        return "redirect:/articles/" + saved.getId();
    }

    @GetMapping("articles/{id}")
    public String getArticle(@PathVariable Long id, Model model) {
        log.info("id={}", id);
        // 1. id로 데이터를 가져온다.
//        Optional<Article> article = articleRepository.findById(id);
        Article articleEntity = articleRepository.findById(id).orElse(null);

        // 2. 가져온 데이터를 모델에 담아줌
        model.addAttribute("article", articleEntity);

        // 3. 보여줄 페이지 설정
        return "articles/show";
    }

    @GetMapping("/articles")
    public String allArticles(Model model) {
        //1. 모든 Article 가져온다
        List<Article> articleEntityList = articleRepository.findAll();

        //2. 가져온 Article 묶음으로 전달
        model.addAttribute("articleList", articleEntityList);

        //3. 뷰 페이지 설정
        return "articles/index";
    }

    @GetMapping("/articles/{id}/edit")
    public String editPage(@PathVariable Long id, Model model) {

        // 수정할 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);

        // 모델에 데이터 등록
        model.addAttribute("article", articleEntity);

        // 뷰 페이지 설정
        return "articles/edit";
    }

    @PostMapping("/articles/update")
    public String update(ArticleForm form) {
        log.info("form={}", form.toString());
        //1. Dto -> ENTITY
        Article articleEntity = form.toEntity();
        log.info("articleEntity={}", articleEntity);

        //2. Entity -> Db 저장
        //2-1 기존 db에서 값을 가져온다
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);

        //2-2 기존 데이터 값 업데이트
        if (target != null) {
            articleRepository.save(articleEntity); // 갱신 !!
        }

        //3. 수정 결과 페이지로 리다이렉트
        return "redirect:/articles/" + articleEntity.getId();
    }

}

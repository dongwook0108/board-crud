package com.example.boardcrud.service;

import com.example.boardcrud.dto.ArticleForm;
import com.example.boardcrud.entity.Article;
import com.example.boardcrud.repository.ArticleRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    public List<Article> index() {
        return articleRepository.findAll();
    }


    public Article show(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm dto) {
        Article article = dto.toEntity();

        if (article.getId() != null) {
            return null;
        }

        return articleRepository.save(article);
    }

    public Article update(Long id, ArticleForm dto) {
        Article article = dto.toEntity();

        Article target = articleRepository.findById(id).orElse(null);

        if (target == null || id != article.getId()) {
            return null;
        }

        target.patch(article);
        return articleRepository.save(target);


    }

    public Article delete(Long id) {
        Article target = articleRepository.findById(id).orElse(null);

        if (target == null) {
            return null;
        }
        articleRepository.delete(target);
        return target;

    }

    @Transactional
    public List<Article> createArticles(List<ArticleForm> dtos) {

        // dtos -> entities 변환
        List<Article> articleList =dtos.stream()
                .map(dto -> dto.toEntity())
                .collect(Collectors.toList());

        // 엔티티 묶음을 DB로 저장
        articleRepository.saveAll(articleList);

        // 강제 예외 발생
        articleRepository.findById(-1L).orElseThrow(
                () -> new IllegalArgumentException("결재 실패")
        );

        // 결과값 반환
        return articleList;
    }
}

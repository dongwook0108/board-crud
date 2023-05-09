package com.example.boardcrud.repository;

import com.example.boardcrud.entity.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article, Long> {


}

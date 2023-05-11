package com.example.boardcrud.repository;

import com.example.boardcrud.entity.Comment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByArticleId(@Param("articleId") Long articleId);

    List<Comment> findByNickname(String nickname);
}

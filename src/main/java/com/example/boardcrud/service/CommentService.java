package com.example.boardcrud.service;

import com.example.boardcrud.dto.CommentDto;
import com.example.boardcrud.entity.Article;
import com.example.boardcrud.entity.Comment;
import com.example.boardcrud.repository.ArticleRepository;
import com.example.boardcrud.repository.CommentRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;
    public List<CommentDto> comments(Long articleId) {
//        // 조회: 댓글 목록
//        List<Comment> comments = commentRepository.findByArticleId(articleId);
//        // 변환: 엔티티 -> DTO
//        List<CommentDto> dtos = new ArrayList<>();
//        for (int i = 0; i < comments.size(); i++) {
//            Comment c = comments.get(i);
//            CommentDto dto = CommentDto.createCommentDto(c);
//            dtos.add(dto);
//        }
//        // 반환
//        return dtos;
        return commentRepository.findByArticleId(articleId).stream()
                .map(comment -> CommentDto.createCommentDto(comment))
                .collect(Collectors.toList());
    }

    @Transactional
    public CommentDto create(Long articleId, CommentDto commentDto) {
        // 게시글 조회 및 예외
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("댓글 생성 실패 ! 대상 게시글이 없습니다."));

        // 댓글 엔티티 생성
        Comment comment = Comment.createComment(commentDto, article);

        // 댓글 엔티티 db로 저장
        Comment created = commentRepository.save(comment);

        // DTO로 변경해서 반환
        return CommentDto.createCommentDto(created);

    }

    @Transactional
    public CommentDto update(Long id, CommentDto dto) {
        Comment target = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("수정 실패"));

        target.patch(dto);

        Comment updated = commentRepository.save(target);

        return CommentDto.createCommentDto(updated);

    }

    @Transactional
    public CommentDto delete(Long id) {

        Comment target = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글 삭제 실패 ! 대상이 없음"));

        commentRepository.delete(target);

        return CommentDto.createCommentDto(target);
    }
}

package com.example.boardcrud.api;

import com.example.boardcrud.dto.CommentDto;
import com.example.boardcrud.service.CommentService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class CommentApiController {

    private final CommentService commentService;

    // 댓글 목록 조회
    @GetMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<List<CommentDto>> comments(@PathVariable Long articleId) {
        // 서비스에게 위임
        List<CommentDto> dtos = commentService.comments(articleId);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    // 댓글 생성
    @PostMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<CommentDto> create(@PathVariable Long articleId,
            @RequestBody CommentDto commentDto
    ) {
        //서비스에게 위임
        CommentDto createDto = commentService.create(articleId, commentDto);

        //결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(createDto);
    }

    // 댓글 수정
    @PatchMapping("/api/comments/{id}")
    public ResponseEntity<CommentDto> update(@PathVariable Long id,
            @RequestBody CommentDto commentDto
    ) {
        //서비스에게 위임
        CommentDto updateDto = commentService.update(id, commentDto);

        //결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(updateDto);
    }

    // 댓글 삭제
    @DeleteMapping("/api/comments/{id}")
    public ResponseEntity<CommentDto> delete(@PathVariable Long id) {
        CommentDto deleteDto = commentService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(deleteDto);
    }
}

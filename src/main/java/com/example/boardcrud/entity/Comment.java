package com.example.boardcrud.entity;

import com.example.boardcrud.dto.CommentDto;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne //해당 댓글 엔티티 여러개가, 하나의 Article에 연관
    @JoinColumn(name = "article_id")
    private Article article;

    @Column
    private String nickname;

    @Column
    private String body;


    public static Comment createComment(CommentDto commentDto, Article article) {
        if (commentDto.getId() != null) {
            throw new IllegalArgumentException("댓글 생성 실패, 댓글의 id가 없어야 합니다");
        }

        if (commentDto.getArticleId() != article.getId()) {
            throw new IllegalArgumentException("댓글 생성 실패, 게시글의 id가 잘못되었습니다.");
        }
        return new Comment(
                commentDto.getId(),
                article,
                commentDto.getNickname(),
                commentDto.getBody());
    }

    public void patch(CommentDto dto) {
        if (dto.getId() != this.id) {
            throw new IllegalArgumentException("댓글 수정 실패");
        }

        if (dto.getNickname() != null) {
            this.nickname = dto.getNickname();
        }

        if (dto.getBody() != null) {
            this.body = dto.getBody();
        }
    }
}

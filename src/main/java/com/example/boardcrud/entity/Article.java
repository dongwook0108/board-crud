package com.example.boardcrud.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity // 해당 클래스로 테이블을 만든다 !
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Getter
public class Article {

    // 왜 NoArgsConstructor 필요?? -> JPA에서 기본적으로 디폴트 생성자 요구
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // db가 알아서 생성
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

}

package com.nn.nerdnest.board;

import com.nn.nerdnest.BaseTimeEntity;
import com.nn.nerdnest.member.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "board")
@Getter
@NoArgsConstructor
public class Board extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String title;

    @Lob
    private String content;

    @Column(columnDefinition = "INT DEFAULT 0")
    private int view;

    @Column(columnDefinition = "INT DEFAULT 0")
    private int likeCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    public Board(String title, String content,  Member member, Category category) {
        this.title = title;
        this.content = content;
        this.member = member;
        this.category = category;
    }
}

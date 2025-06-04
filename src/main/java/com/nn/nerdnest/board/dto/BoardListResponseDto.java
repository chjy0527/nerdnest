package com.nn.nerdnest.board.dto;

import com.nn.nerdnest.board.Board;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardListResponseDto {
    // 게시글 메인에 보여줄 데이터 항목
    private Long id;
    private String title;
    private String content;
    private int view;
    private int likeCount;
    private LocalDateTime createdAt;
    private String categoryName;

    public BoardListResponseDto(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.view = board.getView();
        this.likeCount = board.getLikeCount();
        this.createdAt = board.getCreatedAt();
        this.categoryName = board.getCategory().getName();
    }
}

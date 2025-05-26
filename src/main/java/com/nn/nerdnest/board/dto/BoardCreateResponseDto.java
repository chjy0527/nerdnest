package com.nn.nerdnest.board.dto;

import com.nn.nerdnest.board.Board;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardCreateResponseDto {
    private Long id;
    private String title;
    private String content;
    private int view;
    private int likeCount;
    private LocalDateTime createdAt;

    private String writerName;
    private String writerJob;
    private int writerLevel;

    private String categoryName;

    public BoardCreateResponseDto(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.view = board.getView();
        this.likeCount = board.getLikeCount();
        this.categoryName = board.getCategory().getName();
        this.writerName = board.getMember().getUsername();
        this.writerJob = board.getMember().getJob().getName();
        this.writerLevel = board.getMember().getLevel();


    }



}

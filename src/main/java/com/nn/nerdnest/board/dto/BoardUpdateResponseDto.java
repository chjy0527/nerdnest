package com.nn.nerdnest.board.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardUpdateResponseDto {

    private Long id;
    private String title;
    private String content;
    private String writer;
    private LocalDateTime updatedAt;

    public BoardUpdateResponseDto(Long id, String title, String content, String writer, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.updatedAt = updatedAt;
    }
}

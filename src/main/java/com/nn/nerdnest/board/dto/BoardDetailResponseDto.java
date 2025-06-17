package com.nn.nerdnest.board.dto;

import lombok.Getter;

@Getter
public class BoardDetailResponseDto {

    private Long id;
    private String title;
    private String content;

    public BoardDetailResponseDto(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
}

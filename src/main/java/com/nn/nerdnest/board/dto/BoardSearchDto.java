package com.nn.nerdnest.board.dto;

import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;

@Getter
public class BoardSearchDto {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;

    public BoardSearchDto(Long id, String title, String content, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
    }
}

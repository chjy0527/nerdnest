package com.nn.nerdnest.board.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardUpdateResponseDto {

    @Schema(description = "게시글 ID", example = "1")
    private Long id;

    @Schema(description = "게시글 제목", example = "안녕하세요! 첫 게시글 입니다.")
    private String title;

    @Schema(description = "게시글 내용", example = "백엔드 개발의 기본은 무엇일까요 ?")
    private String content;

    @Schema(description = "작성자", example = "김홍길")
    private String writer;

    @Schema(description = "게시글 수정시간", example = "2025-07-08 21:09:01")
    private LocalDateTime updatedAt;

    public BoardUpdateResponseDto(Long id, String title, String content, String writer, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.updatedAt = updatedAt;
    }
}

package com.nn.nerdnest.comment.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class CommentListResponseDto {
    @Schema(description = "댓글 ID", example = "1")
    private Long id;

    @Schema(description = "댓글내용", example = "백엔드 개발의 기본은 무엇일까요 ?")
    private String content;

    @Schema(description = "작성자", example = "김홍길")
    private String writerName;

    @Schema(description = "작성자 직업", example = "개발자")
    private String writerJob;

    @Schema(description = "작성자 연차", example = "5")
    private int writerLevel;

    @Schema(description = "게시글 생성시간", example = "2025-07-08 21:09:01")
    private LocalDateTime createdAt;

    @Schema(description = "게시글 ID", example = "1")
    private Long boardId;

    @Schema(description = "대댓글 ID", example = "1")
    private Long parentId;

    private List<CommentListResponseDto> children = new ArrayList<>();

    public CommentListResponseDto(Long id, String content, String writerName, String writerJob, int writerLevel, LocalDateTime createdAt, Long boardId, Long parentId) {
        this.id = id;
        this.content = content;
        this.writerName = writerName;
        this.writerJob = writerJob;
        this.writerLevel = writerLevel;
        this.createdAt = createdAt;
        this.boardId = boardId;
        this.parentId = parentId;
    }
}

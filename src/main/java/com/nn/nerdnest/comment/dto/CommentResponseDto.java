package com.nn.nerdnest.comment.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CommentResponseDto {

    @Schema(description = "댓글 ID", example = "1")
    private Long id;

    @Schema(description = "댓글 내용", example = "저도 얼른 백엔드 개발자가 되고 싶네요 !")
    private String content;

    @Schema(description = "사용자 ID", example = "1")
    private Long memberId;

    @Schema(description = "대댓글 ID", example = "1")
    private Long parentId;

    @Schema(description = "댓글 생성시간", example = "2025-07-08 21:09:01")
    private LocalDateTime createdAt;
}

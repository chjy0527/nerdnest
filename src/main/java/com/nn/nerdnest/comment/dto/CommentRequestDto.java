package com.nn.nerdnest.comment.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommentRequestDto {
    @Schema(description = "게시글 ID", example = "1")
    private Long boardId;

    @Schema(description = "댓글 내용", example = "저도 얼른 백엔드 개발자가 되고 싶네요 !")
    private String content;

    @Schema(description = "대댓글 ID", example = "1")
    private Long parentId;

}

package com.nn.nerdnest.board.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardUpdateRequestDto {

    // 사용자가 입력할 데이터(수정작업)
    @Schema(description = "게시글 제목", example = "안녕하세요! 첫 게시글 입니다.")
    private String title;

    @Schema(description = "게시글 내용", example = "백엔드 개발의 기본은 무엇일까요 ?")
    private String content;
}

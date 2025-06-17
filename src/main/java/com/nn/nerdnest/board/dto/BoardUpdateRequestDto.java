package com.nn.nerdnest.board.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardUpdateRequestDto {

    // 사용자가 입력할 데이터(수정작업)

    private String title;
    private String content;
}

package com.nn.nerdnest.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class SearchIdResponse {

    private String email;
    private String message;
    private boolean success;


}

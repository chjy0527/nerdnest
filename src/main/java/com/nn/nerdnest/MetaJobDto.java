package com.nn.nerdnest;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MetaJobDto {

    @Schema(description = "직업 ID", example = "1")
    private Long id;

    @Schema(description = "직업 이름", example = "개발자")
    private String name;


}

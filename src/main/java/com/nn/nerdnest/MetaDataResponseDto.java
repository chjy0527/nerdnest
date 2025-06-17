package com.nn.nerdnest;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class MetaDataResponseDto {
    private List<MetaCategoryDto> categories;
    private List<MetaJobDto> jobs;


}

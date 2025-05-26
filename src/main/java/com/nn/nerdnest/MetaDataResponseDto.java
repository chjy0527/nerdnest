package com.nn.nerdnest;

import com.nn.nerdnest.board.Category;
import com.nn.nerdnest.member.Job;
import lombok.Getter;

import java.util.List;

@Getter
public class MetaDataResponseDto {
    private List<Category> categories;
    private List<Job> jobs;

    public MetaDataResponseDto(List<Category> categories, List<Job> jobs) {
        this.categories = categories;
        this.jobs = jobs;
    }
}

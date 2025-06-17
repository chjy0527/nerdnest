package com.nn.nerdnest;


import com.nn.nerdnest.board.Category;
import com.nn.nerdnest.board.CategoryRepository;
import com.nn.nerdnest.member.Job;
import com.nn.nerdnest.member.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class HomeService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private JobRepository jobRepository;

    public MetaDataResponseDto getMetaData() {
        List<Category> categoryList = categoryRepository.findAll();
        List<MetaCategoryDto> metaCategoryDtos = categoryList.stream()
                .map(c -> new MetaCategoryDto(c.getId(), c.getName()))
                .toList();

        List<Job> jobList = jobRepository.findAll();
        List<MetaJobDto> metaJobDtos = jobList.stream()
                .map(j -> new MetaJobDto(j.getId(), j.getName()))
                .toList();

        return new MetaDataResponseDto(metaCategoryDtos, metaJobDtos);
    }

}

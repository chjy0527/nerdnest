package com.nn.nerdnest;

import com.nn.nerdnest.board.Category;
import com.nn.nerdnest.board.CategoryRepository;
import com.nn.nerdnest.member.Job;
import com.nn.nerdnest.member.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private JobRepository jobRepository;

    public MetaDataResponseDto getMetaData() {
        List<Category> categories = categoryRepository.findAll();
        List<Job> jobs = jobRepository.findAll();

        System.out.println("categories = " + categories); // 여기에 뭐가 나오는지!
        System.out.println("jobs = " + jobs); // 여기도!

        return new MetaDataResponseDto(categories, jobs);
    }
}

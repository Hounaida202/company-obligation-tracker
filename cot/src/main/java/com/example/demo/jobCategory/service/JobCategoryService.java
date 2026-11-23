package com.example.demo.jobCategory.service;


import com.example.demo.jobCategory.entity.JobCategory;
import com.example.demo.jobCategory.repository.JobCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobCategoryService {

    @Autowired
    private JobCategoryRepository jobCategoryRepository;

    public List<JobCategory> getAllJobCategories() {
        return jobCategoryRepository.findAll();
    }
}
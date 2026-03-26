package com.example.demo.jobCategory.controller;

import com.example.demo.jobCategory.entity.JobCategory;
import com.example.demo.jobCategory.service.JobCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobCategories")
public class JobCategoryController {

    @Autowired
    private JobCategoryService jobCategoryService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<JobCategory> getAllJobCategories() {
        return jobCategoryService.getAllJobCategories();
    }
}
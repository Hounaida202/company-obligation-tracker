package com.example.demo.jobCategory.repository;

import com.example.demo.jobCategory.entity.JobCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobCategoryRepository extends JpaRepository<JobCategory, Long> {
}

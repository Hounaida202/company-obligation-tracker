package com.example.demo.dashboard.service;

import com.example.demo.dashboard.dto.DashboardStatsDto;
import com.example.demo.employee.dto.EmployeeSalaryDto;
import com.example.demo.employee.repository.EmployeeRepository;
import com.example.demo.employee.service.EmployeeService;
import com.example.demo.history.entity.History;
import com.example.demo.history.repository.HistoryRepository;
import com.example.demo.jobCategory.entity.JobCategory;
import com.example.demo.jobCategory.repository.JobCategoryRepository;
import com.example.demo.obligation.dto.ObligationDto;
import com.example.demo.obligation.entity.Obligation;
import com.example.demo.obligation.mapper.ObligationMapper;
import com.example.demo.obligation.repository.ObligationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DashboardService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ObligationRepository obligationRepository;

    @Autowired
    private ObligationMapper obligationMapper;

    @Autowired
    private HistoryRepository historyRepository;

    @Autowired
    private JobCategoryRepository jobCategoryRepository;

    @Autowired
    private EmployeeService employeeService;

    public DashboardStatsDto getStats() {

        List<Obligation> obligations = obligationRepository.findAll();
        long activeObligations = obligations.stream()
                .filter(o -> !"paid".equalsIgnoreCase(o.getStatus()))
                .count();

       }

     }
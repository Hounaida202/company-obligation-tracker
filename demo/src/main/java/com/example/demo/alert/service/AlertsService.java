package com.example.demo.alert.service;

import com.example.demo.alert.dto.AlertsDto;
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
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AlertsService {

    @Autowired
    private ObligationRepository obligationRepository;

    @Autowired
    private ObligationMapper obligationMapper;

    @Autowired
    private HistoryRepository historyRepository;

    @Autowired
    private JobCategoryRepository jobCategoryRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeService employeeService;

    public AlertsDto getAlerts() {
        List<Obligation> all = obligationRepository.findAll();

        LocalDate today = LocalDate.now();
        LocalDate soonThreshold = today.plusDays(5);
    }

    private boolean isPaid(Obligation o) {
        return "paid".equalsIgnoreCase(o.getStatus());
    }
}
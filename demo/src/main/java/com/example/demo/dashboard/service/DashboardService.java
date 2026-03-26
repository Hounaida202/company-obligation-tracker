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

        LocalDate today = LocalDate.now();
        LocalDate soon = today.plusDays(5);

        int upcoming = (int) obligations.stream()
                .filter(o -> o.getDueDate() != null && o.getDueDate().isBefore(soon)
                        && !"paid".equalsIgnoreCase(o.getStatus()))
                .count();


        double totalMonthly = obligations.stream()
                .mapToDouble(o -> o.getAmount() != null ? o.getAmount() : 0.0)
                .sum();


        int activeMonth = today.getMonthValue();
        int activeYear = today.getYear();


        double totalAmountThisMonth = 0.0;
        double paidAmountThisMonth = 0.0;
        long obligationsCountThisMonth = 0;


        List<ObligationDto> todayPending = new ArrayList<>();
        List<ObligationDto> todayPaid = new ArrayList<>();
        List<ObligationDto> monthTable = new ArrayList<>();

        for (Obligation ob : obligations) {
            if ("paid".equalsIgnoreCase(ob.getStatus())) continue;

            LocalDate dueDate = ob.getDueDate();
            if (dueDate == null) continue;

            boolean isToday = dueDate.isEqual(today);
            boolean isThisMonth = dueDate.getMonthValue() == activeMonth && dueDate.getYear() == activeYear;

            ObligationDto dto = obligationMapper.toDto(ob);
            dto.setType("OBLIGATION");

            if (isThisMonth) {
                monthTable.add(dto);
                totalAmountThisMonth += ob.getAmount() != null ? ob.getAmount() : 0.0;
                obligationsCountThisMonth++;
            }

            if (isToday) {
                todayPending.add(dto);
            }
        }



    }
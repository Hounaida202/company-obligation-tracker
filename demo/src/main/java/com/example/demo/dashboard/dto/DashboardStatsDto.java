package com.example.demo.dashboard.dto;

import com.example.demo.obligation.dto.ObligationDto;
import lombok.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DashboardStatsDto {
    private Long totalEmployees;
    private Long activeObligations;
    private Double totalMonthlyObligations;
    private Integer upcomingAlerts;

    private Double totalAmountThisMonth;
    private Double paidAmountThisMonth;
    private Long obligationsCountThisMonth;

    private List<ObligationDto> todayPending;
    private List<ObligationDto> todayPaid;
    private List<ObligationDto> monthTable;
}

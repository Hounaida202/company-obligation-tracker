package com.example.demo.employee.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeSalaryDto {
    private Double baseSalary;
    private Double totalDeduction;
    private Double netSalary;
    private Integer absencesCountThisMonth;
}

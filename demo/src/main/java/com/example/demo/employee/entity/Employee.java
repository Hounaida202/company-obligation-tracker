package com.example.demo.employee.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "employees")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long jobId;

    private String firstName;

    private String lastName;

    private String photo;

    private String position;

    private LocalDate entryDate;

    private Double baseSalary;
}

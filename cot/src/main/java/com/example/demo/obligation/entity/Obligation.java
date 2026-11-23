package com.example.demo.obligation.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "obligations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Obligation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long categoryId;

    private String title;

    @Column(length = 1000)
    private String description;

    private Double amount;

    private String currency;

    private String duration;

    private String supplier;

    private LocalDate firstStarted;

    private LocalDate dueDate;

    private String status;
}

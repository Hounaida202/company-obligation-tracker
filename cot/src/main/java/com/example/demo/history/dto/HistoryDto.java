package com.example.demo.history.dto;

import lombok.*;
import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class HistoryDto {
    private Long id;
    private String type;
    private Long referenceId;
    private String title;
    private Double amount;
    private String currency;
    private LocalDate paymentDate;
}

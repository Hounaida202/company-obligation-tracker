package com.example.demo.alert.dto;

import com.example.demo.obligation.dto.ObligationDto;
import lombok.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlertsDto {
    private List<ObligationDto> upcoming;
    private List<ObligationDto> overdue;
}

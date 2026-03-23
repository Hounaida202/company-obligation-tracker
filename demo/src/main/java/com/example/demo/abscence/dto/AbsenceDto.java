package com.example.demo.abscence.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AbsenceDto {
    private Long id;

    @NotNull(message = "L'employé est obligatoire")
    private Long employeeId;

    @NotNull(message = "La date est obligatoire")
    private String date;

    @NotNull(message = "La durée est obligatoire")
    @Min(value = 1, message = "La durée doit être d'au moins 1 minute")
    private Integer durationMinutes;

    private String type;
    private Boolean justified;
    private String note;
}

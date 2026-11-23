package com.example.demo.employee.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeDto {
    private Long id;

    @NotNull(message = "Le poste (catégorie métier) est obligatoire")
    private Long jobId;

    @NotBlank(message = "Le prénom est obligatoire")
    private String firstName;

    @NotBlank(message = "Le nom est obligatoire")
    private String lastName;

    private String photo;

    @NotBlank(message = "Le poste est obligatoire")
    private String position;

    private String entryDate;

    @NotNull(message = "Le salaire de base est obligatoire")
    @DecimalMin(value = "0.0", inclusive = false, message = "Le salaire doit être supérieur à 0")
    private Double baseSalary;

    private String generatedUsername;
    private String generatedPassword;

}

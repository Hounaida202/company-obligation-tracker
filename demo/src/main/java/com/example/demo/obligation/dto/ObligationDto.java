package com.example.demo.obligation.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ObligationDto {
    private Long id;

    @NotNull(message = "La catégorie est obligatoire")
    private Long categoryId;

    @NotBlank(message = "Le titre est obligatoire")
    private String title;

    private String description;

    @NotNull(message = "Le montant est obligatoire")
    @DecimalMin(value = "0.0", inclusive = false, message = "Le montant doit être supérieur à 0")
    private Double amount;

    @NotBlank(message = "La devise est obligatoire")
    private String currency;

    private String duration;

    @NotBlank(message = "Le fournisseur est obligatoire")
    private String supplier;

    private String firstStarted;

    private String dueDate;

    private String status;

    private String type;
}

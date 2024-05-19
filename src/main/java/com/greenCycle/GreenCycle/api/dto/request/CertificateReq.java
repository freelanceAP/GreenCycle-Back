package com.greenCycle.GreenCycle.api.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor


public class CertificateReq {
    @FutureOrPresent(message = "La fecha no puede ser inferior a la actual.")
    private LocalDate dateTime;
    @NotBlank(message = "Hace falta la una descripción")
    private String description; 
    private Long request;
}

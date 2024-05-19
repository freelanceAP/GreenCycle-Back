package com.greenCycle.GreenCycle.api.dto.request;

import java.time.LocalDateTime;

import com.greenCycle.GreenCycle.util.enums.StatusRequest;
import com.greenCycle.GreenCycle.util.enums.TypeWaste;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class RequestReq {

    @NotBlank(message = "Especifique la cantidad unitaria del residuo.")
    private String quantityUnit;
    @NotBlank(message = "Especifique el tipo de residuo.")
    private TypeWaste typeWaste;
    @NotBlank(message = "Hace falta la descripción.")
    private String description;
    @NotNull(message = "Especifique la fecha y la hora.")
    @FutureOrPresent(message = "La fecha no puede ser inferior a la actual.")
    private LocalDateTime dateTime;
    @NotNull(message = "Especifique el estado")
    private StatusRequest status;
    private Long userId;
}

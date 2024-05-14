package com.greenCycle.GreenCycle.api.dto.request;

import com.greenCycle.GreenCycle.util.enums.RoleUser;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class UserReq {
    @NotBlank(message = "Especifique un nombre.")
    private String userName;
    @Email(message = "El correo electrónico no es válida.")
    @Size(
        min = 5, 
        max = 100,
        message = "El email debe tener entre 5 y 100 caracteres."
    )
    private String email;
    @NotBlank(message = "Especifique un rol.")
    private RoleUser role;
    @NotBlank(message = "Especifique un NIT")
    @Size(
        min = 10, 
        max = 20,
        message = "El NIT debe tener entre 10 y 20 caracteres."
    )

    @NotBlank(message = "Especifique un rol.")
    private String password;

    private long nit;
    @NotBlank(message = "Especifique una dirección.")
    private String address;
    @Size(
        min = 10, 
        max = 20, 
        message = "El teléfono debe tener entre 10 y 20 caracteres"
    )
    private String phone;
    private String description;
    @NotNull(message = "La meta es requerida")
    private long target;
    @NotNull(message = "El progreso de la meta es requerido")
    private long targetProgress;

}

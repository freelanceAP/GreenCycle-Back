package com.greenCycle.GreenCycle.api.dto.response;

import java.time.LocalDateTime;

import com.greenCycle.GreenCycle.util.enums.StatusRequest;
import com.greenCycle.GreenCycle.util.enums.TypeWaste;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class RequestResp {
    private long id;
    private String quantityUnit;
    private TypeWaste typeWaste;
    private String description;
    private LocalDateTime dateTime;
    private StatusRequest status;
    private BasicCertificateResp certificate; 
    private String pickupAddress;
    //Se usa BasicUserResp en vez de UserEntity
    private BasicUserResp user;  //->Muestra unicamente la informacion basica del usuario, asociada a la solicitud

}

package com.greenCycle.GreenCycle.api.dto.response;

import java.time.LocalDateTime;

import com.greenCycle.GreenCycle.util.enums.StatusRequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor  

public class RequestRespToCertificateResp {
    private long id;
    private String quantityUnit;
    private String typeWaste;
    private String description;
    private LocalDateTime dateTime;
    private StatusRequest status;
    private BasicUserResp user;  

}

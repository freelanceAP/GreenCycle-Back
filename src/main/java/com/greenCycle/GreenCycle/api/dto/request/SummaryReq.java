package com.greenCycle.GreenCycle.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class SummaryReq {
    private String month;
    private Long totalRequests;
}


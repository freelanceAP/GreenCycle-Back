package com.greenCycle.GreenCycle.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class StatusCountReq {
    private long totalRequest;
    private long totalPending;
    private long totalAccepted;
    private long totalInCollection;
    private long totalInDisposition;
    private long totalFinished;
   
}

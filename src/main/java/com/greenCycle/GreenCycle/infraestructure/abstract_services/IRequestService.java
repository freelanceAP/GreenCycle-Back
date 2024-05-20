package com.greenCycle.GreenCycle.infraestructure.abstract_services;

import java.util.List;

import com.greenCycle.GreenCycle.api.dto.request.RequestReq;
import com.greenCycle.GreenCycle.api.dto.request.StatusCountReq;
import com.greenCycle.GreenCycle.api.dto.request.SummaryReq;
import com.greenCycle.GreenCycle.api.dto.response.RequestResp;

public interface IRequestService extends CrudUser<RequestReq, RequestResp, Long>{
    
    public String FIELD_BY_SORT = "status";
    List<SummaryReq> getRequestsForLastFiveMonths();
    List<SummaryReq> getRequestsForLastFiveMonthsById(Long id);
    StatusCountReq getTotalRequestsByStatus();
}

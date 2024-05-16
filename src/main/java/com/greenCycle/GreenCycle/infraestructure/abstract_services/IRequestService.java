package com.greenCycle.GreenCycle.infraestructure.abstract_services;

import com.greenCycle.GreenCycle.api.dto.request.RequestReq;
import com.greenCycle.GreenCycle.api.dto.response.RequestResp;

public interface IRequestService extends CrudUser<RequestReq, RequestResp, Long>{
    
    public String FIELD_BY_SORT = "status";
}

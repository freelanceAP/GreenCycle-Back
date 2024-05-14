package com.greenCycle.GreenCycle.infraestructure.abstract_services;

import com.greenCycle.GreenCycle.api.dto.request.UserReq;
import com.greenCycle.GreenCycle.api.dto.response.UserResp;

public interface IUserService extends CrudUser<UserReq, UserResp, Long>{
    public final String FIELD_BY_SORT = "userName";
}

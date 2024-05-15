package com.greenCycle.GreenCycle.api.dto.response;


import java.util.List;

import com.greenCycle.GreenCycle.util.enums.RoleUser;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class UserResp {
    private long id;
    private String userName;
    private String email;
    private RoleUser role;
    private String nit;
    private String address;
    private String phone;
    private String description;
    private long target;
    private long targetProgress;
    private List<RequestRespToUserResp> requests;
}

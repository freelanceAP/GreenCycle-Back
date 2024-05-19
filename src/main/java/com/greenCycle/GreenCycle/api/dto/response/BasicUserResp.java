package com.greenCycle.GreenCycle.api.dto.response;

import com.greenCycle.GreenCycle.util.enums.RoleUser;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class BasicUserResp {
    private long id;
    private String userName;
    private String email;
    private RoleUser role;
    private String nit;
}

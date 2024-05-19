package com.greenCycle.GreenCycle.api.dto.errors;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor

public class BaseErrorsResponse implements Serializable{
    private Integer code;
    private String status;
}

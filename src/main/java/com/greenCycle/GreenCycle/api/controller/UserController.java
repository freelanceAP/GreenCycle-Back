package com.greenCycle.GreenCycle.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.greenCycle.GreenCycle.api.dto.request.UserReq;
import com.greenCycle.GreenCycle.api.dto.response.UserResp;
import com.greenCycle.GreenCycle.infraestructure.abstract_services.IUserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/users")
@AllArgsConstructor

public class UserController {
    private final IUserService userService;


    @PostMapping
    public ResponseEntity<UserResp> insert(
        @Validated @RequestBody UserReq request
    ){
        return ResponseEntity.ok(userService.create(request));
    }

}

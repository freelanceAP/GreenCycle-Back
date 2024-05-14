package com.greenCycle.GreenCycle.api.controller;

import java.util.Objects;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.greenCycle.GreenCycle.api.dto.request.UserReq;
import com.greenCycle.GreenCycle.api.dto.response.UserResp;
import com.greenCycle.GreenCycle.infraestructure.abstract_services.IUserService;
import com.greenCycle.GreenCycle.util.enums.SortType;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/users")
@AllArgsConstructor

public class UserController {
    private final IUserService userService;

    @GetMapping
    public ResponseEntity<Page<UserResp>> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestHeader(required = false) SortType sortType) {

        if (Objects.isNull(sortType))
            sortType = SortType.NONE;

        return ResponseEntity.ok(this.userService.getAll(page -1, size, sortType));

    }

    @PostMapping
    public ResponseEntity<UserResp> insert(
            @Validated @RequestBody UserReq request) {
        return ResponseEntity.ok(userService.create(request));
    }

}

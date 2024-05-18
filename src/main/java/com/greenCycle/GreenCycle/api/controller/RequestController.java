package com.greenCycle.GreenCycle.api.controller;

import java.util.Objects;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.greenCycle.GreenCycle.api.dto.request.RequestReq;
import com.greenCycle.GreenCycle.api.dto.response.RequestResp;
import com.greenCycle.GreenCycle.infraestructure.abstract_services.IRequestService;
import com.greenCycle.GreenCycle.util.enums.SortType;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "request")
@AllArgsConstructor
public class RequestController {

    private final IRequestService requestService;

    // Anotacion para obtener
    @GetMapping(path = "/{id}")
    public ResponseEntity<RequestResp> getById(
            @PathVariable("id") Long id) {
        // Se llama al servicio donde esta el metodo para obtener y se reporta el valor
        // obtenido
        return ResponseEntity.ok(this.requestService.get(id));
    }

    // Anotacion para obtener
    @GetMapping
    public ResponseEntity<Page<RequestResp>> getAll(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestHeader(required = false) SortType sortType) {

        if (Objects.isNull(sortType))
            sortType = SortType.NONE;

        // Se llama al servicio donde esta el metodo para listar todo y se retorna todos
        // los valores obtenidos
        return ResponseEntity.ok(this.requestService.getAll(page - 1, size, sortType));

    }

    @PostMapping
    public ResponseEntity<RequestResp> insert(
            @Validated @RequestBody RequestReq request) {
        // Se llama al servicio donde esta el metodo para crear y se reporta el valor
        // obtenido
        return ResponseEntity.ok(this.requestService.create(request));
    }

    // Anotacion para actualizar
    @PutMapping(path = "/{id}")
    public ResponseEntity<RequestResp> update(
            @Validated @RequestBody RequestReq request,
            @PathVariable("id") Long id) {
        // Se llama al servicio donde esta el metodo para actualizar y se reporta el
        // valor
        // obtenido
        return ResponseEntity.ok(this.requestService.update(request, id));
    }

    // eliminar por ID
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        // Se llama al servicio donde esta el metodo para eliminar
        this.requestService.delete(id);
        return ResponseEntity.noContent().build();
    }

}

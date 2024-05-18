package com.greenCycle.GreenCycle.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greenCycle.GreenCycle.api.dto.request.CertificateReq;
import com.greenCycle.GreenCycle.api.dto.response.CertificateResp;
import com.greenCycle.GreenCycle.domain.entities.CertificateEntity;
import com.greenCycle.GreenCycle.infraestructure.service.CertificateService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "certificate")
@AllArgsConstructor
public class CertificateController {

    @Autowired
    private final CertificateService cetificateService;

    // Anotacion para obtener
    @GetMapping(path = "/{id}")
    public ResponseEntity<CertificateResp> getById(
            @PathVariable("id") Long id) {
        // Se llama al servicio donde esta el metodo para obtener y se reporta el valor
        // obtenido
        return ResponseEntity.ok(this.cetificateService.get(id));

    }

    @PostMapping
    public ResponseEntity<CertificateResp> insert(
            @Validated @RequestBody CertificateReq request) {
        // Se llama al servicio donde esta el metodo para crear y se reporta el valor
        // obtenido
        return ResponseEntity.ok(this.cetificateService.create(request));
    }

    // Anotacion para obtener
    @GetMapping
    public ResponseEntity<List<CertificateEntity>> getAll() {
        return ResponseEntity.ok(cetificateService.findAll());
    }

    // Anotacion para actualizar
    @PutMapping(path = "/{id}")
    public ResponseEntity<CertificateResp> update(
            @Validated @RequestBody CertificateReq request,
            @PathVariable("id") Long id) {
        // Se llama al servicio donde esta el metodo para actualizar y se reporta el
        // valor
        // obtenido
        return ResponseEntity.ok(this.cetificateService.update(request, id));
    }

    // anotacion para eliminar
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        // Se llama al servicio donde esta el metodo para eliminar
        this.cetificateService.delete(id);
        return ResponseEntity.noContent().build();
    }

}

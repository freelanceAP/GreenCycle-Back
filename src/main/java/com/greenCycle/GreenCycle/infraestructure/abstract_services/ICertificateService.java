package com.greenCycle.GreenCycle.infraestructure.abstract_services;

import java.util.List;

import com.greenCycle.GreenCycle.api.dto.request.CertificateReq;
import com.greenCycle.GreenCycle.api.dto.response.CertificateResp;
import com.greenCycle.GreenCycle.domain.entities.CertificateEntity;

public interface ICertificateService extends CrudUser<CertificateReq, CertificateResp, Long> {
    
    public List<CertificateEntity> findAll();
}

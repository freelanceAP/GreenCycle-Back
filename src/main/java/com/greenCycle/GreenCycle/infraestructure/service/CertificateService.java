package com.greenCycle.GreenCycle.infraestructure.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.greenCycle.GreenCycle.api.dto.request.CertificateReq;
import com.greenCycle.GreenCycle.api.dto.response.CertificateResp;
import com.greenCycle.GreenCycle.api.dto.response.RequestRespToCertificateResp;
import com.greenCycle.GreenCycle.domain.entities.CertificateEntity;
import com.greenCycle.GreenCycle.domain.entities.RequestEntity;
import com.greenCycle.GreenCycle.domain.repositories.CertificateRepository;
import com.greenCycle.GreenCycle.domain.repositories.RequestRepository;
import com.greenCycle.GreenCycle.infraestructure.abstract_services.ICertificateService;
import com.greenCycle.GreenCycle.util.enums.SortType;
import com.greenCycle.GreenCycle.util.enums.exception.BadRequestException;
import com.greenCycle.GreenCycle.util.enums.messages.ErrorMessages;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor

public class CertificateService implements ICertificateService {

    @Autowired
    private final CertificateRepository certificateRepository;

    @Autowired
    private final RequestRepository requestRepository;

    @Override
    public CertificateResp create(CertificateReq request) {

        CertificateEntity entity = this.requestToentity(request);

        return this.entityToresp(this.certificateRepository.save(entity));
    }

    @Override
    public CertificateResp get(Long id) {

        return this.entityToresp(this.find(id));
    }

    @Override
    public CertificateResp update(CertificateReq request, Long id) {

        CertificateEntity user = this.find(id);

        CertificateEntity userUpdate = this.requestToentity(request);
        userUpdate.setId(user.getId());

        return this.entityToresp(this.certificateRepository.save(userUpdate));
    }

    @Override
    public void delete(Long id) {

        CertificateEntity user = this.find(id);
        this.certificateRepository.delete(user);
    }

    @Override
    public Page<CertificateResp> getAll(int page, int size, SortType sortType) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public List<CertificateEntity> findAll() {
        return this.certificateRepository.findAll();
    }

    private CertificateResp entityToresp(CertificateEntity entity) {

        System.out.println(entity);

        // RequestRespToCertificateResp requestRespToCertificateResp =
        // this.EntityRequestToCertResp(entity.getRequest());
        // CertificateResp certificateResp = new CertificateResp();

        // BeanUtils.copyProperties(entity, certificateResp);
        // certificateResp.setRequest(requestRespToCertificateResp);

        // return certificateResp;

        System.out.println(entity);
        RequestRespToCertificateResp requestRespToCertificateResp = new RequestRespToCertificateResp();
        BeanUtils.copyProperties(entity.getRequest(), requestRespToCertificateResp);

        return CertificateResp.builder()
                .id(entity.getId())
                .dateTime(entity.getDateTime())
                .description(entity.getDescription())
                .request(requestRespToCertificateResp)
                .build();

    }

    private RequestRespToCertificateResp EntityRequestToCertResp(RequestEntity entity) {

        return RequestRespToCertificateResp.builder()
                .id(entity.getId())
                .quantityUnit(entity.getQuantityUnit())
                .typeWaste(entity.getTypeWaste())
                .description(entity.getDescription())
                .dateTime(entity.getDateTime())
                .status(entity.getStatus())
                .build();

    }

    private CertificateEntity requestToentity(CertificateReq resquest) {

        return CertificateEntity.builder()
                .dateTime(resquest.getDateTime())
                .description(resquest.getDescription())
                .request(this.requestRepository.findById(resquest.getRequest()).orElseThrow())
                .build();
    }

    private CertificateEntity find(Long id) {
        return this.certificateRepository.findById(id)
                .orElseThrow(() -> new BadRequestException(ErrorMessages.idNotFound("certificados")));
    }
}

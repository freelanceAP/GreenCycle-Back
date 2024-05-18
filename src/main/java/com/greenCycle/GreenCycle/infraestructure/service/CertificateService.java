package com.greenCycle.GreenCycle.infraestructure.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.greenCycle.GreenCycle.api.dto.request.CertificateReq;
import com.greenCycle.GreenCycle.api.dto.response.CertificateResp;
import com.greenCycle.GreenCycle.domain.entities.CertificateEntity;
import com.greenCycle.GreenCycle.domain.repositories.CertificateRepository;
import com.greenCycle.GreenCycle.infraestructure.abstract_services.ICertificateService;
import com.greenCycle.GreenCycle.util.enums.SortType;
import com.greenCycle.GreenCycle.util.enums.exception.BadRequestException;
import com.greenCycle.GreenCycle.util.enums.messages.ErrorMessages;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CertificateService implements ICertificateService{

    @Autowired
    private final CertificateRepository certificateRepository;

    // @Autowired
    // private final RequestRepository requestRepository;

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
        
        CertificateEntity  user = this.find(id);
        
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

    private CertificateResp entityToresp(CertificateEntity entity){
        return CertificateResp.builder()
        .id(entity.getId())
        .dateTime(entity.getDateTime())
        .description(entity.getDescription())
        .build();

    }
    
    private CertificateEntity requestToentity(CertificateReq resquest){

        return CertificateEntity.builder()
        .dateTime(resquest.getDateTime())
        .description(resquest.getDescription())
        .build();
    }

        private CertificateEntity find(Long id) {
        return this.certificateRepository.findById(id).orElseThrow(() -> new BadRequestException(ErrorMessages.idNotFound("certificados")));
    }
}

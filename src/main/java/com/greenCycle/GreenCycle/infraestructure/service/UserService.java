package com.greenCycle.GreenCycle.infraestructure.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.greenCycle.GreenCycle.api.dto.request.UserReq;
import com.greenCycle.GreenCycle.api.dto.response.CertificateResp;
import com.greenCycle.GreenCycle.api.dto.response.RequestRespToUserResp;
import com.greenCycle.GreenCycle.api.dto.response.UserResp;
import com.greenCycle.GreenCycle.domain.entities.RequestEntity;
import com.greenCycle.GreenCycle.domain.entities.UserEntity;
import com.greenCycle.GreenCycle.domain.repositories.UserRepository;
import com.greenCycle.GreenCycle.infraestructure.abstract_services.IUserService;
import com.greenCycle.GreenCycle.util.enums.SortType;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class UserService implements IUserService {

    @Autowired
    private final UserRepository userRepository;

    @Override
    public UserResp create(UserReq request) {

        UserEntity entity = this.requestToEntity(request);

        return this.entityToResponse(this.userRepository.save(entity));
    }

    @Override
    public UserResp get(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }

    @Override
    public UserResp update(UserReq request, Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public Page<UserResp> getAll(int page, int size, SortType sortType) {

        if (page < 0)
            page = 0;

        PageRequest pagination = null;
        switch (sortType) {
            case NONE -> pagination = PageRequest.of(page, size);
            case ASC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).ascending());
            case DESC -> pagination = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).descending());
        }

        return this.userRepository.findAll(pagination).map(this::entityToResponse);
    }

    private UserResp entityToResponse(UserEntity entity) {
        
        // List<RequestEntity> requestEntity = entity.getRequests();

        
        List<RequestRespToUserResp> list = entity.getRequests().stream().map(this::requestEntityToResquesRespToUserResp).collect(Collectors.toList());

        return UserResp.builder()
                .id(entity.getId())
                .userName(entity.getUserName())
                .email(entity.getEmail())
                .role(entity.getRole())
                .nit(entity.getNit())
                .address(entity.getAddress())
                .phone(entity.getPhone())
                .description(entity.getDescription())
                .target(entity.getTarget())
                .targetProgress(entity.getTargetProgress())
                .requests(list)
                .build();

    }

    private RequestRespToUserResp requestEntityToResquesRespToUserResp(RequestEntity entity){
        RequestRespToUserResp response = new RequestRespToUserResp();
        CertificateResp certResp = new CertificateResp();
        BeanUtils.copyProperties(entity.getCertificate(), certResp);
        BeanUtils.copyProperties(entity, response);
        response.setCertificate(certResp);
        return response;
    }

    private UserEntity requestToEntity(UserReq request) {
        return UserEntity.builder()
                .userName(request.getUserName())
                .email(request.getEmail())
                .password(request.getPassword())
                .role(request.getRole())
                .nit(request.getNit())
                .address(request.getAddress())
                .phone(request.getPhone())
                .description(request.getDescription())
                .target(request.getTarget())
                .targetProgress(request.getTargetProgress())
                .build();
    }

    private RequestRespToUserResp entityToResponseRequest(RequestEntity entity){
        return RequestRespToUserResp.builder()

    }

}

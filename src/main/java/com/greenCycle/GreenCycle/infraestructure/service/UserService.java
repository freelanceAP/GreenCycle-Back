package com.greenCycle.GreenCycle.infraestructure.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.greenCycle.GreenCycle.api.dto.request.UserReq;
import com.greenCycle.GreenCycle.api.dto.response.BasicCertificateResp;
import com.greenCycle.GreenCycle.api.dto.response.RequestRespToUserResp;
import com.greenCycle.GreenCycle.api.dto.response.UserResp;
import com.greenCycle.GreenCycle.domain.entities.RequestEntity;
import com.greenCycle.GreenCycle.domain.entities.UserEntity;
import com.greenCycle.GreenCycle.domain.repositories.UserRepository;
import com.greenCycle.GreenCycle.infraestructure.abstract_services.IUserService;
import com.greenCycle.GreenCycle.util.enums.SortType;
import com.greenCycle.GreenCycle.util.enums.exception.BadRequestException;
import com.greenCycle.GreenCycle.util.enums.messages.ErrorMessages;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class UserService implements IUserService {

    @Autowired
    private final UserRepository userRepository;

    @Override
    // Cre crea metodo publico lpara crear
    public UserResp create(UserReq request) {

        // Se llama al metodo que convierte la resquest en respuesta
        // y al metodo que busca por id para saber cual se va listar
        UserEntity entity = this.requestToEntity(request);
        entity.setRequests(new ArrayList<>());
        return this.entityToResponse(this.userRepository.save(entity));
    }

    // Metodo para listar por ID
    @Override
    public UserResp get(Long id) {
        // Se llama al metodo que convierte la entidad en respuesta
        // y al metodo que busca por id para saber cual se va listar
        return this.entityToResponse(this.find(id));
    }

    @Override
    public UserResp update(UserReq request, Long id) {

        UserEntity user = this.find(id);

        UserEntity userUpdate = this.requestToEntity(request);
        userUpdate.setId(user.getId());
        userUpdate.setRequests(user.getRequests());

        return this.entityToResponse(this.userRepository.save(userUpdate));
    }

    @Override
    public void delete(Long id) {
        UserEntity user = this.find(id);
        this.userRepository.delete(user);

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

        List<RequestRespToUserResp> list = entity.getRequests().stream().map(this::requestEntityToResquesRespToUserResp)
                .collect(Collectors.toList());

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

    private RequestRespToUserResp requestEntityToResquesRespToUserResp(RequestEntity entity) {
        RequestRespToUserResp response = new RequestRespToUserResp();
        BasicCertificateResp certResp = new BasicCertificateResp();
        if (entity.getCertificate() != null) {
            BeanUtils.copyProperties(entity.getCertificate(), certResp);

        }
        BeanUtils.copyProperties(entity, response);
        response.setCertificate(certResp);
        return response;
    }

    private UserEntity requestToEntity(UserReq request) {
        List<RequestEntity> list = new ArrayList<>();
        UserEntity entity = new UserEntity();
        BeanUtils.copyProperties(request, entity);
        entity.setRequests(list);

        return entity;
        // return UserEntity.builder()
        // .userName(request.getUserName())
        // .email(request.getEmail())
        // .password(request.getPassword())
        // .role(request.getRole())
        // .nit(request.getNit())
        // .address(request.getAddress())
        // .phone(request.getPhone())
        // .description(request.getDescription())
        // .target(request.getTarget())
        // .targetProgress(request.getTargetProgress())
        // .requests(list)
        // .build();

    }

    private UserEntity find(Long id) {
        return this.userRepository.findById(id).orElseThrow(() -> new BadRequestException(ErrorMessages.idNotFound("usuario")));
    }

}

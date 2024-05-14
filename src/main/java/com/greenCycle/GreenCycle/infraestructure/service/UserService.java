package com.greenCycle.GreenCycle.infraestructure.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.greenCycle.GreenCycle.api.dto.request.UserReq;
import com.greenCycle.GreenCycle.api.dto.response.UserResp;
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    private UserResp entityToResponse(UserEntity entity){
        return UserResp.builder()
        .id(entity.getId())
        .userName(entity.getUserName())
        .email(entity.getEmail())
        .password(entity.getPassword())
        .role(entity.getRole())
        .nit(entity.getNit())
        .address(entity.getAddress())
        .phone(entity.getPhone())
        .description(entity.getDescription())
        .target(entity.getTarget())
        .targetProgress(entity.getTargetProgress())
        .build();

    }


    private UserEntity requestToEntity(UserReq request){
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

 
}

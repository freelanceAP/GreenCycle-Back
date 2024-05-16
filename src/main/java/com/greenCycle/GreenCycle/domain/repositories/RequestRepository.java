package com.greenCycle.GreenCycle.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greenCycle.GreenCycle.domain.entities.RequestEntity;

public interface RequestRepository extends JpaRepository<RequestEntity, Long>{
    
    
}

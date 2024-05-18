package com.greenCycle.GreenCycle.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greenCycle.GreenCycle.domain.entities.CertificateEntity;

public interface CertificateRepository extends JpaRepository<CertificateEntity, Long> {
    
    public String FIELD_BY_SORT = "";
}

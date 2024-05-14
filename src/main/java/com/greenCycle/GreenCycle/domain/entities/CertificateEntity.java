package com.greenCycle.GreenCycle.domain.entities;

import java.time.LocalDate;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "certificates")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class CertificateEntity {
    @Id
    @GeneratedValue
    private long id;
    @Column(nullable = false)
    private LocalDate dateTime;
    @Column(length = 100, nullable = false)
    private String description; 
    
    /*RELACIONES*/
    //Un certificado puede tener una solicitud
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "request_id")
    private RequestEntity request;
    
}

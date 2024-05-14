package com.greenCycle.GreenCycle.domain.entities;

import java.time.LocalDateTime;

import com.greenCycle.GreenCycle.util.enums.StatusRequest;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity(name = "requests")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class RequestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 100)
    private String quantityUnit;
    @Column(length = 100)
    private String typeWaste;
    @Column(length = 100)
    private String description;
    @Column(nullable = false)
    private LocalDateTime dateTime;
    @Enumerated(EnumType.STRING)
    private StatusRequest status;

    /*RELACIONES*/
    //Una solicitud puede tener un certificado
    @OneToOne(mappedBy = "request", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private  CertificateEntity certificate; 

    //Muchas solicitudes puede tener un usuario
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user; 
}

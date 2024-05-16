package com.greenCycle.GreenCycle.domain.entities;

import java.util.List;

import com.greenCycle.GreenCycle.util.enums.RoleUser;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 100, nullable = false)
    private String userName;
    @Column(length = 100, nullable = false)
    private String email;
    @Column(length = 100, nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    private RoleUser role;
    @Column(length = 20, nullable = false)
    private String nit;
    @Column(length = 100)
    private String address;
    @Column(length = 20)
    private String phone;
    @Column(length = 100)
    private String description;
    @Column(length = 20, nullable = false)
    // ->Meta que la empresa debe de alcanzar
    private long target;
    // ->Progreso de la meta
    @Column(length = 20, nullable = false)
    private long targetProgress;

    /* RELACIONES */

    //Un usuario puede tener muchas solicitudes
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = false)
    private List<RequestEntity> requests; //Lista de las solicitudes que tiene el usuario 
}

package com.squezada.msvc.sucursales.msvc_sucursales.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Entity
@Table( name = "sucursales")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor

public class Sucursal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_sucursal")
    private long idSucursal;

    @Column(nullable = false, unique = true)
    @NotEmpty(message = "El id de la sucursal no puede ser vacio")
    private String id;

}

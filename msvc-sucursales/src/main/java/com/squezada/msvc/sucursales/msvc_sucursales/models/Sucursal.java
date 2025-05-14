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
    private long idsucursal;
    private String horario;
    private String direccion;


    @Column(nullable = false, unique = true)
    @NotEmpty(message = "El id de la sucursal no puede ser vacio")
    private long id;

}

package com.squezada.msvc.sucursales.msvc_sucursales.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Entity
@Table( name = "sucursales")
@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor

public class Sucursal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    @NotEmpty(message = "El id de la sucursal no puede ser vacio")
    private long idsucursal;

    @Column(nullable = false, unique = true)
    private String horario;

    @Column(nullable = false, unique = true)
    @NotEmpty(message = "La direccion de la sucursal no puede ser vacio")
    private String direccion;





}

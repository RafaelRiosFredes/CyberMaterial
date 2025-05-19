package com.squezada.msvc.sucursales.msvc_sucursales.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table( name = "sucursales")
@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor

public class Sucursal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    @NotNull(message = "El id de la sucursal no puede ser vacio")
    private long idsucursal;

    @Column(nullable = false, unique = true)
    private String horario;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "La direccion de la sucursal no puede ser vacio")
    private String direccion;





}

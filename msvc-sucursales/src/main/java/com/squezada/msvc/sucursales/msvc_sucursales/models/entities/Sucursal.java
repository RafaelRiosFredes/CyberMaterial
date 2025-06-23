package com.squezada.msvc.sucursales.msvc_sucursales.models.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Entity
@Table( name = "sucursales")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Entidad que representa una sucursal")

public class Sucursal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sucursal")
    @Schema(description = "Codigo de la sucursal", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long idSucursal;

    @NotBlank(message = "El horario de la sucursal no puede ser vacio")
    @Column(name = "horario", nullable = false, unique = true)
    @Schema(description = "Horario de la sucursal", example = "9:00-19:00 hrs")
    private String horario;

    @NotBlank(message = "La direccion de la sucursal no puede ser vacio")
    @Column(name = "direccion", nullable = false, unique = true)
    @Schema(description = "Direccion de la sucursal", example = "Av Matta 123, Villa Alemana" )
    private String direccion;



    public Sucursal(String horario, String direccion) {
        this.horario = horario;
        this.direccion = direccion;
    }



}

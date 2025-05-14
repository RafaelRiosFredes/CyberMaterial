package com.squezada.msvc.sucursales.msvc_sucursales.dtos;


import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class SucursalDTO {
    private long idsucursal;

    @NotBlank (message = "El horario de atencion no puede estar vacio")
    private String horarioAtencion;

    @NotBlank(message = "La direccion no puede estar vacia")
    private String direccion;


}

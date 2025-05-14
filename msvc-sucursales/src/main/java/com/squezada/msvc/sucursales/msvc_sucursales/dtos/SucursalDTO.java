package com.squezada.msvc.sucursales.msvc_sucursales.dtos;


import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class SucursalDTO {
    private long id;
    private LocalDateTime horaAtencion;
    private String direccion;
}

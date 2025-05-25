package com.squezada.msvc.detallecompras.msvc_detallecompras.dtos;

import lombok.*;

@Getter@Setter@ToString
@NoArgsConstructor@AllArgsConstructor
public class SucursalDTO {
    private Long idSucursal;
    private String horario;
    private String direccion;
}

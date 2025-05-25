package com.rrios.msvc.boletas.msvc_boletas.dtos;

import lombok.*;

@Getter@Setter@ToString
@NoArgsConstructor@AllArgsConstructor
public class SucursalDTO {
    private Long idSucursal;
    private String horario;
    private String direccion;
}

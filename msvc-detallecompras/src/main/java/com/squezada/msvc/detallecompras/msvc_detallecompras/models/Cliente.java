package com.squezada.msvc.detallecompras.msvc_detallecompras.models;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    private Long idCliente;
    private String run;
    private String nombres;
    private String apellidos;
    private Integer telefono;
    private String correo;
    private String direccion;
}

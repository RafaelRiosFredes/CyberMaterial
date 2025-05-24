package com.rrios.msvc.boletas.msvc_boletas.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter
@NoArgsConstructor@AllArgsConstructor
public class Cliente {
    private Long idCliente;
    private String run;
    private String nombres;
    private String apellidos;
    private Integer telefono;
    private String correo;
    private String direccion;
}

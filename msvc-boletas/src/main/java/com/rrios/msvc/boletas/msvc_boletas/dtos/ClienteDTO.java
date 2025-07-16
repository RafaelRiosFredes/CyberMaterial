package com.rrios.msvc.boletas.msvc_boletas.dtos;

import lombok.*;

@Getter@Setter@ToString
@NoArgsConstructor@AllArgsConstructor
public class ClienteDTO {
    private String run;
    private String nombres;
    private String apellidos;
    private String telefono;
    private String correo;
    private String direccion;
}

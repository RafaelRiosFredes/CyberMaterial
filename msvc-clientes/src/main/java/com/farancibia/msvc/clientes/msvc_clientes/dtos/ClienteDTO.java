package com.farancibia.msvc.clientes.msvc_clientes.dtos;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ClienteDTO {
    private String run;
    private String nombres;
    private String apellidos;
    private String telefono;
    private String correo;
    private String direccion;
}

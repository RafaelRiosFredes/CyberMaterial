package com.farancibia.msvc.inventarios.msvc_inventarios.models;

import lombok.*;

@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
public class Sucursal {
    private Long idSucursal;
    private Long idProducto;
    private Long idInventario;
    private String horaApertura;
    private String direccion;

}

package com.farancibia.msvc.inventarios.msvc_inventarios.models;


import lombok.*;

@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
public class Producto {
    private Long idProducto;
    private Long idSucursal;
    private Long idInventario;
    private String nombreProducto;
    private Long precio;
    private String descripcion;
}

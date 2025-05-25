package com.rrios.msvc.boletas.msvc_boletas.models;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Producto {
    private Long idProducto;
    private String nombreProducto;
    private Double precio;
    private String descripcion;
}

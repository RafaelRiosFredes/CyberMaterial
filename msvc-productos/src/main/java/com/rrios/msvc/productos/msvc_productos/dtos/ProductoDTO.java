package com.rrios.msvc.productos.msvc_productos.dtos;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProductoDTO {
    private String nombreProducto;
    private Integer precio;
    private String descripcion;
}

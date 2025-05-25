package com.rrios.msvc.boletas.msvc_boletas.dtos;

import lombok.*;

@Getter@Setter@ToString
@NoArgsConstructor@AllArgsConstructor
public class ProductoDTO {
    private Long idProducto;
    private String nombreProducto;
    private Double precio;
    private String descripcion;
}

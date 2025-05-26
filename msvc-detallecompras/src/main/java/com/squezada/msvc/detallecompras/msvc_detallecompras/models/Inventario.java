package com.squezada.msvc.detallecompras.msvc_detallecompras.models;

import lombok.*;

@Getter@Setter@ToString
@NoArgsConstructor@AllArgsConstructor
public class Inventario {
    private Long idInventario;
    private Integer stock;
    private Long idSucursal;
    private Long idProducto;
}

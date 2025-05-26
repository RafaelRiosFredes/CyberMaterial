package com.squezada.msvc.detallecompras.msvc_detallecompras.dtos;

import lombok.*;

@Getter@Setter@ToString
@NoArgsConstructor@AllArgsConstructor
public class InventarioDTO {
    private Long idInventario;
    private Integer stock;
    private Long idSucursal;
    private Long idProducto;
}

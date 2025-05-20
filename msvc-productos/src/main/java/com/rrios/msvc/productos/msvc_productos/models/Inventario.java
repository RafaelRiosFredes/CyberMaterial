package com.rrios.msvc.productos.msvc_productos.models;

import lombok.*;
import org.springframework.stereotype.Service;

@Getter@Setter@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Inventario {
    private Long idInventario;
    private Integer stock;
    private Long idSucursal;
    private Long idProducto;
}

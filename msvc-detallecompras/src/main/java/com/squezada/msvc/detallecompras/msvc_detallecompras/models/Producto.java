package com.squezada.msvc.detallecompras.msvc_detallecompras.models;


import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter @Setter@ToString
@NoArgsConstructor@AllArgsConstructor
public class Producto {
    private Long idProducto;
    private String nombreProducto;
    private Double precio;
    private String descripcion;
}




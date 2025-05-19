package com.squezada.msvc.sucursales.msvc_sucursales.models;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter @Setter@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Producto {
    private Long idproducto;
    private String nombreProducto;
    private BigDecimal precio;
    private String descripcion;

}
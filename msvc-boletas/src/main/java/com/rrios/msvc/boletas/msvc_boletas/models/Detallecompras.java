package com.rrios.msvc.boletas.msvc_boletas.models;

import lombok.*;

import java.math.BigDecimal;

@Getter@Setter@ToString
@NoArgsConstructor@AllArgsConstructor
public class Detallecompras {
    private Long idDetallecompras;
    private String cantidad;
    private BigDecimal total;
    private Long idProducto;
}

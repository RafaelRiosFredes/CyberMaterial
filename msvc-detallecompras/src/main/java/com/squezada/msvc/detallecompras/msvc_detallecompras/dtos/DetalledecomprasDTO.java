package com.squezada.msvc.detallecompras.msvc_detallecompras.dtos;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DetalledecomprasDTO {

    private long iddetallecompras;
    private long idProducto;
    private String cantidad;
    private BigDecimal total;
    private ProductoDTO producto;



}

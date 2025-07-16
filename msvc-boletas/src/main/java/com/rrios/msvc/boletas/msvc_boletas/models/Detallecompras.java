package com.rrios.msvc.boletas.msvc_boletas.models;

import lombok.*;

@Getter@Setter@ToString
@NoArgsConstructor@AllArgsConstructor
public class Detallecompras {
    private Long idDetallecompras;
    private Long cantidad;
    private Double total;
    private Long idProducto;
}

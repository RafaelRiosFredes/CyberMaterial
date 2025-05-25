package com.squezada.msvc.detallecompras.msvc_detallecompras.dtos;

import lombok.*;

@Getter@Setter@ToString
@NoArgsConstructor@AllArgsConstructor
public class DetalledecomprasDTO {
    private Long cantidad;
    private Double total;
    private ProductoDTO productoDTO;
    private BoletaDTO boletaDTO;
}

package com.squezada.msvc.detallecompras.msvc_detallecompras.dtos;

import lombok.*;

@ToString
@Getter
@Setter
@NoArgsConstructor@AllArgsConstructor
public class DetalledecomprasDTO {
    private Long cantidad;
    private Double total;
    private ProductoDTO productoDTO;
    private BoletaDTO boletaDTO;
}

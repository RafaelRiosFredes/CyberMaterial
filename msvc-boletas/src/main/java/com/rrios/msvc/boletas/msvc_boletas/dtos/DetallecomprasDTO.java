package com.rrios.msvc.boletas.msvc_boletas.dtos;

import lombok.*;

@Getter@Setter@ToString
@NoArgsConstructor@AllArgsConstructor
public class DetallecomprasDTO {
    private Long idDetallecompras;
    private String cantidad;
    private Double total;
    private ProductoDTO productoDTO;
}

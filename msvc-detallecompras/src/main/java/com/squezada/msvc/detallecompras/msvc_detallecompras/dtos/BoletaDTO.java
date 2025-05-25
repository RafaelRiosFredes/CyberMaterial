package com.squezada.msvc.detallecompras.msvc_detallecompras.dtos;

import lombok.*;

import java.util.Date;

@Getter@Setter@ToString
@NoArgsConstructor@AllArgsConstructor
public class BoletaDTO {
    private Long idBoleta;
    private ClienteDTO clienteDTO;
    private SucursalDTO sucursalDTO;
    private Date fechaBoleta;
    private Boolean entregaPresencial;
    private Boolean estadoPago;
}

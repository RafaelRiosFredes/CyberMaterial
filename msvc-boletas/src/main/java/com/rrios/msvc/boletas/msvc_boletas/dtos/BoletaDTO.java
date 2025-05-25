package com.rrios.msvc.boletas.msvc_boletas.dtos;

import lombok.*;

import java.util.Date;

@Getter@Setter@ToString
@NoArgsConstructor@AllArgsConstructor
public class BoletaDTO {
    private ClienteDTO clienteDTO;
    private DetallecomprasDTO detallecomprasDTO;
    private SucursalDTO sucursalDTO;
    private Date fechaBoleta;
    private Boolean entregaPresencial;
    private Boolean estadoPago;
}

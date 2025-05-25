package com.squezada.msvc.detallecompras.msvc_detallecompras.models;

import lombok.*;

import java.util.Date;

@Getter@Setter@ToString
@NoArgsConstructor@AllArgsConstructor
public class Boleta {
    private Long idBoleta;
    private Long idCliente;
    private Long idSucursal;
    private Date fechaBoleta;
    private Boolean entregaPresencial;
    private Boolean estadoPago;
}

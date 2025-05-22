package com.farancibia.msvc.clientes.msvc_clientes.models;


import lombok.*;

import java.security.PrivateKey;
import java.time.LocalDateTime;

@Getter @Setter @ToString
@NoArgsConstructor  @AllArgsConstructor
public class Boleta {
    private Long idSucursal;
    private Long idBoleta;
    private Long idCliente;
    private LocalDateTime fechaBoleta;
    private Boolean entregaPresencial;
    private Boolean estadoPago;


}

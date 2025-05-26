package com.rrios.msvc.boletas.msvc_boletas.dtos;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Getter@Setter@ToString
@NoArgsConstructor@AllArgsConstructor
public class BoletaDTO {
    private ClienteDTO clienteDTO;
    private SucursalDTO sucursalDTO;
    private LocalDate fechaBoleta;
    private Boolean entregaPresencial;
    private Boolean estadoPago;
}

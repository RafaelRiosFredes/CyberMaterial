package com.rrios.msvc.boletas.msvc_boletas.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name="boletas")
@Getter@Setter
@NoArgsConstructor@AllArgsConstructor
public class Boleta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_boleta")
    private Long idBoleta;

    @Column(name = "id_cliente")
    @NotNull(message = "El campo id cliente no puede estar vacío")
    private Long idCliente;

    @Column(name = "id_sucursal")
    @NotNull(message = "El campo id sucursal no puede estar vacío")
    private Long idSucursal;

    @Column(name = "fecha_boleta")
    @NotNull(message = "El campo fecha boleta no puede estar vacío")
    private LocalDate fechaBoleta;

    @Column(name = "entrega_presencial")
    @NotNull(message = "El campo entrega presencial no puede estar vacío")
    private Boolean entregaPresencial;

    @Column(name = "estado_pago")
    @NotNull(message = "El campo estado pago no puede estar vacío")
    private Boolean estadoPago;
}

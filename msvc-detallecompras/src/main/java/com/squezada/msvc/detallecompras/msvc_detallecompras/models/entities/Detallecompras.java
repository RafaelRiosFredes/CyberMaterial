package com.squezada.msvc.detallecompras.msvc_detallecompras.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "detallecompras")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Detallecompras {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    @NotEmpty(message = "El id del detalle de compras no puede ser vacio")
    private long iddetallecompras;


    @Column(nullable = false, unique = true)
    @NotEmpty(message = "La cantidad del detalle de compras no puede ser vacio")
    private String cantidad;

    @Column(nullable = false, unique = true)
    @NotEmpty(message = "El total del detalle de compras no puede ser vacio")
    private BigDecimal total;

    @Column(name="id_producto", nullable = false)
    @NotEmpty(message = "El id de producto no puede ser vacio")
    private Long idproducto;


}

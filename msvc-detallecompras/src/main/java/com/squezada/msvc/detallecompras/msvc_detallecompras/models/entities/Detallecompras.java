package com.squezada.msvc.detallecompras.msvc_detallecompras.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
    @Column(name ="id_detallecompras")
    private Long idDetallecompras;


    @Column(nullable = false)
    @NotNull(message = "La cantidad del detalle de compras no puede ser vacio")
    private Long cantidad;

    @Column(nullable = false)
    @NotNull(message = "El total del detalle de compras no puede ser vacio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El total debe ser mayor que cero")
    private Double total;

    @Column(name="id_producto", nullable = false)
    @NotNull(message = "El campo id producto no puede ser vacío")
    private Long idProducto;

    @Column(name = "id_boleta",nullable = false)
    @NotNull(message = "El campo id boleta no puede ser vacío")
    private Long idBoleta;

}

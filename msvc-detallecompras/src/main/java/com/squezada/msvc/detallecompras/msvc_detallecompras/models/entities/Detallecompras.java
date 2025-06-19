package com.squezada.msvc.detallecompras.msvc_detallecompras.models.entities;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Entidad que representa un producto")
public class Detallecompras {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id_detallecompras")
    @Schema(description = "Codigo del detalle de compras",example = "1")
    private Long idDetallecompras;


    @Column(nullable = false)
    @NotNull(message = "La cantidad del detalle de compras no puede ser vacio")
    @Schema(description = "Cantidad del detalle de compra",example = "1")
    private Long cantidad;

    @Column(nullable = false)
    @NotNull(message = "El total del detalle de compras no puede ser vacio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El total debe ser mayor que cero")
    @Schema(description = "Total del detalle de compras",example = "1500")
    private Double total;

    @Column(name="id_producto", nullable = false)
    @NotNull(message = "El campo id producto no puede ser vacío")
    @Schema(description = "Codigo del producto",example = "1")
    private Long idProducto;

    @Column(name = "id_boleta",nullable = false)
    @NotNull(message = "El campo id boleta no puede ser vacío")
    @Schema(description = "Codigo de la boleta",example = "1")
    private Long idBoleta;

    @Column(name = "id_inventario",nullable = false)
    @NotNull(message = "El campo id inventario no puede ser vacío")
    @Schema(description = "Codigo del inventario",example = "1")
    private Long idInventario;

    public Detallecompras(Long cantidad, Double total, Long idProducto, Long idBoleta, Long idInventario) {
        this.cantidad = cantidad;
        this.total = total;
        this.idProducto = idProducto;
        this.idBoleta = idBoleta;
        this.idInventario = idInventario;
    }
}

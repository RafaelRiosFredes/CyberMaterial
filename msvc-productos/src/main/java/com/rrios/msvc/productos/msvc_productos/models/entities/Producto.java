package com.rrios.msvc.productos.msvc_productos.models.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name="productos")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Entidad que representa un producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_producto")
    @Schema(description = "Codigo del producto",example = "1")
    private Long idProducto;

    @Column(name="nombre_producto",nullable = false)
    @NotBlank(message = "EL campo nombre de producto no puede estar vacío")
    @Schema(description = "Nombre del producto",example = "Lapiz grafito")
    private String nombreProducto;

    @Column(nullable = false)
    @NotNull(message = "El campo precio no puede estar vacío")
    @Schema(description = "Precio del producto",example = "1500")
    private Integer precio;

    @Column(nullable = false)
    @NotBlank(message = "El campo descripcion no puede estar vacío")
    @Schema(description = "Descripcion del producto",example = "Lapiz grafito H")
    private String descripcion;

    public Producto(String nombreProducto, int precio, String descripcion) {
        this.nombreProducto = nombreProducto;
        this.precio = precio;
        this.descripcion = descripcion;
    }
}

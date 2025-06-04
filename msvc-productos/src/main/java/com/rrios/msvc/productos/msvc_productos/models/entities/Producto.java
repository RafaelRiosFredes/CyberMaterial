package com.rrios.msvc.productos.msvc_productos.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name="productos")
@Getter@Setter@ToString
@NoArgsConstructor@AllArgsConstructor
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_producto")
    private Long idProducto;

    @Column(name="nombre_producto",nullable = false)
    @NotBlank(message = "EL campo nombre de producto no puede estar vacío")
    private String nombreProducto;


    @Column(nullable = false)
    @NotNull(message = "El campo precio no puede estar vacío")
    private Double precio;

    @Column(nullable = false)
    @NotBlank(message = "El campo descripcion no puede estar vacío")
    private String descripcion;

    public Producto(String nombreProducto, Double precio, String descripcion) {
        this.nombreProducto = nombreProducto;
        this.precio = precio;
        this.descripcion = descripcion;
    }
}

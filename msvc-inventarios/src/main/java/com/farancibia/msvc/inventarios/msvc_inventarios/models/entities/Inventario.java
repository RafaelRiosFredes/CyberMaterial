package com.farancibia.msvc.inventarios.msvc_inventarios.models.entities;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name="inventario")
@Getter
@Setter
@ToString
@NoArgsConstructor @AllArgsConstructor
public class Inventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_inventario")
    private Long idInventario;

    @Column (name = "id_sucursal", nullable =false)
    @NotNull(message = "El campo id sucursal no puede ser vacío")
    private Long idSucursal;


    @Column(name = "id_producto")
    @NotNull(message = "El campo id producto no puede ser vacio")
    private Long idProducto;

    @Column(name = "stock")
    @NotNull (message = "El campo stock no puede ser vacio")
    private Integer stock;
}

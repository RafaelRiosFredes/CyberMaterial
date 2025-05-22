package com.farancibia.msvc.inventarios.msvc_inventarios.models.entities;
import com.farancibia.msvc.inventarios.msvc_inventarios.models.Sucursal;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.http.codec.xml.XmlEventDecoder;

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
    private Long idSucursal;


    @Column(name = "id_producto")
    @NotNull(message = "El campo id_producto no puede ser vacio")
    private Long idProducto;

    @Column(name = "stock")
    @NotNull (message = "El campo stock no puede ser vacio")
    private Integer stock;
}

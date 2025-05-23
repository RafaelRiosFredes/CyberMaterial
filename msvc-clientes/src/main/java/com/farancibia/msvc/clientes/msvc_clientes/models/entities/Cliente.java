package com.farancibia.msvc.clientes.msvc_clientes.models.entities;


import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Entity
@Table(name="clientes")
@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id_cliente")
    private long idCliente;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "El campo run cliente no pude quedar vacio")
    @Pattern(regexp = "\\d{1,8}-[\\dKk]", message = "El formato del run debe ser XXXXXXXX-X")
    private String run;

    @Column(nullable = false)
    @NotBlank(message = "El campo nombre cliente no puede ser vacio")
    private String nombres;

    @Column(nullable = false)
    @NotBlank(message = "El campo apellido cliente no puede ser vacio")
    private String apellidos;

    @Column(nullable = false)
    @NotNull(message = "El campo telefono no puede ser vacio")
    private Integer telefono;

    @Column(nullable = true)
    private String correo;

    @Column(nullable = false)
    @NotBlank(message = "El campo direccion no puede ser vacio")
    private String direccion;

}

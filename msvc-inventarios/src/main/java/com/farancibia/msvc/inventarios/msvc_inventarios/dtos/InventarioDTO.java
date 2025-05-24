package com.farancibia.msvc.inventarios.msvc_inventarios.dtos;

import lombok.*;

@Getter @Setter @ToString
@NoArgsConstructor @AllArgsConstructor
public class InventarioDTO {

    private Integer stock;
    private ProductoDTO producto;
    private SucursalDTO sucursal;
}

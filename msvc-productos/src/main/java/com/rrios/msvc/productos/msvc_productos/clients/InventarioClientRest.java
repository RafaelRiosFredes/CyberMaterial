package com.rrios.msvc.productos.msvc_productos.clients;

import com.rrios.msvc.productos.msvc_productos.models.Inventario;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="msvc-inventarios",url="localhost:8086/api/v1/inventarios")
public interface InventarioClientRest {
    @GetMapping("/sucursal/{idSucursal}/producto/{idProducto")
    Inventario findByIdSucursalAndIdProducto(@PathVariable Long idSucursal,@PathVariable Long idProducto);
}

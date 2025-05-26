package com.squezada.msvc.detallecompras.msvc_detallecompras.clients;

import com.squezada.msvc.detallecompras.msvc_detallecompras.models.Inventario;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name ="msvc-inventarios", url = "localhost:8086/api/v1/inventarios")
public interface InventarioClientRest {
    @GetMapping("/sucursales/{idSucursal}/productos/{idProducto}")
    Inventario findByIdSucursalAndIdProducto(@PathVariable Long idSucursal, @PathVariable Long idProducto);
}

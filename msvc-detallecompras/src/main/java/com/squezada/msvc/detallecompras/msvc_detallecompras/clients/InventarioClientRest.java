package com.squezada.msvc.detallecompras.msvc_detallecompras.clients;

import com.squezada.msvc.detallecompras.msvc_detallecompras.models.Inventario;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name ="msvc-inventarios", url = "localhost:8086/api/v1/inventarios")
public interface InventarioClientRest {
    @GetMapping("/sucursales/{idSucursal}/productos/{idProducto}")
    Inventario findByIdSucursalAndIdProducto(@PathVariable Long idSucursal, @PathVariable Long idProducto);

    @PutMapping("/{id}")
    public ResponseEntity<Inventario> update(@PathVariable Long id, @RequestBody Inventario inventario);

}

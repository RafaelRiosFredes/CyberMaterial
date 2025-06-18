package com.squezada.msvc.detallecompras.msvc_detallecompras.clients;

import com.squezada.msvc.detallecompras.msvc_detallecompras.models.Sucursal;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name ="msvc-sucursales", url = "localhost:8084/api/v1/sucursales")
public interface SucursalClientRest {
    @GetMapping("/{id}")
    Sucursal findById(@PathVariable Long id);
}

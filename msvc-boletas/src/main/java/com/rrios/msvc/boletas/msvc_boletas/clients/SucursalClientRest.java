package com.rrios.msvc.boletas.msvc_boletas.clients;

import com.rrios.msvc.boletas.msvc_boletas.models.Sucursal;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "msvc-sucursales", url = "localhost:8084/api/v1/sucursales")
public interface SucursalClientRest {
    @GetMapping("/{id}")
    Sucursal findById(@PathVariable Long id);

    @GetMapping
    List<Sucursal> findAll();
}

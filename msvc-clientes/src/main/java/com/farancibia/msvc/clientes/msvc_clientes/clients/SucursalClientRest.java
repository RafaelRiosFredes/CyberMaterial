package com.farancibia.msvc.clientes.msvc_clientes.clients;


import com.squezada.msvc.sucursales.msvc_sucursales.models.entities.Sucursal;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "msvc-sucursal", url = "localhost:8084/api/v1/sucursal")
public interface SucursalClientRest {

    @GetMapping
    Sucursal findByIdCliente();



}

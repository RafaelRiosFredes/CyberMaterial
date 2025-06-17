package com.farancibia.msvc.clientes.msvc_clientes.clients;


import com.farancibia.msvc.clientes.msvc_clientes.models.Boleta;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "msvc-boleta", url = "localhost:8085/api/v1/boletas")
public interface BoletaClientRest {

    @GetMapping("/{id}")
    Boleta findById(@PathVariable Long id);

}

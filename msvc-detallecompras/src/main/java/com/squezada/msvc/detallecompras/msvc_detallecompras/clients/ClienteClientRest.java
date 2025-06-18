package com.squezada.msvc.detallecompras.msvc_detallecompras.clients;

import com.squezada.msvc.detallecompras.msvc_detallecompras.models.Cliente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name ="msvc-clientes", url = "localhost:8081/api/v1/clientes")
public interface ClienteClientRest {
    @GetMapping("/{id}")
    Cliente findById(@PathVariable Long id);
}

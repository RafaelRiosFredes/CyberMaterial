package com.rrios.msvc.boletas.msvc_boletas.clients;

import com.rrios.msvc.boletas.msvc_boletas.models.Cliente;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "msvc-clientes",url = "localhost:8081/api/v1/clientes")
public interface ClienteClientRest {
    @GetMapping("/{id}")
    Cliente findById(@PathVariable Long id);

    @GetMapping
    List<Cliente> findAll();
}

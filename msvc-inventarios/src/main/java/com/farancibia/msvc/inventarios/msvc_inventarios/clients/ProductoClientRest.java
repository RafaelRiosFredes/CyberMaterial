package com.farancibia.msvc.inventarios.msvc_inventarios.clients;

import com.farancibia.msvc.inventarios.msvc_inventarios.models.Producto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient (name = "msvc-productos", url = "localhost:8080/api/v1/productos")
public interface ProductoClientRest {

    @GetMapping ("/{id}")
    Producto findById(@PathVariable Long id);
}

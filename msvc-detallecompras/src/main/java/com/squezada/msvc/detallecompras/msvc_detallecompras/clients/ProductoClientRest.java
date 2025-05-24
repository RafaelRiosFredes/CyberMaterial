package com.squezada.msvc.detallecompras.msvc_detallecompras.clients;


import com.squezada.msvc.detallecompras.msvc_detallecompras.models.Producto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name ="msvc-producto", url = "localhost:8080/api/v1/productos")
public interface ProductoClientRest {

    @GetMapping("/productos/{id}")
    Producto findById(@PathVariable Long id);
}

package com.rrios.msvc.boletas.msvc_boletas.clients;

import com.rrios.msvc.boletas.msvc_boletas.models.Detallecompras;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "msvc-detallecompras",url = "localhost:8083/api/v1/detallecompras")
public interface DetallecomprasClientRest {
    @GetMapping("/{id}")
    Detallecompras getDetallecompras(@PathVariable Long id);

}

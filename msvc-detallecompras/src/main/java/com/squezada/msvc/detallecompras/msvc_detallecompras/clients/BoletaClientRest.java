package com.squezada.msvc.detallecompras.msvc_detallecompras.clients;

import com.squezada.msvc.detallecompras.msvc_detallecompras.dtos.BoletaDTO;
import com.squezada.msvc.detallecompras.msvc_detallecompras.models.Boleta;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name ="msvc-boletas", url = "localhost:8085/api/v1/boletas")
public interface BoletaClientRest {
    @GetMapping("/{id}")
    Boleta findById(@PathVariable Long id);
}

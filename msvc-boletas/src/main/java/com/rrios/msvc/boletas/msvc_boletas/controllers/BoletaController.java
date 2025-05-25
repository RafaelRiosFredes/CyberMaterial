package com.rrios.msvc.boletas.msvc_boletas.controllers;

import com.rrios.msvc.boletas.msvc_boletas.dtos.BoletaDTO;
import com.rrios.msvc.boletas.msvc_boletas.models.entities.Boleta;
import com.rrios.msvc.boletas.msvc_boletas.services.BoletaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/boletas")
@Validated
public class BoletaController {
    @Autowired
    private BoletaService boletaService;

    @GetMapping
    public ResponseEntity<List<BoletaDTO>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(this.boletaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Boleta> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(this.boletaService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Boleta> save(@RequestBody Boleta boleta){
        return ResponseEntity.status((HttpStatus.CREATED)).body(this.boletaService.save(boleta));
    }
}

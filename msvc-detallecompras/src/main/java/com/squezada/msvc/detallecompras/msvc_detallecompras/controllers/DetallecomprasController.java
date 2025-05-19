package com.squezada.msvc.detallecompras.msvc_detallecompras.controllers;


import com.squezada.msvc.detallecompras.msvc_detallecompras.services.DetallecomprasService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/detallecompras")
@Validated
public class DetallecomprasController {

    @Autowired
    private DetallecomprasService detallecomprasService;

    @GetMapping
    public ResponseEntity<List<Detallecompra>> findAll(){
        List<Detallecompra> detallecompras = this.detallecomprasService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(detallecompras);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Detallecompra> findById(@PathVariable Long id){
        Detallecompra detallecompra = this.detallecomprasService.findById(id);
        if(detallecompra == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(detallecompra);

    }

    @PostMapping
    public ResponseEntity<Detallecompra> save(@Valid @RequestBody Detallecompra detallecompra){
        Detallecompra saved = this.detallecomprasService.save(detallecompra);
        return ResponseEntity.status(HttpStatus.OK).body(saved);
    }
}
